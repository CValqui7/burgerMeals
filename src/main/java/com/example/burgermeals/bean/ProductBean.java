package com.example.burgermeals.bean;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
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
@Getter
@Setter
public class ProductBean implements Serializable {
    @Expose
    private BigInteger id;

    @Expose
    private String name;

    @Expose
    private String description;

    @Expose
    private double precio;

    @Expose
    private double descuento;

    @Expose
    private String status;

    @Expose
    private String image;

    @Expose
    private long version;
}
