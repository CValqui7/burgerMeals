package com.example.burgermeals.service;

import com.example.burgermeals.base.BaseService;
import com.example.burgermeals.bean.VoucherDetailBean;
import com.example.burgermeals.exception.NotFoundException;
import com.example.burgermeals.model.Product;
import com.example.burgermeals.model.Voucher;
import com.example.burgermeals.model.VoucherDetail;
import com.example.burgermeals.repository.ProductRespository;
import com.example.burgermeals.repository.VoucherDetailRepository;
import com.example.burgermeals.repository.VoucherRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Optional;

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

@Service
@Transactional
@Log4j2
public class VoucherDetailService extends BaseService<VoucherDetail, VoucherDetailBean, BigInteger> {
    @Autowired
    VoucherDetailRepository voucherDetailRepository;

    @Autowired
    ProductRespository productRespository;

    @Autowired
    VoucherRepository voucherRepository;

    protected VoucherDetailService(VoucherDetailRepository repository) {
        super(repository);
    }

    @Override
    protected VoucherDetail toModel(VoucherDetail model, VoucherDetailBean bean) {
        if (model == null) {
            model = new VoucherDetail();
        }
        BeanUtils.copyProperties(bean, model);
        return model;
    }

    @Override
    protected VoucherDetailBean toBean(VoucherDetail model) {
        VoucherDetailBean bean = new VoucherDetailBean();
        BeanUtils.copyProperties(model, bean);
        return bean;
    }


    public void createDetail(VoucherDetailBean voucherDetailBean, Voucher voucher) {
        if (voucher == null || voucher.getId() == null) {
            throw new NullPointerException("Voucher can not be null");
        }
        if (voucherDetailBean.getProductId() == null) {
            throw new NullPointerException("Product can not be null");
        }

        Optional<Product> productOptional = productRespository.findById(voucherDetailBean.getProductId());
        if (!productOptional.isPresent()) {
            throw new NotFoundException("Product is not found");
        }
        Optional<Voucher> voucherOptional = voucherRepository.findById(voucher.getId());
        if (!voucherOptional.isPresent()) {
            throw new NotFoundException("Voucher is not found");
        }
        VoucherDetail voucherDetail = new VoucherDetail();

        voucherDetail.setProduct(productOptional.get());
        voucherDetail.setVoucher(voucherOptional.get());

        BeanUtils.copyProperties(voucherDetailBean, voucherDetail);

        voucherDetailRepository.save(voucherDetail);
    }
}
