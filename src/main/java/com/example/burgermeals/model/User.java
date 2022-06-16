package com.example.burgermeals.model;

import com.example.burgermeals.converter.ItemStateConverter;
import com.example.burgermeals.enums.ItemState;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
@Entity
@Table(name="FFS_USER")
@NamedQuery(name="User.findByPK", query = "Select u from User u where u.id = ?1")
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = "id")
public class User {
    @Id
    @Column(name="FFSU_ID")
    private String id;

    @Column(name="FFSU_NAME")
    private String name;

    @Column(name="FFSU_FLASTNAME")
    private String firstLastName;

    @Column(name="FFSU_SLASTNAME")
    private String secondLastName;

    @Column(name="FFSU_EMAIL")
    private String email;

    @Column(name="FFSU_STATE")
    @Convert(converter = ItemStateConverter.class)
    private ItemState state;

    @Column(name="FFSU_ADDRESS")
    private String address;

    @Column(name="FFSU_PASSWORD")
    private String password;

    @Version
    @Column(name="VERSION")
    @Basic(optional = false)
    private long version;
}
