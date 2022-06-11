package com.example.burgermeals.rsql;

import com.example.burgermeals.rsql.exception.RsqlException;
import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class GenericRsqlSpecification<T> implements Specification<T> {

    private String property;
    private ComparisonOperator operator;
    private List<String> arguments;

    public GenericRsqlSpecification(final String property, final ComparisonOperator operator, final List<String> arguments) {
        super();
        this.property = property;
        this.operator = operator;
        this.arguments = arguments;

        decimalFormat.setRoundingMode(java.math.RoundingMode.HALF_UP);
        decimalFormat.setParseBigDecimal(true);
    }


//    public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
//        if ("partyList.id.partyId".equals(property)) {
//            Join profileJoin = root.join("partyList");
//            this.property = "id.partyId";
//            return toPredicateRoot(profileJoin, query, builder);
//        }
//        return toPredicate(root, query, builder);
//    }

    @Override
    public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
        return toPredicateFrom(root, query, builder, property);
    }

    public boolean doesClassContainProperty(Class<?> genericClass, String fieldName) {
        return Arrays.stream(genericClass.getDeclaredFields()).anyMatch(f -> f.getName().equals(fieldName));
    }

    public Predicate toPredicateFrom(final From<T, T> root, final CriteriaQuery<?> query, final CriteriaBuilder builder, String property) {
        Pattern pattern = Pattern.compile("([^.]+)");
        Matcher matcher = pattern.matcher(property);
        if (matcher.find())
        {
            String key = matcher.group(1);
            if (key.endsWith("Collection")) {
                property = property.substring(key.length() + 1);
                Join profileJoin = root.join(key);
                return toPredicateFrom(profileJoin, query, builder, property);
            }
        }

        final List<Object> args = castArguments(root, property);
        final Object argument = args.get(0);

        Path<T> path = (Path<T>) getPath(root, property);
        switch (RsqlSearchOperation.getSimpleOperator(operator)) {

            case EQUAL: {
//                if (property.startsWith("solicitudDetallePortInCollection")) {
//                    Join<SolicitudPortIn, SolicitudDetallePortIn> profileJoin = root.join("solicitudDetallePortInCollection");
//                    return builder.equal(profileJoin.get("solicitudDetallePortInPK.numeroTelefono"), argument);
//                } else {
                if (argument instanceof String) {

                    //                    String argumentString = argument.toString();
                    //                    if (argumentString.contains("*")) {
                    //                        return builder.like((Path<String>) path, argumentString.replace('*', '%'));
                    //                    } else {
                    //                        return builder.equal(path, argumentString);
                    //                    }
                    String argumentString = argument.toString() + "%";
                    return builder.like((Path<String>) path, argumentString);
                } else if (argument == null) {
                    return builder.isNull(path);
                } else {
                    return builder.equal(path, argument);
                }
//                }
            }
            case NOT_EQUAL: {
                if (argument instanceof String) {
//                    String argumentString = argument.toString();
//                    if (argumentString.contains("*")) {
//                        return builder.notLike((Path<String>)path, argumentString.replace('*', '%'));
//                    } else {
//                        return builder.notEqual(path, argumentString);
//                    }
                    String argumentString = argument.toString() + "%";
                    return builder.notLike((Path<String>) path, argumentString);
                } else if (argument == null) {
                    return builder.isNotNull(path);
                } else {
                    return builder.notEqual(path, argument);
                }
            }
            case GREATER_THAN: {
                if (argument instanceof OffsetDateTime) {
                    return builder.greaterThan((Path<OffsetDateTime>) path, (OffsetDateTime) argument);
                } else if (argument instanceof LocalDateTime) {
                    return builder.greaterThan((Path<LocalDateTime>) path, (LocalDateTime) argument);
                } else if (argument instanceof LocalDate) {
                    return builder.greaterThan((Path<LocalDate>) path, (LocalDate) argument);
                } else if (argument instanceof Date) {
                    return builder.greaterThan((Path<Date>) path, (Date) argument);
                }
                return builder.greaterThan((Path<String>) path, argument.toString());
            }
            case GREATER_THAN_OR_EQUAL: {
                if (argument instanceof OffsetDateTime) {
                    return builder.greaterThanOrEqualTo((Path<OffsetDateTime>) path, (OffsetDateTime) argument);
                } else if (argument instanceof LocalDateTime) {
                    return builder.greaterThanOrEqualTo((Path<LocalDateTime>) path, (LocalDateTime) argument);
                } else if (argument instanceof LocalDate) {
                    return builder.greaterThanOrEqualTo((Path<LocalDate>) path, (LocalDate) argument);
                } else if (argument instanceof Date) {
                    return builder.greaterThanOrEqualTo((Path<Date>) path, (Date) argument);
                }
                return builder.greaterThanOrEqualTo((Path<String>) path, argument.toString());
            }
            case LESS_THAN: {
                if (argument instanceof OffsetDateTime) {
                    return builder.lessThan((Path<OffsetDateTime>) path, (OffsetDateTime) argument);
                } else if (argument instanceof LocalDateTime) {
                    return builder.lessThan((Path<LocalDateTime>) path, (LocalDateTime) argument);
                } else if (argument instanceof LocalDate) {
                    return builder.lessThan((Path<LocalDate>) path, (LocalDate) argument);
                } else if (argument instanceof Date) {
                    return builder.lessThan((Path<Date>) path, (Date) argument);
                }
                return builder.lessThan((Path<String>) path, argument.toString());
            }
            case LESS_THAN_OR_EQUAL: {
                if (argument instanceof OffsetDateTime) {
                    return builder.lessThanOrEqualTo((Path<OffsetDateTime>) path, (OffsetDateTime) argument);
                } else if (argument instanceof LocalDateTime) {
                    return builder.lessThanOrEqualTo((Path<LocalDateTime>) path, (LocalDateTime) argument);
                } else if (argument instanceof LocalDate) {
                    return builder.lessThanOrEqualTo((Path<LocalDate>) path, (LocalDate) argument);
                } else if (argument instanceof Date) {
                    return builder.lessThanOrEqualTo((Path<Date>) path, (Date) argument);
                }
                return builder.lessThanOrEqualTo((Path<String>) path, argument.toString());
            }
            case IN:
                return path.in(args);
            case NOT_IN:
                return builder.not(path.in(args));
        }

        throw new RsqlException(String.format("Operator \"%s\" is not valid", operator));
    }

    DateTimeFormatter zoneDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DecimalFormat decimalFormat = new DecimalFormat("###0.00");

    // === private
    private List<Object> castArguments(final From<T, T> root, String property) {
        final List<Object> args = new ArrayList<>();
        final Class<? extends Object> type = getPath(root, property).getJavaType();

        for (final String argument : arguments) {
            if (type.equals(Integer.class)) {
                args.add(Integer.parseInt(argument));
            } else if (type.equals(Long.class)) {
                args.add(Long.parseLong(argument));
            } else if (type.equals(Boolean.class)) {
                args.add(Boolean.parseBoolean(argument));
            } else if (type.equals(BigInteger.class)) {
                args.add(new BigInteger(argument));
            } else if (type.equals(BigDecimal.class)) {
                try {
                    args.add(decimalFormat.parse(argument));
                } catch (ParseException e) {
                    throw new RsqlException(String.format("Argument \"%s\" not valid Decimal", argument));
                }
            } else if (type.equals(OffsetDateTime.class)) {
                try {
                    args.add(OffsetDateTime.parse(argument, zoneDateTimeFormatter));
                } catch (Exception e) {
                    throw new RsqlException(String.format("Argument \"%s\" is not valid ZoneDateTime", argument));
                }
//            } else if (type.equals(LocalDateTime.class)) {
//                try {
//                    args.add(LocalDateTime.parse(argument, dateTimeFormatter));
//                } catch (Exception e) {
//                    throw new RsqlException(String.format("Argument \"%s\" is not valid DateTime", argument));
//                }
            } else if (type.equals(LocalDate.class)) {
                try {
                    args.add(LocalDate.parse(argument, dateFormatter));
                } catch (Exception e) {
                    throw new RsqlException(String.format("Argument \"%s\" is not valid LocalDate", argument));
                }
            } else if (type.equals(Date.class)) {
                try {
                    args.add(simpleDateFormat.parse(argument));
                } catch (Exception e) {
                    throw new RsqlException(String.format("Argument \"%s\" is not valid Date", argument));
                }
            } else if(type.isEnum()) {
                try {
                    Method m = type.getMethod("fromString", String.class);
                    Object result = m.invoke(null, argument);
                    args.add(result);
                } catch (Exception e) {
                    throw new RsqlException(String.format("Argument \"%s\" is not valid Enum %s", argument, type.getSimpleName()));
                }
            } else {
                //args.add(argument.toLowerCase());
                args.add(argument);
            }
        }

        return args;
    }

    private Path<?> getPath(final From<T, T> root, String property) {
        String[] fn = property.split("\\.");
        Path<T> path = root;
        for (String part : fn) {
            path = path.get(part);
        }

        return path;
    }

}
