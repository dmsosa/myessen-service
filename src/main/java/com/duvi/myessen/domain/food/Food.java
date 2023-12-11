package com.duvi.myessen.domain.food;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Validators
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;


@Table(name = "foods ")
@Entity(name = "food")
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Food {

    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull(message = "Jedes zutaten muss ein name haben")
    @Pattern(regexp="^[a-zA-Z ]+$", message = "Die name muss ein Ketter sein!")
    private String name;

    @NotNull(message = "jede einzige Zutaten ein Kaloienwert hat!")
    @Positive(message = "es gibt keine Zutaten mit negativen Kalorienwert, bruder!")
    private Long kcal;

    @NotNull(message = "jede einzige Zutaten ein Geldwert hat!")
    @Positive(message = "Die Zutaten  nicht kostenlos ist, bruder!!")
    private BigDecimal price;

    @URL
    private String image;
    private String description;
    private Long toolId;

    public Food(String name, Long kcal, BigDecimal price) {
        this.name = name;
        this.kcal = kcal;
        this.price = price;
    }
    public Food(FoodDTO foodDTO) {
        this.name = foodDTO.name();
        this.kcal = foodDTO.kcal();
        this.price = foodDTO.price();
        this.image = foodDTO.image();
        this.description = foodDTO.description();
        this.toolId = foodDTO.toolId();
    }
    public Food update(Food newFood) {
        return new Food(
            this.id, 
            newFood.getName(), 
            newFood.getKcal(), 
            newFood.getPrice(), 
            newFood.getImage(), 
            newFood.getDescription(),
                newFood.getToolId()
            );
    }

}
