package com.shakti.potluck.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
public class PotLuck {

    @Id
    private String id;

    /**
     * name of the potluck
     * Examples: Silvester Party, diwali
     */

    private String name;

    /**
     * no. of families invited in potluck
     *  Example 2, 3 4
     */

    private Integer familyCount;

    /**
     * date planned for the potluck
     * Example : dd/mm/yyyy
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate plannedDate;

    /**
     * food items planned
     */

    private String[] foodItems;
}
