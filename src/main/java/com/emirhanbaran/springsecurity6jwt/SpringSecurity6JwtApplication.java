package com.emirhanbaran.springsecurity6jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringSecurity6JwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurity6JwtApplication.class, args);
    }

}
