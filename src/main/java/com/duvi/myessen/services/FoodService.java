package com.duvi.myessen.services;

import com.duvi.myessen.controller.exception.food.FoodExistsException;
import com.duvi.myessen.controller.exception.food.FoodNotFoundException;
import com.duvi.myessen.domain.food.Food;
import com.duvi.myessen.domain.food.FoodDTO;

import java.math.BigDecimal;
import java.util.List;

public interface FoodService {
    List<Food> getFoods();
    public Food getFood(Long id) throws FoodNotFoundException;
    public Food findByName(String name) throws FoodNotFoundException;
    public List<Food> findByToolId(Long toolId);
    public Food addFood(String name, Long kcal, BigDecimal price, String image, String description, Long toolId) throws FoodExistsException;
    // public Food getFoodByName(String name) throws FoodNotFoundException;
    public Food updateFood(Long oldFoodId, FoodDTO newFoodDTO);
    public void deleteFood(Long foodId) throws FoodNotFoundException;
}
