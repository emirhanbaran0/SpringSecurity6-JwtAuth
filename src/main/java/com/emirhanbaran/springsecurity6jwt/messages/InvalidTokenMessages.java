package com.emirhanbaran.springsecurity6jwt.messages;

import lombok.Getter;

@Getter
public enum InvalidTokenMessages {

    INVALID_JWT_SIGNATURE("Invalid JWT Signature."),
    INVALID_JWT_TOKEN("Invalid JWT token."),
    EXPIRED_JWT_TOKEN("Expired JWT token."),
    UNSUPPORTED_JWT_TOKEN("Unsupported JWT token."),
    ILLEGAL_ARGUMENT_EXCEPTION("JWT token compact of handler are invalid.");


    private final String description;

    InvalidTokenMessages(String description) {
        this.description = description;
    }

}
