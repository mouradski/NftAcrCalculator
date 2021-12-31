package com.birdsgenesis.dto.meta;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "trait_type",
        "value"
})
@Getter
@Setter
public class Attribute {

    @JsonProperty("trait_type")
    private String traitType;
    @JsonProperty("value")
    private String value;
}