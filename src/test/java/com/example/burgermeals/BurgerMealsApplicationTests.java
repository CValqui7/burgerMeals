package com.example.burgermeals;

import com.example.burgermeals.enums.ItemState;
import com.example.burgermeals.model.User;
import com.example.burgermeals.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.math.BigInteger;

@SpringBootTest
class BurgerMealsApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    void contextLoads() {
    }

    @Test void createUser() {
        User us = new User();
        BigInteger bigInteger = BigInteger.valueOf(1);
        us.setId("73030112");
        us.setName("Cristian");
        us.setAddress("Dirección");
        us.setEmail("cvalqui@gmail.com");
        us.setFirstLastName("Valqui");
        us.setSecondLastName("Cabrera");
        us.setState(ItemState.ACTIVE);
        us.setPassword(encoder.encode("123"));
        User usuario = userRepository.save(us);

    }

}
