package com.example.burgermeals.controller;

import com.example.burgermeals.bean.VoucherBean;
import com.example.burgermeals.service.VoucherService;
import lombok.extern.log4j.Log4j2;
import com.example.burgermeals.base.BasicXDFController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

/**
 * @author Cristian J. Valqui Cabrera
 * @version 02/07/2022
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
@RequestMapping("/service/voucher")
@Log4j2
public class VoucherController extends BasicXDFController<VoucherBean, BigInteger> {

    public VoucherController(VoucherService service) {
        super(service);
    }

}
