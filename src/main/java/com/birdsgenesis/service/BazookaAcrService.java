package com.birdsgenesis.service;

import com.birdsgenesis.dto.nft.Bazooka;
import com.birdsgenesis.utils.NftHelper;
import com.birdsgenesis.utils.Range;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

@Service
public class BazookaAcrService extends AbstractAcrService<Bazooka> {

    @Override
    public String getProjectName() {
        return "BazookaChicks";
    }

    @Override
    protected Class<Bazooka> getNftType() {
        return Bazooka.class;
    }

    @Override
    protected String getMetaUrl() {
        return "https://ipfs.io/ipfs/QmNSQh2m4aozJESozZnCj37szuiRvyab57Nkqd25HeGMHY/_metadata.json";
    }

    @Override
    protected Function<Object, Double> acrFunction() {
        return NftHelper.statisticalRarityScore();
    }
}