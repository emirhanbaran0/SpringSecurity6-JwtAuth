package com.emirhanbaran.springsecurity6jwt.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {


    @GetMapping("/hello")
    @Operation(summary = "Apı for admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String sayHello ()
    { return "Hello" ;}

}
