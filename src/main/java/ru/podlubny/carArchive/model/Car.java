package ru.podlubny.carArchive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Getter
@Setter
@Document("car")
public class Car {
    @Id
    private String id;

    private String model;
    private String mark;
    private String vin;
    private double price;
    private double mileage;
}

