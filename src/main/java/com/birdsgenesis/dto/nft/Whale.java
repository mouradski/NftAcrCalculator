package com.birdsgenesis.dto.nft;

import com.birdsgenesis.dto.meta.Metadata;
import com.birdsgenesis.dto.meta.NftAttribute;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Whale extends Nft {

    private NftAttribute background;
    private NftAttribute base;
    private NftAttribute clothing;
    private NftAttribute finTailAccessory;
    private NftAttribute neckAccessory;
    private NftAttribute head;
    private NftAttribute mouthAccessory;
    private NftAttribute companion;

    public Whale(Metadata metadata) throws IllegalAccessException {
        super(metadata);
    }
}
