package com.birdsgenesis.service;

import com.birdsgenesis.dto.nft.Panda;
import com.birdsgenesis.utils.NftHelper;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PrettyPandaAcrService extends AbstractAcrService<Panda> {

    @Override
    public String getProjectName() {
        return "PrettyPandas";
    }

    @Override
    protected Class<Panda> getNftType() {
        return Panda.class;
    }

    @Override
    protected String getMetaUrl() {
        return "https://ipfs.io/ipfs/QmaQUXdPXyoSuPzjPHyifnT21o7LR9CDQ7u3sMK9q7pLY3/_metadata.json";
    }

    @Override
    protected Function<Object, Double> acrFunction() {
        return NftHelper.statisticalRarityScore();
    }
}