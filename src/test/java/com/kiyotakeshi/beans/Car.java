package com.kiyotakeshi.beans;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.NONE)
public class Car {

    private Integer id;
    private String make;
    private String model;
    private String color;
    private Integer year;
    private Double price;
}
