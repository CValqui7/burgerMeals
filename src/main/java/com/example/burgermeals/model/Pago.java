package com.example.burgermeals.model;

import com.example.burgermeals.converter.ItemStateConverter;
import com.example.burgermeals.enums.ItemState;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

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

@Entity
@Table(name = "PAGO")
@NamedQuery(name = "Pago.findByPK", query = "Select u from Pago u where u.id = ?1")
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = "id")
public class Pago implements Serializable{
    @Id
    @Column(name = "ID")
    @TableGenerator(name = "FFS_PRODUCT_GENERATOR", table = "SEQUENCE_TABLE", pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_COUNT", pkColumnValue = "FFS_PRODUCT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FFS_PRODUCT_GENERATOR")
    private BigInteger id;

    @Column(name = "MONTO")
    private BigDecimal monto;


    @Column(name = "STATUS")
    @Convert(converter = ItemStateConverter.class)
    private ItemState status;


    @ManyToOne(optional = false)
    @JoinColumn(name = "METODO", referencedColumnName = "ID", nullable = false)
    private Metodo metodo;

}
