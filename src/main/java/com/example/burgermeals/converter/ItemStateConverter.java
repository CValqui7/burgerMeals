package com.example.burgermeals.converter;

import com.example.burgermeals.enums.ItemState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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
@Converter
public class ItemStateConverter implements AttributeConverter<ItemState, String> {
    @Override
    public String convertToDatabaseColumn(ItemState itemState) {
        if(itemState == null) return null;
        return itemState.getName();
    }

    @Override
    public ItemState convertToEntityAttribute(String s) {
        return ItemState.fromString(s);
    }
}
