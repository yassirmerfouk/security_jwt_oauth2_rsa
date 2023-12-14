package com.ym.security_jwt_oauth2_rsa.dto;

import com.ym.security_jwt_oauth2_rsa.config.GrantType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class AuthDTO {

    private String username;
    private String password;
    private boolean withRefreshToken;
    private String refreshToken;
    private GrantType grantType;
}
