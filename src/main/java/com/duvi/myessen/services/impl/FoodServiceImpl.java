package com.duvi.myessen.services.impl;

import com.duvi.myessen.services.FoodService;
import com.duvi.myessen.controller.exception.food.FoodExistsException;
import com.duvi.myessen.controller.exception.food.FoodNotFoundException;
import com.duvi.myessen.domain.food.Food;
import com.duvi.myessen.domain.food.FoodDTO;
import com.duvi.myessen.repository.FoodGateway;
import com.duvi.myessen.repository.FoodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;



@Service
public class FoodServiceImpl implements FoodService {

    // private FoodGateway gateway;
    @Autowired
    private FoodRepository repository;



    @Override
    public List<Food> getFoods() {
        return repository.findAll(); 
    }    

    @Override
    public Food getFood(Long id) throws FoodNotFoundException {
        Optional<Food> food = repository.findById(id);

        if (food.isPresent()) {
            return food.get();
        } else {
            throw new FoodNotFoundException(id);
        }
    }

    @Override
    public Food findByName(String name) throws FoodNotFoundException {
        Optional<Food> food = repository.findByName(name);
        if (food.isPresent()) {
            return food.get();
        } else {
            throw new FoodNotFoundException(name);
        }
    }
    @Override
    public List<Food> findByToolId(Long toolId) {
        List<Food> foodList = repository.findAll();
        return foodList.stream().filter((food) -> food.getToolId() == toolId).toList();
    }
    @Override
    public Food addFood(String name, Long kcal, BigDecimal price, String image, String description, Long toolId) throws FoodExistsException {
        if (repository.existsByName(name)) {
            throw new FoodExistsException(name);
        }
        else {
            Food b = new Food();
            b.setName(name);
            b.setKcal(kcal);
            b.setPrice(price);
            b.setImage(image);
            b.setDescription(description);
            b.setToolId(toolId);
            this.repository.save(b);
            return b; 
        }
    }

    // @Override
    // public Food getFoodByName(String name) throws FoodNotFoundException {
    //         Optional<Food> food = this.gateway.getFoodByName(name);
    //         if (food.isPresent()) {
    //             return food.get();
    //         } else {
    //             throw new FoodNotFoundException(name + " was not found!!");
    //         }
    // }

    @Override
    public Food updateFood(Long oldFoodId, FoodDTO newFoodDTO) {
        Optional<Food> oldFood = this.repository.findById(oldFoodId);
        Food newFood = new Food(newFoodDTO);
        if (oldFood.isPresent()) {
            Food updated = oldFood.get().update(newFood);
            repository.save(updated);
            return updated;
        } else {
            this.repository.save(newFood);
            newFood = this.repository.findByName(newFood.getName()).get();
            return newFood;
        }
        
    }

    @Override
    public void deleteFood(Long foodId) throws FoodNotFoundException {
        Optional<Food> food = this.repository.findById(foodId);
        if (food.isPresent())  {
            this.repository.deleteById(foodId);
        } else {
            throw new FoodNotFoundException(foodId);
        };
    }
}
