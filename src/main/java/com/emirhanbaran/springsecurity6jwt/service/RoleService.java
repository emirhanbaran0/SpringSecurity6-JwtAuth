package com.emirhanbaran.springsecurity6jwt.service;

import com.emirhanbaran.springsecurity6jwt.entity.Role;
import com.emirhanbaran.springsecurity6jwt.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
}
