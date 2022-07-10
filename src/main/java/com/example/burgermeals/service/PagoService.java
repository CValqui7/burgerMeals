package com.example.burgermeals.service;

import com.example.burgermeals.base.BaseService;
import com.example.burgermeals.bean.PagoBean;
import com.example.burgermeals.bean.ProductBean;
import com.example.burgermeals.enums.ItemState;
import com.example.burgermeals.exception.NotFoundException;
import com.example.burgermeals.model.Metodo;
import com.example.burgermeals.model.Pago;
import com.example.burgermeals.model.Product;
import com.example.burgermeals.repository.MetodoRepository;
import com.example.burgermeals.repository.PagoRespository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Optional;

/**
 * @author Cristian J. Valqui Cabrera
 * @version 05/07/2022
 * <p>
 * Copyright (c) 2022 CValqui, S.A. Todos los derechos reservados.
 * <p>
 * Este software constituye información confidencial y propietaria de CValqui,
 * ("Información Confidencial"). Usted no debe develar dicha Información
 * Confidencial y debe usarla de acuerdo con los términos de aceptación de
 * licencia de uso que firmó con Byte.
 */
@Service
@Transactional
@Log4j2
public class PagoService extends BaseService<Pago, PagoBean, BigInteger> {
    @Autowired
    MetodoService metodoService;
    @Autowired
    MetodoRepository metodoRepository;

    protected PagoService(PagoRespository repository) {
        super(repository);
    }

    @Override
    protected Pago toModel(Pago pago, PagoBean pagoBean) {
        if(pago == null) {
            pago = new Pago();
        }
        if(pagoBean.getMetodo() == null ){
            throw new NullPointerException("Metodo no puede estar vacio");
        }

        BeanUtils.copyProperties(pagoBean, pago);

        pago.setStatus(ItemState.fromString(pagoBean.getStatus()));
        Optional<Metodo> metodoOptional = metodoRepository.findById(pagoBean.getMetodo());

        if(!metodoOptional.isPresent()) {
            throw  new NotFoundException("Metodo no existe");
        }

        pago.setMetodo(metodoOptional.get());


        return pago;
    }

    @Override
    protected PagoBean toBean(Pago model) {
        PagoBean pagoBean = new PagoBean();

        BeanUtils.copyProperties(model, pagoBean);
        pagoBean.setStatus(model.getStatus().getName());
        Optional<Metodo> metodo = metodoRepository.findById(model.getMetodo().getId());
        pagoBean.setMetodoBean(metodoService.toBean(model.getMetodo()));
        pagoBean.setMetodo(model.getMetodo().getId());

        return pagoBean;
    }
}
