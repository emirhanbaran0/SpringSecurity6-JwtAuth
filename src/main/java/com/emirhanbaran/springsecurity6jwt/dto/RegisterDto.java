package com.emirhanbaran.springsecurity6jwt.dto;


public record RegisterDto(
        String email,
        String username,
        String password
) {}
