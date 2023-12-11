package com.duvi.myessen.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duvi.myessen.domain.food.Food;

@Repository
public interface FoodRepository  extends JpaRepository<Food, Long> {
    boolean existsByName(String name);
    Optional<Food> findByName(String name);
    Optional<Food> findByToolId(Long toolId);
    
}
