package com.birdsgenesis.dto.nft;

import com.birdsgenesis.dto.meta.Metadata;
import com.birdsgenesis.dto.meta.NftAttribute;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Panda extends Nft {

    private NftAttribute background;
    private NftAttribute base;
    private NftAttribute nose;
    private NftAttribute color;
    private NftAttribute lineart;
    private NftAttribute clothing;
    private NftAttribute eyes;
    private NftAttribute mouth;
    private NftAttribute headwear;

    public Panda(Metadata metadata) throws IllegalAccessException {
        super(metadata);
    }
}
