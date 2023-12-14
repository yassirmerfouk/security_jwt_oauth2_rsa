package com.ym.security_jwt_oauth2_rsa.service;

import com.ym.security_jwt_oauth2_rsa.config.GrantType;
import com.ym.security_jwt_oauth2_rsa.dto.AuthDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthService {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private JwtDecoder jwtDecoder;
    private UserDetailsService userDetailsService;

    public Map<String, String> login(AuthDTO authDTO) {
        Map<String, String> tokens = new HashMap<>();
        UserDetails user = null;
        if (authDTO.getGrantType() == GrantType.PASSWORD) {
           Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword())
            );
           user = (UserDetails) authentication.getPrincipal();
        }else if(authDTO.getGrantType() == GrantType.REFRESH_TOKEN){
          Jwt jwt = jwtDecoder.decode(authDTO.getRefreshToken());
          user = userDetailsService.loadUserByUsername(jwt.getSubject());
        }else{
            throw new RuntimeException("Grant Type not found");
        }
        tokens.put("accessToken", jwtService.generateAccessToken(user));
        if (authDTO.isWithRefreshToken())
            tokens.put("refreshToken", jwtService.generateRefreshToken(user));
        return tokens;
    }
}
