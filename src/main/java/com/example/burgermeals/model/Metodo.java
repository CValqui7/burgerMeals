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
@Table(name = "METODO")
@NamedQuery(name = "Metodo.findByPK", query = "Select u from Metodo u where u.id = ?1")
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = "id")
public class Metodo implements Serializable {
    @Id
    @Column(name = "ID")
    private BigInteger id;

    @Column(name = "NOMBRE")
    private String nombre;


    @Column(name = "DESCIPCION")
    private String descripcion;

}
