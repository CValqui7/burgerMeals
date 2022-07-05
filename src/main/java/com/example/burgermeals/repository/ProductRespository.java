package com.example.burgermeals.repository;

import com.example.burgermeals.enums.ItemState;
import com.example.burgermeals.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

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
public interface ProductRespository extends CrudRepository<Product, BigInteger> {

    List<Product> findAllByStatus(ItemState itemState);
}
