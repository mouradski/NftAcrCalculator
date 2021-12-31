package com.birdsgenesis.dto.meta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NftAttribute {
    private double rarity;
    private String value;

    @JsonIgnore
    private String type;

    @Override
    public String toString() {
        return value;
    }
}
