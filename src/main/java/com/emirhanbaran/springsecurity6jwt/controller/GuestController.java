package com.emirhanbaran.springsecurity6jwt.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guest")
@RequiredArgsConstructor
public class GuestController {

    @GetMapping("/hi")
    @Operation(summary = "Apı for guest")
    public String sayHello ()
    { return "hi" ;}
}
