package com.birdsgenesis.dto.nft;

import com.birdsgenesis.dto.meta.Metadata;
import com.birdsgenesis.dto.meta.NftAttribute;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bird extends Nft {

    private NftAttribute background;
    private NftAttribute eyes;
    private NftAttribute headWear;
    private NftAttribute beaks;
    private NftAttribute body;
    private NftAttribute bodyWear;
    private NftAttribute earFeathers;
    private NftAttribute extras;

    public Bird(Metadata metadata) throws IllegalAccessException {
        super(metadata);
    }
}
