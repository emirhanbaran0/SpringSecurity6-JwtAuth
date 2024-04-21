package com.emirhanbaran.springsecurity6jwt.repository;

import com.emirhanbaran.springsecurity6jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

     User findByUsername(String username);
     boolean existsByUsername(String username);
}
