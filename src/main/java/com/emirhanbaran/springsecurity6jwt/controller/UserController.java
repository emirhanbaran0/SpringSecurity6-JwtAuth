package com.emirhanbaran.springsecurity6jwt.controller;


import com.emirhanbaran.springsecurity6jwt.annotation.IdGuard;
import com.emirhanbaran.springsecurity6jwt.dto.LoginDto;
import com.emirhanbaran.springsecurity6jwt.dto.RegisterDto;
import com.emirhanbaran.springsecurity6jwt.dto.UserProfileDto;
import com.emirhanbaran.springsecurity6jwt.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Apı for register as a user")
    public ResponseEntity<Object> register (@RequestBody RegisterDto registerDto)
    { return  userService.register(registerDto);}

    @PostMapping("/authenticate")
    @Operation(summary = "Apı for user",
    description = "It returns a jwt if user gives the right username and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Succcesful"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    public String authenticate(@RequestBody LoginDto loginDto)
    { return  userService.authenticate(loginDto);}


    @GetMapping("/profile/{id}")
    @Operation(summary = "Apı for getting uer profile infos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Succcesful"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    @IdGuard(parameterIndex = 0)
    public ResponseEntity<UserProfileDto> getProfileInfo(@PathVariable Long id){
       try {
          return new ResponseEntity<UserProfileDto>(userService.getProfileInfo(id),HttpStatus.OK);
       }catch (Exception e){
           System.out.println(e.getMessage());
           return null;
       }
    }
}
