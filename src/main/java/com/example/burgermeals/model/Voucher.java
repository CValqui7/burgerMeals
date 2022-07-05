package com.example.burgermeals.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.OffsetDateTime;

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
@Entity
@EqualsAndHashCode
@Table(name = "FFS_VOUCHER")
@Getter @Setter @ToString(exclude = "id")
@NamedQuery(name = "Voucher.findByPK", query = "Select u from Voucher u where u.id = ?1")
public class Voucher implements Serializable {

    @Id
    @Column(name = "FFSV_ID")
    @TableGenerator(name = "FFS_VOUCHER_GENERATOR", table = "SEQUENCE_TABLE", pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_COUNT", pkColumnValue = "FFS_VOUCHER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FFS_VOUCHER_GENERATOR")
    private BigInteger id;

    @Column(name = "FFSV_CREATION")
    private OffsetDateTime creationDate;

    @Column(name = "FFSV_TOTAL_PRICE")
    private BigDecimal totalPrice;

}
