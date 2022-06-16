package com.example.burgermeals.bean;

import com.example.burgermeals.converter.ItemStateConverter;
import com.example.burgermeals.enums.ItemState;
import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Version;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author Cristian J. Valqui Cabrera
 * @version 11/06/2022
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
public class UserBean implements Serializable {
    @Expose private BigInteger id;

    @Expose private String name;

    @Expose private String firstLastName;

    @Expose private Double secondLastName;

    @Expose private Double email;

    @Expose private String state;

    @Expose private String address;

    @Expose private String password;

    @Expose private long version;
}
