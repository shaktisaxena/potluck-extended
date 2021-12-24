package com.shakti.potluck.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class FoodItem {
    private String name;
    private FOODTYPE foodtype;
    private FOODCATEGORY foodcategory;

}
