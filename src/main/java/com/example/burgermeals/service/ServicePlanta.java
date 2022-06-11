package com.example.burgermeals.service;

import com.example.burgermeals.controller.PlantController;
import com.example.burgermeals.entity.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("plant")
public class ServicePlanta {

    @Autowired
    private PlantController plantController;

    @GetMapping
    public ResponseEntity<List<Plant>> getPlant() {
        List<Plant> plantList =(List<Plant>)plantController.findAll();
        return ResponseEntity.ok(plantList);
    }

    @RequestMapping(value="{plantId}")
    public ResponseEntity<Plant> getPlantById(@PathVariable("plantId") int plantId) {
        Optional<Plant> plantOptional = plantController.findById(plantId);
        return plantOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());

    }

    @RequestMapping(value="delete/{plantId}")
    public String deletePlantById(@PathVariable("plantId") Integer plantId) {
        Optional<Plant> plantOptional = plantController.findById(plantId);
        if(plantOptional.isPresent()){
            plantController.deleteById(plantId);
            return "Se elimino";
        }
        return "No se pudo eliminar";

    }
}
