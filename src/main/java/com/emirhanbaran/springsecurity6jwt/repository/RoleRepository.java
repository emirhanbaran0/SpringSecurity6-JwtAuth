package com.emirhanbaran.springsecurity6jwt.repository;

import com.emirhanbaran.springsecurity6jwt.entity.Role;
import com.emirhanbaran.springsecurity6jwt.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(RoleName roleName);
}
