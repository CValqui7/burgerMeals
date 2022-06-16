package com.example.burgermeals.model;

import com.example.burgermeals.converter.ItemStateConverter;
import com.example.burgermeals.enums.ItemState;
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
@Table(name = "FFS_PRODUCT")
@NamedQuery(name = "Product.findByPK", query = "Select u from Product u where u.id = ?1")
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = "id")
public class Product implements Serializable {
    @Id
    @Column(name = "FFSP_ID")
    @TableGenerator(name = "FFS_PRODUCT_GENERATOR", table ="SEQUENCE_TABLE", pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_COUNT", pkColumnValue = "FFS_PRODUCT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FFS_PRODUCT_GENERATOR")
    private BigInteger id;

    @Column(name = "FFSP_NAME")
    private String name;

    @Column(name = "FFSP_DESCR")
    private String description;

    @Column(name = "FFSP_PRICE")
    private Double precio;

    @Column(name = "FFSP_DISCOUNT")
    private Double descuento;

    @Column(name = "FFSP_STATE")
    @Convert(converter = ItemStateConverter.class)
    private ItemState status;

    @Column(name = "FFSP_IMAGE")
    private String image;

    @Version
    @Column(name = "VERSION")
    @Basic(optional = false)
    private long version;
}
