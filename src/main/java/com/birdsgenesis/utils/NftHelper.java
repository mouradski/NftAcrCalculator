package com.birdsgenesis.utils;

import com.birdsgenesis.dto.meta.NftAttribute;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NftHelper {

    public static Function<Object, Double> statisticalRarityScore() {
        return (nft) -> {
            double score = 1;
            Field[] fields = nft.getClass().getDeclaredFields();
            for (Field fileid : fields) {
                if (fileid.getType().equals(NftAttribute.class)) {
                    fileid.setAccessible(true);
                    NftAttribute attribute = null;
                    try {
                        attribute = (NftAttribute) fileid.get(nft);
                        score = score * attribute.getRarity();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return score;
        };
    }

    public static Function<Object, Double> averageTraitRarityScore() {
        return (nft) -> {
            double sum = 0;
            int nbr = 0;
            Field[] fields = nft.getClass().getDeclaredFields();
            for (Field fileid : fields) {
                if (fileid.getType().equals(NftAttribute.class)) {
                    fileid.setAccessible(true);
                    NftAttribute attribute = null;
                    try {
                        attribute = (NftAttribute) fileid.get(nft);
                        sum += attribute.getRarity();
                        nbr++;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            return sum / nbr;
        };
    }

    public static Function<Object, Double> rarityScore() {
        return (nft) -> {
            double sum = 0;
            Field[] fields = nft.getClass().getDeclaredFields();
            for (Field fileid : fields) {
                if (fileid.getType().equals(NftAttribute.class)) {
                    fileid.setAccessible(true);
                    NftAttribute attribute = null;
                    try {
                        attribute = (NftAttribute) fileid.get(nft);
                        sum += attribute.getRarity();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            return sum;
        };
    }

    public static List<Field> getAttributeFields(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields()).filter(field -> field.getType().equals(NftAttribute.class)).collect(Collectors.toList());
    }

    public static List<NftAttribute> attributes(Object obj) throws IllegalAccessException {
        List<NftAttribute> nftAttributes = new ArrayList<>();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field fileid : fields) {
            if (fileid.getType().equals(NftAttribute.class)) {
                fileid.setAccessible(true);
                NftAttribute attribute = (NftAttribute) fileid.get(obj);

                nftAttributes.add(attribute);
            }
        }

        return nftAttributes;
    }

}
