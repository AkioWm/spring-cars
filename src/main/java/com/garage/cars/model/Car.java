package com.garage.cars.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="make_name")
    private String makeName;
    @Column(name="model_name")
    private String modelName;
    @Column(name="horsepower")
    private int horsePower;
    @Column(name = "retail_price")
    private int retailPrice;

}
