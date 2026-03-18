package com.bplus.backend.controller;

import com.bplus.backend.dto.RegisterRequest;
import com.bplus.backend.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequest request)
    {
        return authService.submitUserApplication(request);
    }

}
