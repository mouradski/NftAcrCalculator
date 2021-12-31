package com.birdsgenesis.dto.meta;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "description",
        "image",
        "dna",
        "edition",
        "date",
        "attributes"
})
@Getter
@Setter
public class Metadata {

    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("image")
    private String image;
    @JsonProperty("dna")
    private String dna;
    @JsonProperty("edition")
    private Integer edition;
    @JsonProperty("date")
    private Long date;
    @JsonProperty("attributes")
    private List<Attribute> attributes = null;
}