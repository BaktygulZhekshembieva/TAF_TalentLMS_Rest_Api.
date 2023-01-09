package com.digital_nomads.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class Unknown {

    private int id;
    private String name;
    private int year;
    private String color;
    private String pantone_value;

}
