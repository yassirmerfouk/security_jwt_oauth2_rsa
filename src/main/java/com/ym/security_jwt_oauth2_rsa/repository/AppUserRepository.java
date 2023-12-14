package com.ym.security_jwt_oauth2_rsa.repository;

import com.ym.security_jwt_oauth2_rsa.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
