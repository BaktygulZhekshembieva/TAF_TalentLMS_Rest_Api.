package com.digital_nomads.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(value = {"page", "per_page", "total", "total_pages"}, ignoreUnknown = true)
//это анатация игнорит эти поля (филды) в постмене кот-е выходят в боди
//ignoreUnknown = true игнорит новые добавленные поля, т.е незнакомые поля

public class User { // это класс для конкретного пользователя
    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;

}
