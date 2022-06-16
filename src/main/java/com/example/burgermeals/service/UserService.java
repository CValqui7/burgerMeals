package com.example.burgermeals.service;

import com.example.burgermeals.base.BaseService;
import com.example.burgermeals.bean.ProductBean;
import com.example.burgermeals.bean.UserBean;
import com.example.burgermeals.exception.NotFoundException;
import com.example.burgermeals.model.Product;
import com.example.burgermeals.model.User;
import com.example.burgermeals.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
@Service
@Transactional
@Log4j2
public class UserService extends BaseService<User, UserBean, String> implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    protected UserService(UserRepository repository) {
        super(repository);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findById(username);
        if(!userOptional.isPresent()){
            throw new NotFoundException();
        }
        User user = userOptional.get();
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ADMIN"));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), roles);
        return userDetails;
    }
}
