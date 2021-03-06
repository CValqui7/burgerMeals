package com.example.burgermeals.repository;

import com.example.burgermeals.model.User;
import org.springframework.data.repository.CrudRepository;

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
public interface UserRepository extends CrudRepository<User, String> {
}
