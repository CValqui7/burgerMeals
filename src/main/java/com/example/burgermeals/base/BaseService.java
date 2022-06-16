package com.example.burgermeals.base;

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

import com.example.burgermeals.exception.AlreadyExistsException;
import com.example.burgermeals.exception.NotFoundException;
import com.example.burgermeals.rsql.CustomRsqlVisitor;
import com.example.burgermeals.sql.bean.Pagination;
import com.example.burgermeals.sql.bean.SortField;
import com.google.common.reflect.TypeToken;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(value = "transactionManager")
public abstract class BaseService<T, Bean, PK> {
    protected CrudRepository repository;

    private final TypeToken<T> typeTokenModel = new TypeToken<T>(getClass()) {
    };
    private final TypeToken<Bean> typeTokenBean = new TypeToken<Bean>(getClass()) {
    };

    protected T toModel(T t, Bean bean) {
        if (((Class) this.typeTokenModel.getType()).isInstance(bean)) {
            return (T) bean;
        }
        throw new RuntimeException("The model requires that you implement the toModel and toBean methods");
    }

    protected Bean toBean(T model) {
        if (((Class) this.typeTokenBean.getType()).isInstance(model)) {
            return (Bean) model;
        }
        throw new RuntimeException("The model requires that you implement the toModel and toBean methods");
    }

    protected BaseService(CrudRepository repository) {
        this.repository = repository;
    }

    public Bean create(Bean bean) {
        if (existsById(bean)) {
            throw new AlreadyExistsException();
        }

        if (existsByUK(bean)) {
            throw new AlreadyExistsException();
        }

        T t = (T) repository.save(toModel(null, bean));
        return toBean(t);
    }


    public Bean update(Bean bean, PK id) {
        Optional<T> found = repository.findById(id);
        if (!found.isPresent()) {
            throw new NotFoundException();
        }

        T t = toModel(found.get(), bean);
        BeanUtils.copyProperties(t, found.get());

        t = (T) repository.save(found.get());
        return toBean(t);
    }

    public void delete(PK id) {
        Optional<T> found = repository.findById(id);
        if (!found.isPresent()) {
            throw new NotFoundException();
        }

        repository.delete(found.get());
    }

    public Bean getById(PK id) {
        Optional<T> found = repository.findById(id);
        if (!found.isPresent()) {
            throw new NotFoundException();
        }
        return toBean(found.get());
    }

    public List<Bean> getAll() {
        List<T> list = (List<T>) repository.findAll();
        List<Bean> beanList = new ArrayList<>();
        for (T t : list) {
            beanList.add(toBean(t));
        }
        return beanList;
    }

    public void searchByPagination(Pagination<Bean> pagination) {
        if (pagination.getItemsPerPage() == 0) pagination.setItemsPerPage(10);

        Sort sort = null;
        if (pagination.getSortFields() != null) sort = createSort(pagination);

        Pageable pageable = null;
        if (sort != null) {
            pageable = PageRequest.of(pagination.getCurrentPage(), pagination.getItemsPerPage(), sort);
        } else {
            pageable = PageRequest.of(pagination.getCurrentPage(), pagination.getItemsPerPage());
        }

        Specification<T> specification = null;
        if (pagination.getFilterExpression() != null) {
            specification = createSpecification(pagination.getFilterExpression());
        }

        Page<T> page = ((JpaSpecificationExecutor) repository).findAll(specification, pageable);

        pagination.setTotalItems(page.getTotalElements());
        pagination.setTotalPages(page.getTotalPages());

        List<Bean> beanList = new ArrayList<>();
        for (T t : page.getContent()) {
            beanList.add(toBean(t));
        }
        pagination.setData(beanList);
    }

    public List<Bean> searchAllByPagination(Pagination<Bean> pagination) {
        Sort sort = null;
        if (pagination.getSortFields() != null) sort = createSort(pagination);

        Specification<T> specification = null;
        if (pagination.getFilterExpression() != null) {
            specification = createSpecification(pagination.getFilterExpression());
        }

        List<T> page = ((JpaSpecificationExecutor) repository).findAll(specification, sort);

        List<Bean> beanList = new ArrayList<>();
        for (T t : page) {
            beanList.add(toBean(t));
        }
        return beanList;
    }

    private Specification<T> createSpecification(final String filterExpression) {
        Node rootNode = new RSQLParser().parse(filterExpression);
        return rootNode.accept(new CustomRsqlVisitor<>());
    }

    private Sort createSort(Pagination pagination) {
        List<Sort.Order> orderList = new ArrayList<>();
        for (SortField sortField : pagination.getSortFields()) {
            if ("asc".equalsIgnoreCase(sortField.getDirection())) {
                orderList.add(Sort.Order.asc(sortField.getField()).ignoreCase());
            } else {
                orderList.add(Sort.Order.desc(sortField.getField()).ignoreCase());
            }
        }
        return Sort.by(orderList);
    }
    protected boolean existsById(Bean bean) {
        return false;
    }
    protected boolean existsByUK(Bean bean) {
        return false;
    }

    public List<Bean> searchAllByRsql(String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<T> spec = rootNode.accept(new CustomRsqlVisitor<>());

        List<T> list = ((JpaSpecificationExecutor) repository).findAll(spec);
        List<Bean> beanList = new ArrayList<>();
        for (T t : list) {
            beanList.add(toBean(t));
        }
        return beanList;
    }
}
