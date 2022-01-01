package com.birdsgenesis.service;

import com.birdsgenesis.dto.nft.Whale;
import com.birdsgenesis.utils.NftHelper;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MrSGBWhalesAcrService extends AbstractAcrService<Whale> {

    @Override
    protected Class<Whale> getNftType() {
        return Whale.class;
    }

    @Override
    protected String getMetaUrl() {
        return "https://ipfs.io/ipfs/QmdJPpG6TbbjDiJZLRzsrvuNKUsMJy6vXi9uKujRd5QwpT/_metadata.json";
    }

    @Override
    protected Function<Object, Double> acrFunction() throws IllegalAccessException {
        return NftHelper.statisticalRarityScore();
    }

    @Override
    public String getProjectName() {
        return "MrSGBWhales";
    }
}
