package com.bplus.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bplus.backend.dto.LoginRequest;
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

    @PostMapping("/register/request-otp")
    public Map<String, Object> requestOTP(@RequestBody RegisterRequest request)
    {
        Map<String, Object> response = new HashMap<>();

        String validation = request.registerValidation();

        if(!"Valid Details".equals(validation)){
            response.put("success", false);
            response.put("message", validation);

            return response;
        }

        String email = request.getEmail();

        if(authService.userAlreadyExists(email)){
            response.put("success", false);
            response.put("message", "User Already Exists");

            return response;
        }

        String otp = otpService.generateOTP(email, request);
        emailService.sendOTP(email, otp);

        response.put("success", true);
        response.put("message", "OTP Sent Successfully");

        return response;
    }

    @PostMapping("/register/verify-otp")
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

        var otpData = otpService.getOTPData(email);

        if(otpData == null){
            response.put("success", false);
            response.put("message", "OTP Not Found");
            return response;
        }

        RegisterRequest request = otpData.getRequest();

        if(request != null){
            authService.submitUserApplication(request);
            otpService.clearOTP(email);

            response.put("success", true);
            response.put("message", "Registered Successfully");
        } 

        return response;
    }


    @PostMapping("/login/request-otp")
    public Map<String, Object> loginRequestOTP(@RequestBody LoginRequest request){

        Map<String, Object> response = new HashMap<>();

        String validation = request.loginValidation();

        if(!"Valid Details".equals(validation)){
            response.put("success", false);
            response.put("message", validation);

            return response;
        }

        String email = request.getEmail();
        String role = request.getRole();

        var exists = authService.validateLogin(email, role);

        if(!exists){
            response.put("success", false);
            response.put("message", "Account Not Exist");

            return response;
        }

        String otp = otpService.generateOTP(email, null);
        emailService.sendOTP(email, otp);

        response.put("success",true);
        response.put("message","OTP Sent Succesfully");

        return response;
    }

    @PostMapping("/login/verify-otp")
    public Map<String, Object> loginVerifyOTP(@RequestBody Map<String, String> body){

        Map<String,Object> response = new HashMap<>();

        String email = body.get("email");
        String otp = body.get("otp");
        String role = body.get("role");

        if(!otpService.verifyOTP(email, otp)){
            response.put("success",false);
            response.put("message", "Invalid OTP");

            return response;
        }

        if(otpService.getOTPData(email) == null){
            response.put("success", false);
            response.put("message", "OTP Expired");

            return response;
        } 

        otpService.clearOTP(email);

        response.put("success", true);
        response.put("message", "Login Succesful");
        response.put("role",role);
        return response;
    }
}
