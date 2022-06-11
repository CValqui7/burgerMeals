package com.example.burgermeals.sql.bean;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
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
@Getter @Setter
public class Pagination<T> implements Serializable {
    @Expose
    private int itemsPerPage;
    @Expose
    private int currentPage;

    @Expose
    private long totalItems;

    @Expose
    private int totalPages;

    @Expose
    private String filterExpression;

    @Expose
    private SortField[] sortFields;

    @Expose
    private List<T> data;
}
