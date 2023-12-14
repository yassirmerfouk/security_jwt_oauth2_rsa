package com.ym.security_jwt_oauth2_rsa.repository;

import com.ym.security_jwt_oauth2_rsa.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
}
