package com.example.burgermeals.service;

import com.example.burgermeals.base.BaseService;
import com.example.burgermeals.bean.ProductBean;
import com.example.burgermeals.enums.ItemState;
import com.example.burgermeals.exception.NotFoundException;
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
    @Autowired
    ProductRespository productRespository;

    @Autowired
    ProductTypeRepository productTypeRepository;

    @Autowired
    private ProductTypeService productTypeService;

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
        if (bean.getProductTypeBean() == null) {
            throw new NullPointerException();
        }
        Optional<ProductType> productTypeOptional = productTypeRepository.findById(bean.getProductTypeBean().getId());
        if (!productTypeOptional.isPresent()) {
            throw new NotFoundException("No se encontro el tipo de producto seleccionado.");
        }
        model.setTypeProduct(productTypeOptional.get());
        return model;
    }

    @Override
    protected ProductBean toBean(Product model) {
        ProductBean bean = new ProductBean();
        BeanUtils.copyProperties(model, bean);
        bean.setProductTypeBean(productTypeService.toBean(model.getTypeProduct()));
        bean.setStatus(model.getStatus().getName());
        return bean;
    }

    protected ProductBean updateStatus(ProductBean productBean, boolean status) {
        Optional<Product> productOptional = productRespository.findById(productBean.getId());

        if (!productOptional.isPresent()) {
            throw new NotFoundException("Product is not found");
        }

        return productBean;
    }

    public List<ProductBean> getAllActive() {
        List<Product> productList = productRespository.findAllByStatus(ItemState.ACTIVE);
        List<ProductBean> productBeanList = new ArrayList<>();

        for (Product product : productList) {
            ProductBean productBean= toBean(product);
            productBeanList.add(productBean);
        }

        return productBeanList;
    }

}
