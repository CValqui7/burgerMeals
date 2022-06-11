package com.example.burgermeals.controller;

import com.example.burgermeals.bean.ProductBean;
import com.example.burgermeals.entity.Plant;
import com.example.burgermeals.model.Product;
import com.example.burgermeals.repository.ProductRespository;
import com.example.burgermeals.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
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
@RequestMapping("product")
@Log4j2
public class ProductController {
    @Autowired
    ProductRespository productRespository;

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductBean>> getAll() {
        return ResponseEntity.ok(productService.geAll());
    }

    @PostMapping(value= {"/save/{id}"})
    public ResponseEntity<String> update(@PathVariable("id") String id, @RequestBody ProductBean productBean, HttpServletRequest req) {
        try {
            if(!id.equals("0")){
                productBean.setId(new BigInteger(id));
            }
            this.productService.save(productBean);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity("", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value= {"/delete/{id}"})
    public ResponseEntity<String> delete(@PathVariable("id") String id, HttpServletRequest req) {
        try {
            this.productService.delete(new BigInteger(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity("", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
