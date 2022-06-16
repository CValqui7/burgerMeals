package com.example.burgermeals.session;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author Cristian J. Valqui Cabrera
 * @version 15/06/2022
 * <p>
 * Copyright (c) 2022 CValqui, S.A. Todos los derechos reservados.
 * <p>
 * Este software constituye información confidencial y propietaria de CValqui,
 * ("Información Confidencial"). Usted no debe develar dicha Información
 * Confidencial y debe usarla de acuerdo con los términos de aceptación de
 * licencia de uso que firmó con Byte.
 */
@Component
public class InMemorySessionRegistry {

    private static final HashMap<String, String> SESSIONS = new HashMap<>();

    public String registerSession(final String userId) {
        if (userId == null) {
            throw new RuntimeException("Username needs to be provided");
        }
        final String sessionId = generateSessionId();
        SESSIONS.put(sessionId, userId);
        return sessionId;
    }

    public String getUsernameForSession(final String sessionId) {
        return SESSIONS.get(sessionId);
    }
    private String generateSessionId() {
        return new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)));
    }
}
