package com.ymchen.incubatordemo.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movie {

    private Integer id;

    private String name;

    private String describe;

    private Double score;

}
