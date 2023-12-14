package com.ym.security_jwt_oauth2_rsa.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JwtService {

    private JwtEncoder jwtEncoder;

    public JwtClaimsSet generateJwtAccessTokenClaims(UserDetails user){
        return JwtClaimsSet.builder()
                .subject(user.getUsername())
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(5, ChronoUnit.MINUTES))
                .issuer("Auth-Service")
                .claim(
                        "scope",
                        user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                                .collect(Collectors.joining(" "))
                        )
                .build();
    }

    public JwtClaimsSet generateJwtRefreshTokenClaims(UserDetails user){
        return JwtClaimsSet.builder()
                .subject(user.getUsername())
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(30, ChronoUnit.MINUTES))
                .issuer("Auth-Service")
                .build();
    }

    public String generateAccessToken(UserDetails user){
        JwtClaimsSet jwtClaimsSet = this.generateJwtAccessTokenClaims(user);
        return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }

    public String generateRefreshToken(UserDetails user){
        JwtClaimsSet jwtClaimsSet = this.generateJwtRefreshTokenClaims(user);
        return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }
}
