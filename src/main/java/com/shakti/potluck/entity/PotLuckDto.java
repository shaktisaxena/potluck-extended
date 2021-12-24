package com.shakti.potluck.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
public class PotLuckDto {

    private String potLuckId;
    /**
     * Name of the potluck
     * Examples: Silvester Party, diwali
     */
    @NotNull(message = "name of the potluck cannot be null.")
    @NotEmpty(message = "Name of Potluck event cannot be empty.")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,20}$",message = "Potluck name must not contain any special characters.")
    private String name;

    /**
     * no. of families invited in potluck
     *  Example 2, 3 4
     */
    @Min(value = 1, message = "The number of families must be greater than 1")
    @Max(value = 5, message = "The number of families must be greater than 5")
    private Integer familyCount;

    /**
     * date planned for the potluck. Date must be in future.
     * Example : dd/mm/yyyy
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @NotNull(message = "The planned date is required.")
    @FutureOrPresent(message = "The planned date must be today or in the future.")
    private LocalDate plannedDate;

    /**
     * food items planned
     */
    @NotNull(message = "The food-items are required.")
    @NotEmpty(message = "The food types must have at least 2 item.")
    private String[] foodItems;
}
