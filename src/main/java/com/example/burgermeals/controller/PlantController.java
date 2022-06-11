package com.example.burgermeals.controller;

import com.example.burgermeals.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PlantController extends JpaRepository<Plant, Integer> {

}
