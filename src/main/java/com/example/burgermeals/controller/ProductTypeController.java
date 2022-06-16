package com.example.burgermeals.controller;

import com.example.burgermeals.model.ProductType;
import com.example.burgermeals.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Cristian J. Valqui Cabrera
 * @version 18/05/2022
 * <p>
 * Copyright (c) 2022 CValqui, S.A. Todos los derechos reservados.
 * <p>
 * Este software constituye información confidencial y propietaria de CValqui,
 * ("Información Confidencial"). Usted no debe develar dicha Información
 * Confidencial y debe usarla de acuerdo con los términos de aceptación de
 * licencia de uso que firmó con Byte.
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("product-type")
public class ProductTypeController {
    @Autowired
    ProductTypeRepository productTypeRepository;

    @GetMapping
    public ResponseEntity<List<ProductType>> getAll() {
        List<ProductType> plantList = productTypeRepository.findAll();
        return ResponseEntity.ok(plantList);
    }
}
