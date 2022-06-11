package com.example.burgermeals.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
@Entity
@Table(name="FFS_PRODUCT_TYPE")
@NamedQuery(name="ProductType.findByPK", query = "Select u from ProductType u where u.id = ?1")
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = "id")
public class ProductType implements Serializable {
    @Id
    @Column(name="FFSPT_ID")
    private BigInteger id;

    @Column(name="FFSPT_NAME")
    private String name;

    @Column(name="FFSPT_DESCR")
    private String description;
}
