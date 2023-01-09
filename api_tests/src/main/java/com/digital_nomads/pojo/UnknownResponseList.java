package com.digital_nomads.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class UnknownResponseList {
    @JsonProperty("data")
    /**это анатация привязывает.
     * т.е весь список поль-й. лежат внутри массива "data"
     * и это "data" присвоиваем к полям User */
    private List<Unknown> unknowns;
}
