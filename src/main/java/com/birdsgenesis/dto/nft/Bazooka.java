package com.birdsgenesis.dto.nft;

import com.birdsgenesis.dto.meta.Metadata;
import com.birdsgenesis.dto.meta.NftAttribute;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bazooka extends Nft {

    private NftAttribute mouth;
    private NftAttribute background;
    private NftAttribute weapon;
    private NftAttribute bodyColor;
    private NftAttribute skinPattern;
    private NftAttribute outfit;
    private NftAttribute eyeshadowMain;
    private NftAttribute eyeshadowAccent;
    private NftAttribute eyes;
    private NftAttribute rightEyeSpecial;
    private NftAttribute neckAccessory;
    private NftAttribute hair;
    private NftAttribute leftEyeSpecial;
    private NftAttribute faceBolt;
    private NftAttribute personalAccessory;

    public Bazooka(Metadata metadata) throws IllegalAccessException {
        super(metadata);
    }
}
