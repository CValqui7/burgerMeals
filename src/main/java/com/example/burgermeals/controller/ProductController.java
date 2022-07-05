package com.example.burgermeals.controller;

import com.example.burgermeals.base.BasicXDFController;
import com.example.burgermeals.bean.ProductBean;
import com.example.burgermeals.repository.ProductRespository;
import com.example.burgermeals.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/service/product")
@Log4j2
public class ProductController extends BasicXDFController<ProductBean, BigInteger> {
    @Autowired
    ProductService productService;

    public ProductController(ProductService service) {
        super(service);
    }

    @RequestMapping(value = "/allActive", method = RequestMethod.GET)
    public ResponseEntity<String> findAll(HttpServletRequest req) {
        List<ProductBean> ts = productService.getAllActive();
        return new ResponseEntity<>(gson.toJson(ts), HttpStatus.OK);
    }
}
