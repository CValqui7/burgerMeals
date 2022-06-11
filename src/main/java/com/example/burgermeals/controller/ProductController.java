package com.example.burgermeals.controller;

import com.example.burgermeals.base.BasicXDFController;
import com.example.burgermeals.bean.ProductBean;
import com.example.burgermeals.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;

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
@RequestMapping("/product")
@Log4j2
public class ProductController extends BasicXDFController<ProductBean, BigInteger> {

    public ProductController(ProductService service) {
        super(service);
    }
}
