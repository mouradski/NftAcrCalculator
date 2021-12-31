package com.birdsgenesis.dto.nft;

import com.birdsgenesis.dto.meta.Attribute;
import com.birdsgenesis.dto.meta.Metadata;
import com.birdsgenesis.dto.meta.NftAttribute;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
public abstract class Nft {

    protected String dna;
    protected Integer id;
    protected Double acr;
    protected String imageUrl;

    protected Nft(Metadata metadata) throws IllegalAccessException {
        this.dna = metadata.getDna();
        this.id = metadata.getEdition();
        this.imageUrl = metadata.getImage().replace("ipfs://", "https://ipfs.io/ipfs/");

        List<Attribute> attributes = metadata.getAttributes();

        initAttributesWithNoneValue();

        for (Attribute attribute : attributes) {

            Field[] fileds = this.getClass().getDeclaredFields();

            for (Field field : fileds) {
                if (matchesField(field, attribute.getTraitType())) {
                    NftAttribute nftAttribute = NftAttribute.builder().value(getTrait(metadata, attribute.getTraitType())).build();
                    field.setAccessible(true);
                    field.set(this, nftAttribute);
                }
            }
        }
    }

    private void initAttributesWithNoneValue() {
        Arrays.stream(this.getClass().getDeclaredFields())
                .filter(filed -> filed.getType().equals(NftAttribute.class))
                .forEach(field -> {
                    try {
                        field.setAccessible(true);
                        if (field.get(this) == null) {
                            field.set(this, NftAttribute.builder().value("None").build());
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
    }

    protected String getTrait(Metadata metadata, String traitName) {
        return metadata.getAttributes().stream()
                .filter(trait -> trait.getTraitType().equals(traitName))
                .map(Attribute::getValue)
                .findFirst().get();
    }

    private boolean matchesField(Field field, String traitName) {
        return field.getType().equals(NftAttribute.class)
                && traitName.replaceAll(" ", "").toLowerCase().equalsIgnoreCase(field.getName().toLowerCase(Locale.ROOT));
    }
}
