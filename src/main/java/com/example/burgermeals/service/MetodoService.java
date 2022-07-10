package com.example.burgermeals.service;

import com.example.burgermeals.base.BaseService;
import com.example.burgermeals.bean.MetodoBean;
import com.example.burgermeals.model.Metodo;
import com.example.burgermeals.repository.MetodoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

public class MetodoService extends BaseService<Metodo, MetodoBean, MetodoRepository> {
    protected MetodoService(MetodoRepository repository) {
        super(repository);
    }


    public MetodoBean toBean(Metodo model) {
        MetodoBean metodoBean = new MetodoBean();
        BeanUtils.copyProperties(model, metodoBean);
        return metodoBean;
    }
}
