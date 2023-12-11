package com.duvi.myessen.controller.exception.food;


public class FoodExistsException extends Exception {
    public FoodExistsException(Long id) {
        super(String.format("Food: \"%s\" already exists!", String.valueOf(id)));
    }

    public FoodExistsException(String name) {
        super(String.format("Food: \"%s\" already exists!", name));
    }
}
