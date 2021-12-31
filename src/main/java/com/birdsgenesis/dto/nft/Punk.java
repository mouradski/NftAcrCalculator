package com.birdsgenesis.dto.nft;

import com.birdsgenesis.dto.meta.Metadata;
import com.birdsgenesis.dto.meta.NftAttribute;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Punk extends Nft {

    private NftAttribute background;
    private NftAttribute skinColor;
    private NftAttribute skinFeature;
    private NftAttribute punk;
    private NftAttribute neck;
    private NftAttribute faceSpecial;
    private NftAttribute mouth;
    private NftAttribute eyeWhites;
    private NftAttribute eyes;
    private NftAttribute iris;
    private NftAttribute nose;
    private NftAttribute eyebrows;
    private NftAttribute eyeAccessory;
    private NftAttribute hair;
    private NftAttribute companion;
    private NftAttribute mouthSpecial;
    private NftAttribute anomaly;

    public Punk(Metadata metadata) throws IllegalAccessException {
        super(metadata);
    }

}
