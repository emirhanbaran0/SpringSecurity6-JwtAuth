package com.emirhanbaran.springsecurity6jwt.dto;


import com.emirhanbaran.springsecurity6jwt.entity.Gender;

public record RegisterDto(
        String email,
        String username,
        String password,
        Short age,
        Gender gender
) {}
