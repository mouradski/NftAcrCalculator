package com.birdsgenesis.service;

import com.birdsgenesis.dto.meta.Metadata;
import com.birdsgenesis.dto.meta.NftAttribute;
import com.birdsgenesis.dto.nft.Nft;
import com.birdsgenesis.utils.NftHelper;
import com.birdsgenesis.utils.Range;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractAcrService<T extends Nft> {

    private List<T> nfts = new ArrayList<>();

    protected abstract Class<T> getNftType();

    protected abstract String getMetaUrl();

    protected abstract Function<Object, Double> acrFunction();

    public abstract String getProjectName();

    protected Set<Range> getBurnedEditions() {
        return null;
    }

    @PostConstruct
    public void init() throws Exception {
        HttpEntity<String> entity = getEntity();

        ResponseEntity<Metadata[]> response = new RestTemplate()
                .exchange(getMetaUrl(),
                        HttpMethod.GET, entity, Metadata[].class);


        for (Metadata metadata : response.getBody()) {
            T nft = getNftType().getConstructor(Metadata.class).newInstance(metadata);
            if (!isBurned(nft.getId())) {
                this.nfts.add(nft);
            }
        }

        updateStats();
    }

    public List<T> getNfts(List<Integer> ids, Integer limit) {
        if (ids.isEmpty()) {
            return nfts.stream().sorted(Comparator.comparing(T::getAcr))
                    .limit(limit)
                    .collect(Collectors.toList());
        } else {
            return nfts.stream()
                    .filter(nft -> ids.contains(nft.getId()))
                    .sorted(Comparator.comparing(T::getAcr))
                    .collect(Collectors.toList());
        }
    }

    private MultiKeyMap<String, Double> updateStats() throws Exception {

        MultiKeyMap<String, Double> stats = new MultiKeyMap<>();
        MultiKeyMap<String, Integer> counts = new MultiKeyMap<>();


        this.nfts.forEach(nft ->
                NftHelper.getAttributeFields(getNftType()).forEach(field -> {
                    try {
                        field.setAccessible(true);
                        NftAttribute value = (NftAttribute) field.get(nft);
                        value.setType(field.getName());

                        if (!counts.containsKey(field.getName(), value.toString())) {
                            counts.put(field.getName(), value.toString(), 0);
                        }

                        counts.put(field.getName(), value.toString(), counts.get(field.getName(), value.toString()) + 1);
                    } catch (Exception e) {
                        log.warn("Unable to calculate stats for field {} on edition {}:{}", field.getName(), this.getProjectName(), nft.getId());
                    }
                })
        );


        counts.entrySet().forEach(entry -> {
            stats.put(entry.getKey(), (entry.getValue().doubleValue() * 100) / nfts.size());
        });


        for (T nft : nfts) {
            NftHelper.attributes(nft)
                    .forEach(attribute ->
                            attribute.setRarity(stats.get(attribute.getType(), attribute.getValue()))
                    );

            nft.setAcr(acrFunction().apply(nft));
        }

        List<T> sorted = nfts.stream().sorted(Comparator.comparing(T::getAcr)).collect(Collectors.toList());

        int acr = 1;

        for (T nft : sorted) {
            nft.setAcr((double) acr);
            acr++;
        }

        return stats;
    }

    public Set<String> getList(String type) throws IllegalAccessException {
        Field field = Arrays.stream(getNftType().getDeclaredFields())
                .filter(f -> f.getName().equalsIgnoreCase(type)).findAny().orElse(null);

        if (field == null) {
            return new HashSet<>();
        }

        field.setAccessible(true);

        Set<String> values = new HashSet<>();

        for (T nft : nfts) {
            values.add(((NftAttribute) field.get(nft)).getValue());
        }

        return values;
    }

    private boolean isBurned(Integer id) {
        if (getBurnedEditions() == null) {
            return false;
        }

        for (Range range : getBurnedEditions()) {
            if (range.contains(id)) {
                return true;
            }
        }

        return false;
    }

    private HttpEntity getEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("user-agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        return new HttpEntity("parameters", headers);
    }

}
