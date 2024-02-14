package com.garage.cars.model;
import lombok.Data;

@Data
public class Car {


    private Long id;

    private String makeName;

    private String modelName;

    private int horsePower;

    private int retailPrice;

}
