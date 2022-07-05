package com.example.burgermeals.service;

import com.example.burgermeals.base.BaseService;
import com.example.burgermeals.bean.VoucherBean;
import com.example.burgermeals.bean.VoucherDetailBean;
import com.example.burgermeals.model.Voucher;
import com.example.burgermeals.model.VoucherDetail;
import com.example.burgermeals.repository.VoucherRepository;
import lombok.extern.log4j.Log4j2;
import org.aspectj.apache.bcel.generic.FieldOrMethod;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

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
public class VoucherService extends BaseService<Voucher, VoucherBean, BigInteger> {
    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private VoucherDetailService voucherDetailService;

    protected VoucherService(VoucherRepository repository) {
        super(repository);
    }

    @Override
    protected Voucher toModel(Voucher model, VoucherBean bean) {
        if (model == null) {
            model = new Voucher();
        }
        BeanUtils.copyProperties(bean, model);
        return model;
    }

    @Override
    protected VoucherBean toBean(Voucher model) {
        VoucherBean bean = new VoucherBean();
        BeanUtils.copyProperties(model, bean);
        return bean;
    }

    @Override
    public VoucherBean create(VoucherBean voucherBean) {
        Voucher voucher = new Voucher();

        voucher = toModel(voucher, voucherBean);
        voucher = voucherRepository.save(voucher);

        for (VoucherDetailBean voucherDetailBean : voucherBean.getVoucherDetailList()) {
            voucherDetailService.createDetail(voucherDetailBean, voucher);
        }

        return toBean(voucher);
    }


}
