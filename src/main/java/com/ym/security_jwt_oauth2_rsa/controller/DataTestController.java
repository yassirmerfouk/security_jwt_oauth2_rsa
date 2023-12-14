package com.ym.security_jwt_oauth2_rsa.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class DataTestController {

    @GetMapping("/test1")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public Map<String, Object> getTestData1(Authentication authentication){
        Map<String,Object> data = new HashMap<>();
        data.put("data", "data test1");
        data.put("authentication", authentication.getAuthorities());
        return data;
    }

    @GetMapping("/test2")
    @PreAuthorize("hasAnyAuthority('SCOPE_USER','SCOPE_ADMIN')")
    public Map<String, Object> getTestData2(Authentication authentication){
        Map<String,Object> data = new HashMap<>();
        data.put("data", "data test2");
        data.put("authentication", authentication.getAuthorities());
        return data;
    }

    @GetMapping("/test3")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public Map<String, Object> getTestData3(Authentication authentication){
        Map<String,Object> data = new HashMap<>();
        data.put("data", "data test3");
        data.put("authentication", authentication.getAuthorities());
        return data;
    }
}
