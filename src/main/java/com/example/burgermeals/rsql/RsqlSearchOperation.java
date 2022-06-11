package com.example.burgermeals.rsql;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import cz.jirutka.rsql.parser.ast.RSQLOperators;

import java.util.Arrays;

/**
 * @author Cristian J. Valqui Cabrera
 * @version 10/06/2022
 * <p>
 * Copyright (c) 2022 CValqui, S.A. Todos los derechos reservados.
 * <p>
 * Este software constituye información confidencial y propietaria de CValqui,
 * ("Información Confidencial"). Usted no debe develar dicha Información
 * Confidencial y debe usarla de acuerdo con los términos de aceptación de
 * licencia de uso que firmó con Byte.
 */
public enum RsqlSearchOperation {
    EQUAL(RSQLOperators.EQUAL), NOT_EQUAL(RSQLOperators.NOT_EQUAL), GREATER_THAN(RSQLOperators.GREATER_THAN), GREATER_THAN_OR_EQUAL(RSQLOperators.GREATER_THAN_OR_EQUAL), LESS_THAN(RSQLOperators.LESS_THAN), LESS_THAN_OR_EQUAL(
            RSQLOperators.LESS_THAN_OR_EQUAL), IN(RSQLOperators.IN), NOT_IN(RSQLOperators.NOT_IN);

    private ComparisonOperator operator;

    RsqlSearchOperation(final ComparisonOperator operator) {
        this.operator = operator;
    }

    public static RsqlSearchOperation getSimpleOperator(final ComparisonOperator operator) {
        return Arrays.stream(values())
                .filter(operation -> operation.getOperator() == operator)
                .findAny().orElse(null);
    }

    public ComparisonOperator getOperator() {
        return operator;
    }
}
