package com.bplus.backend.controller;

import com.bplus.backend.dto.LoginRequest;
import com.bplus.backend.dto.RegisterRequest;
import com.bplus.backend.model.UserApplication;
import com.bplus.backend.service.AuthService;
import com.bplus.backend.service.EmailService;
import com.bplus.backend.service.OTPService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

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
    public String requestOTP(@RequestBody RegisterRequest request)
    {
        String validation = request.registerValidation();

        if(!"Valid details".equals(validation)){
            return validation;
        }

        String email = request.getEmail();

        String otp = otpService.generateOTP(email, request);
        emailService.sendOTP(email, otp);

        return "OTP Sent Successfully";
    }

    @PostMapping("/login/request-otp")
    public String requestloginOTP(@RequestBody LoginRequest request){
        
        String validation = request.loginValidation();

        if(!"Valid details".equals(validation)){
            return validation;
        }

        String email = request.getEmail();
        String role = request.getRole();

        UserApplication user = authService.getUserByEmail(email);

        if(user == null){
            return "User Not Found. Please Register First";
        }

        if(!user.getRole().equals(role)) {
            return "Invalid Role for this user";
        }

        String otp = otpService.generateOTP(email, null);
        emailService.sendOTP(email, otp);

        return "OTP Sent Successfully";
    }

    @PostMapping("/verify-otp")
    public String verifyOTP(@RequestBody Map<String, String> body)
    {
        String email = body.get("email");
        String otp = body.get("otp");

        if(!otpService.verifyOTP(email, otp)){
            return "Invalid OTP";
        }

        RegisterRequest request = otpService.getOTPData(email).getRequest();

        if(request != null){
            authService.submitUserApplication(request);

            otpService.clearOTP(email);

            return "Registered Successfully";
        }else{
            otpService.clearOTP(email);

            return "Login Successfull";
        }
    }
}
