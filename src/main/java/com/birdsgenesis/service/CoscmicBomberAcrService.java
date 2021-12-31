package com.birdsgenesis.service;

import com.birdsgenesis.dto.nft.Bomber;
import com.birdsgenesis.utils.NftHelper;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CoscmicBomberAcrService extends AbstractAcrService<Bomber> {

    @Override
    protected Class<Bomber> getNftType() {
        return Bomber.class;
    }

    @Override
    protected String getMetaUrl() {
        return "https://ipfs.io/ipfs/QmfX2NpneJNKfk3xemkxEAvygcdGzQ4ijrRwRsxbSsxJRg/_metadata.json";
    }

    @Override
    protected Function<Object, Double> acrFunction() throws IllegalAccessException {
        return NftHelper.statisticalRarityScore();
    }

    @Override
    public String getProjectName() {
        return "CosmicBombers";
    }
}
