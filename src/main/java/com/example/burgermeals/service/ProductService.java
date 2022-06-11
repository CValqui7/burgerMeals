package com.example.burgermeals.service;

import com.example.burgermeals.bean.ProductBean;
import com.example.burgermeals.enums.ItemState;
import com.example.burgermeals.model.Product;
import com.example.burgermeals.model.ProductType;
import com.example.burgermeals.repository.ProductRespository;
import com.example.burgermeals.repository.ProductTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ProductService {

    @Autowired
    private ProductRespository productRespository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    protected Product toModel(Product model, ProductBean bean) {
        if (model == null) {
            model = new Product();
        }
        BeanUtils.copyProperties(bean, model);
        model.setStatus(ItemState.fromString(bean.getStatus()));

        return model;
    }

    protected ProductBean toBean(Product model) {
        ProductBean bean = new ProductBean();
        BeanUtils.copyProperties(model, bean);
        bean.setStatus(model.getStatus().getName());
        return bean;
    }

    public List<ProductBean> geAll(){
        List<ProductBean> productBeanList = new ArrayList<>();
        List<Product> productList = productRespository.findAll();

        for(Product product : productList){
            productBeanList.add(toBean(product));
        }
        return productBeanList;
    }

    public Product save(ProductBean productBean) {
        Product product = new Product();
        if(productBean.getId() != null){
            Optional<Product> productOptional = productRespository.findById(productBean.getId());
            if(productOptional.isPresent()){
                product = productOptional.get();
            }
        }
        product = toModel(product, productBean);
        return productRespository.save(product);
    }

    public void delete(BigInteger id){
        Optional<Product> productOptional = productRespository.findById(id);
        if(productOptional.isPresent()){
            productRespository.delete(productOptional.get());
        }
    }


}
