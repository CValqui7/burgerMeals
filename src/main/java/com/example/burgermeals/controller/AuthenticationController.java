package com.example.burgermeals.controller;

import com.example.burgermeals.dto.ResponseDTO;
import com.example.burgermeals.dto.UserDTO;
import com.example.burgermeals.model.User;
import com.example.burgermeals.session.InMemorySessionRegistry;
import com.google.gson.Gson;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping("/api")
public class AuthenticationController {
    @Autowired
    protected Gson gson;
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    public InMemorySessionRegistry sessionRegistry;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO user){
        manager.authenticate(
          new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
    final String sessionID = sessionRegistry.registerSession(user.getUsername());

    ResponseDTO responseDto = new ResponseDTO();
    responseDto.setSessionId(sessionID);


        return new ResponseEntity<>(gson.toJson(responseDto), HttpStatus.OK);
    }
}
