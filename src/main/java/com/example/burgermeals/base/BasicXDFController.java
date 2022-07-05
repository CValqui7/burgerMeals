package com.example.burgermeals.base;

import com.example.burgermeals.sql.bean.Pagination;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Cristian J. Valqui Cabrera
 * @version 10/06/2022
 * <p>
 * Copyright (c) 2022 CValqui, S.A. Todos los derechos reservados.
 * <p>
 * Este software constituye información confidencial y propietaria de CValqui,
 * ("Información Confidencial"). Usted no debe develar dicha Información
 * Confidencial y debe usarla de acuerdo con los términos de aceptación de
 * licencia de uso que firmó con Byte.
 */
@Log4j2
public abstract class BasicXDFController<BEAN, PK> {
    protected BaseService service;

    @Autowired
    protected Gson gson;

    public BasicXDFController(BaseService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> update(@PathVariable("id") PK id, @RequestBody BEAN bean, HttpServletRequest req) {
        try {
            service.update(bean, id);
        } catch (ObjectOptimisticLockingFailureException e) {
            BEAN actualBean = (BEAN) service.getById(id);
            return new ResponseEntity<>(gson.toJson(actualBean), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(gson.toJson(bean), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody BEAN bean, HttpServletRequest req) {
        bean = (BEAN) service.create(bean);

        return new ResponseEntity<>(gson.toJson(bean), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> findOne(@PathVariable("id") PK id,
                                          HttpServletRequest req) {

        BEAN bean = (BEAN) service.getById(id);
        return new ResponseEntity<>(gson.toJson(bean), HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") PK id, HttpServletRequest req) {
        service.delete(id);
        return new ResponseEntity<>(gson.toJson(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<String> findAll(HttpServletRequest req) {
        List<BEAN> ts = service.getAll();
        return new ResponseEntity<>(gson.toJson(ts), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    @ResponseBody
    public ResponseEntity<String> searchByRsql(@RequestParam(value = "search") String search) {
        List<BEAN> ts = service.searchAllByRsql(search);
        return new ResponseEntity<>(gson.toJson(ts), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/page")
    @ResponseBody
    public ResponseEntity<String> findAllByPagination(@RequestBody Pagination<BEAN> pagination) {
        service.searchByPagination(pagination);

        return new ResponseEntity<>(gson.toJson(pagination), HttpStatus.OK);
    }
}