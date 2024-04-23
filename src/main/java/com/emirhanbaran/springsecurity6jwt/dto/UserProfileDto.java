package com.emirhanbaran.springsecurity6jwt.dto;

import com.emirhanbaran.springsecurity6jwt.entity.Gender;
import lombok.AllArgsConstructor;


public record UserProfileDto(
        String username,
        String email,
        Short age,
        Gender gender

) {
}
