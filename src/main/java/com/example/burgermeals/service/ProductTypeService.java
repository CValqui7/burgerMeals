package com.example.burgermeals.service;

import com.example.burgermeals.base.BaseService;
import com.example.burgermeals.bean.ProductTypeBean;
import com.example.burgermeals.model.ProductType;
import com.example.burgermeals.repository.ProductTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
@Service
@Transactional
public class ProductTypeService extends BaseService<ProductType, ProductTypeBean, BigInteger> {
    protected ProductTypeService(ProductTypeRepository repository) {
        super(repository);
    }

    @Override
    public ProductTypeBean toBean(ProductType model) {
        ProductTypeBean bean = new ProductTypeBean();
        BeanUtils.copyProperties(model, bean);
        return bean;
    }
}
