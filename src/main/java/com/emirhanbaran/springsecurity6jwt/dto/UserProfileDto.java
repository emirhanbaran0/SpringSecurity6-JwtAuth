package com.emirhanbaran.springsecurity6jwt.dto;

import lombok.AllArgsConstructor;


public record UserProfileDto(
        String username,
        String email,
        Short age,
        Boolean gender

) {
}
