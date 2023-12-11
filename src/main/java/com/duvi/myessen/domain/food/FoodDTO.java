package com.duvi.myessen.domain.food;

import java.math.BigDecimal;

public record FoodDTO(String name, Long kcal, BigDecimal price, String image, String description, Long toolId) {
    public FoodDTO(Food food) {
        this(food.getName(), food.getKcal(), food.getPrice(), food.getImage(), food.getDescription(), food.getToolId());
    }
}
