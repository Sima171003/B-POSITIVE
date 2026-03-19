package com.bplus.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bplus.backend.dto.RegisterRequest;
import com.bplus.backend.service.AuthService;
import com.bplus.backend.service.EmailService;
import com.bplus.backend.service.OTPService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private OTPService otpService;

@PostMapping("/request-otp")
public Map<String, Object> requestOTP(@RequestBody RegisterRequest request)
{
    Map<String, Object> response = new HashMap<>();

    String validation = request.registerValidation();

    if(!"Valid details".equals(validation)){
        response.put("success", false);
        response.put("message", validation);
        return response;
    }

    String email = request.getEmail();

    String otp = otpService.generateOTP(email, request);
    emailService.sendOTP(email, otp);

    response.put("success", true);
    response.put("message", "OTP Sent Successfully");

    return response;
}

    // @PostMapping("/login/request-otp")
    // public String requestloginOTP(@RequestBody LoginRequest request){
        
    //     String validation = request.loginValidation();

    //     if(!"Valid details".equals(validation)){
    //         return validation;
    //     }

    //     String email = request.getEmail();
    //     String role = request.getRole();

    //     UserApplication user = authService.getUserByEmail(email);

    //     if(user == null){
    //         return "User Not Found. Please Register First";
    //     }

    //     if(!user.getRole().equals(role)) {
    //         return "Invalid Role for this user";
    //     }

    //     String otp = otpService.generateOTP(email, null);
    //     emailService.sendOTP(email, otp);

    //     return "OTP Sent Successfully";
    // }

   
    @PostMapping("/verify-otp")
public Map<String, Object> verifyOTP(@RequestBody Map<String, String> body)
{
    Map<String, Object> response = new HashMap<>();

    String email = body.get("email");
    String otp = body.get("otp");

    if(!otpService.verifyOTP(email, otp)){
        response.put("success", false);
        response.put("message", "Invalid OTP");
        return response;
    }

    RegisterRequest request = otpService.getOTPData(email).getRequest();

    if(request != null){
        authService.submitUserApplication(request);
        otpService.clearOTP(email);

        response.put("success", true);
        response.put("message", "Registered Successfully");
    } else {
        otpService.clearOTP(email);

        response.put("success", true);
        response.put("message", "Login Successful");
    }

    return response;
}
}
