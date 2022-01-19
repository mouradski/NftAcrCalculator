package com.birdsgenesis.dto.nft;

import com.birdsgenesis.dto.meta.Metadata;
import com.birdsgenesis.dto.meta.NftAttribute;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bomber extends Nft {

    private NftAttribute background;
    private NftAttribute backgroundLines;
    private NftAttribute backgroundRocks;
    private NftAttribute shadow;
    private NftAttribute sideBombsP1;
    private NftAttribute sideBombsP2;
    private NftAttribute accessories;
    private NftAttribute straps;
    private NftAttribute middle;
    private NftAttribute top;
    private NftAttribute fins;
    private NftAttribute base;
    private NftAttribute blades;
    private NftAttribute stickers;

    public Bomber(Metadata metadata) throws IllegalAccessException {
        super(metadata);
    }
}
