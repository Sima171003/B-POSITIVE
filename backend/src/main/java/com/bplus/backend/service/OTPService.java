package com.bplus.backend.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OTPService {

    private Map<String , String> otpStorage = new HashMap<>();
    
    public String generateOTP (String email) {

        String otp = String.valueOf((int)(Math.random() * 9000) + 1000);
        otpStorage.put(email,otp);
        return otp;
    }

    public boolean verifyOTP(String email,String otp){
        return otp.equals(otpStorage.get(email));
    }
}
