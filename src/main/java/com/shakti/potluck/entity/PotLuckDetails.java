package com.shakti.potluck.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Getter
@Setter
public class PotLuckDetails {

    private String id;
    private String name;
    private LocalDate plannedDate;
    private String plannedLocation;
    private String[] foodItems;
    private Integer familyCount;
    private String userEmail;
    private Address address;
    private String[] guests;
}
