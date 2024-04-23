package com.emirhanbaran.springsecurity6jwt.service;

import com.emirhanbaran.springsecurity6jwt.dto.BearerToken;
import com.emirhanbaran.springsecurity6jwt.dto.LoginDto;
import com.emirhanbaran.springsecurity6jwt.dto.RegisterDto;
import com.emirhanbaran.springsecurity6jwt.entity.Role;
import com.emirhanbaran.springsecurity6jwt.entity.RoleName;
import com.emirhanbaran.springsecurity6jwt.entity.User;
import com.emirhanbaran.springsecurity6jwt.repository.RoleRepository;
import com.emirhanbaran.springsecurity6jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager ;
    private final UserRepository userRepository ;
    private final RoleRepository roleRepository ;
    private final PasswordEncoder passwordEncoder ;
    private final JwtService jwtService ;




    public User saverUser(User user) {
        return userRepository.save(user);
    }


    public ResponseEntity<Object> register(RegisterDto registerDto) {
        if(userRepository.existsByUsername(registerDto.username()))
        { return  new ResponseEntity<>("email is already taken !", HttpStatus.SEE_OTHER); }
        else
        { User user = new User();
            user.setEmail(registerDto.email());
            user.setPassword(passwordEncoder.encode(registerDto.password()));
            user.setUsername(registerDto.username());
            Role adminRole = roleRepository.findByRoleName(RoleName.ROLE_ADMIN);
            Role userRole = roleRepository.findByRoleName(RoleName.ROLE_USER);
            Set<Role> roles = new HashSet<>(Arrays.asList(adminRole,userRole));
            user.setRoles(roles);

            try {
                userRepository.save(user);
            }catch (Exception e){
                e.printStackTrace();
            }
            String token = jwtService.generateToken(registerDto.username(),roles.stream().map(Role::getRoleName).collect(Collectors.toSet()));
            return new ResponseEntity<>(new BearerToken(token , "Bearer "),HttpStatus.OK);

        }
    }

    public String authenticate(LoginDto loginDto) {
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.username(),
                        loginDto.password()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByUsername(authentication.getName());
        Set<String> rolesNames = new HashSet<>();
        user.getRoles().forEach(r-> rolesNames.add(r.getRoleName()));
        return jwtService.generateToken(user.getUsername(),rolesNames);
    }

}
