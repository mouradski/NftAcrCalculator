package com.birdsgenesis.service;

import com.birdsgenesis.dto.nft.Bird;
import com.birdsgenesis.utils.NftHelper;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BirdsAbstractAcrService extends AbstractAcrService<Bird> {

    @Override
    public String getProjectName() {
        return "BirdsGenesis";
    }

    @Override
    protected Class<Bird> getNftType() {
        return Bird.class;
    }

    @Override
    protected String getMetaUrl() {
        return "https://sparkles.mypinata.cloud/ipfs/QmXe2RLWnagcD62nSxr45CwA9vPKVNoALwazY9UbiVNF6g/_metadata.json";
    }

    @Override
    protected Function<Object, Double> acrFunction() {
        return NftHelper.statisticalRarityScore();
    }
}