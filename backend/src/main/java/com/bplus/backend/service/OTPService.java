package com.bplus.backend.service;

import com.bplus.backend.dto.RegisterRequest;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OTPService {

    private Map<String , OTPData> otpStorage = new HashMap<>();
    
    public String generateOTP (String email, RegisterRequest request) {

        String otp = String.valueOf((int)(Math.random() * 9000) + 1000);
        otpStorage.put(email, new OTPData(otp, request));
        return otp;
    }

    public OTPData getOTPData(String email){
        return otpStorage.get(email);
    }
    public boolean verifyOTP(String email,String otp){
        OTPData data = otpStorage.get(email);
        return data != null && data.otp.equals(otp);
    }

    public void clearOTP(String email){
        otpStorage.remove(email);
    }

    public static class OTPData{

        String otp;
        RegisterRequest request;
        String type;

        public OTPData(String otp, RegisterRequest request){
            this.otp = otp;
            this.request = request;
        }

        public String getOTP(){
            return otp;
        }

        public RegisterRequest getRequest(){
            return request;
        }

    }

}
