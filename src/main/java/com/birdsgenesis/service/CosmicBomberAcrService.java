package com.birdsgenesis.service;

import com.birdsgenesis.dto.nft.Bomber;
import com.birdsgenesis.utils.NftHelper;
import com.birdsgenesis.utils.Range;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Service
public class CosmicBomberAcrService extends AbstractAcrService<Bomber> {

    @Override
    protected Class<Bomber> getNftType() {
        return Bomber.class;
    }

    @Override
    protected String getMetaUrl() {
        return "https://ipfs.io/ipfs/QmfX2NpneJNKfk3xemkxEAvygcdGzQ4ijrRwRsxbSsxJRg/_metadata.json";
    }

    @Override
    protected Function<Object, Double> acrFunction() {
        return NftHelper.statisticalRarityScore();
    }

    @Override
    public String getProjectName() {
        return "CosmicBombers";
    }

    @Override
    public List<Range> getBurntEditions() {
        return Arrays.asList(new Range(1001, 10000));
    }
}
