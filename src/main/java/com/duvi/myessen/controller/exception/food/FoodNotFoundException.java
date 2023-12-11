package com.duvi.myessen.controller.exception.food;

public class FoodNotFoundException extends Exception {


    public FoodNotFoundException(Long id) {
        super(String.format("Food with id %s was not found", String.valueOf(id)));
    }
    public FoodNotFoundException(String name) {
        super(String.format("Food with name %s was not found", name));
    }

    
}
