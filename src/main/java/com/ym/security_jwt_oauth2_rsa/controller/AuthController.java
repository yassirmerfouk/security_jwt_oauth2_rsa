package com.ym.security_jwt_oauth2_rsa.controller;

import com.ym.security_jwt_oauth2_rsa.dto.AuthDTO;
import com.ym.security_jwt_oauth2_rsa.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody AuthDTO authDTO){
        try{
            return new ResponseEntity<>(authService.login(authDTO), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
