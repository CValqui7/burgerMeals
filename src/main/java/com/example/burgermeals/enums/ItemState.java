package com.example.burgermeals.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

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
public enum ItemState {
    ACTIVE("AC"), INACTIVE("IN");

    private final String name;

    private static final Map<String, ItemState> map = new HashMap<>();

    static {
        for (ItemState itemState : ItemState.values()) {
            map.put(itemState.name, itemState);
        }
    }

    ItemState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ItemState fromString(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        }
        throw new NoSuchElementException(name + "not found");
    }

}
