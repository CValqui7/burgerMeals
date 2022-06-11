package com.example.burgermeals.service;

import com.example.burgermeals.base.BaseService;
import com.example.burgermeals.bean.ProductBean;
import com.example.burgermeals.enums.ItemState;
import com.example.burgermeals.model.Product;
import com.example.burgermeals.model.ProductType;
import com.example.burgermeals.repository.ProductRespository;
import com.example.burgermeals.repository.ProductTypeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
@Service
@Transactional
@Log4j2
public class ProductService extends BaseService<Product, ProductBean, BigInteger> {

    protected ProductService(ProductRespository repository) {
        super(repository);
    }

    @Override
    protected Product toModel(Product model, ProductBean bean) {
        if (model == null) {
            model = new Product();
        }
        BeanUtils.copyProperties(bean, model);
        model.setStatus(ItemState.fromString(bean.getStatus()));

        return model;
    }

    @Override
    protected ProductBean toBean(Product model) {
        ProductBean bean = new ProductBean();
        BeanUtils.copyProperties(model, bean);
        bean.setStatus(model.getStatus().getName());
        return bean;
    }

}
