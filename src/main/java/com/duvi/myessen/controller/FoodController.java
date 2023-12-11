package com.duvi.myessen.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.duvi.myessen.domain.food.FoodResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duvi.myessen.controller.exception.food.FoodExistsException;
import com.duvi.myessen.controller.exception.food.FoodNotFoundException;
import com.duvi.myessen.domain.food.Food;
import com.duvi.myessen.domain.food.FoodDTO;
import com.duvi.myessen.services.FoodService;
import reactor.core.publisher.Mono;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/foods")
public class FoodController {

    @Autowired
    private FoodService service;

//     @GetMapping("/food/{name}")
//     public FoodDTO getFood(@PathVariable String name)   {
//         System.out.println(name);
//         Food food = service.getFoodByName(name);
//         FoodDTO foodTransferObject = new FoodDTO(food);
//         System.out.print(food.toString());
//         return foodTransferObject;
//     }

    //Basic CRUD Operations
    @GetMapping("/")
    public ResponseEntity<List<Food>> getAll(@RequestParam(required = false, name = "name") String name) throws FoodNotFoundException {
        if (name == null) {
            return new ResponseEntity<>(this.service.getFoods(), HttpStatus.OK);
        }
        try {
            List<Food> food = Collections.singletonList(this.service.findByName(name));
            return new ResponseEntity<>(food, HttpStatus.OK);
        } catch (FoodNotFoundException ex) {
            throw ex;
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable Long id) throws FoodNotFoundException {
        try {
            Food food = this.service.getFood(id);
            return new ResponseEntity<>(food, HttpStatus.OK);
        } catch (FoodNotFoundException ex) {
            throw ex;
        }
    }

    @PostMapping("/")
    public ResponseEntity<Food> createFood(@Valid @RequestBody FoodDTO food) throws FoodExistsException {
        try {
            Food newFood = this.service.addFood(food.name(), food.kcal(), food.price(), food.image(), food.description(), food.toolId());
            return new ResponseEntity<>(newFood, HttpStatus.OK);
        } catch (FoodExistsException ex) {
            throw ex;
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable Long id, @Valid @RequestBody FoodDTO food) {
        Food newFood = this.service.updateFood(id, food);
        return new ResponseEntity<>(newFood, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFood(@PathVariable Long id) throws FoodNotFoundException {
        try {
            this.service.deleteFood(id);
            return new ResponseEntity<>(String.format("Food with ID: %s successfully deleted!", String.valueOf(id)),HttpStatus.ACCEPTED);
        } catch (FoodNotFoundException ex) {
            throw ex;
        }
    }

    //Operations with tools
    @GetMapping("/toolId/{toolId}")
    public ResponseEntity<FoodResponse> getByToolId(@PathVariable Long toolId) {
        FoodResponse foodResponse = new FoodResponse(service.findByToolId(toolId));
        return new ResponseEntity<>(foodResponse, HttpStatus.OK);
    }



}

