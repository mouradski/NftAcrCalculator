package com.birdsgenesis.service;

import com.birdsgenesis.dto.nft.Punk;
import com.birdsgenesis.utils.NftHelper;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PunkAcrtaServiceAbstract extends AbstractAcrService<Punk> {

    @Override
    public String getProjectName() {
        return "SongbirdPunks";
    }

    @Override
    protected Class<Punk> getNftType() {
        return Punk.class;
    }

    @Override
    protected String getMetaUrl() {
        return "https://ipfs.io/ipfs/QmVEABGSJp2YSXYdULyJuiJLLbeSrexf2iY3zmZrecc5u8/_metadata.json";
    }

    @Override
    protected Function<Object, Double> acrFunction() {
        return NftHelper.statisticalRarityScore();
    }
}
