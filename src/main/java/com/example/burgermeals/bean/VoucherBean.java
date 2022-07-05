package com.example.burgermeals.bean;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.List;

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
@Getter
@Setter
public class VoucherBean implements Serializable {
    @Expose private BigInteger id;
    @Expose private OffsetDateTime creationDate;
    @Expose private BigDecimal totalPrice;
    @Expose private List<VoucherDetailBean> voucherDetailList;
}
