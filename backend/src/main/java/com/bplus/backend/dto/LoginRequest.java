package com.bplus.backend.dto;

public class LoginRequest {
    private String role;
    private String email;

    public LoginRequest()
    {

    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role.toLowerCase().trim();
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    
    public String loginValidation()
    {

        if(role == null || role.trim().isEmpty() || email == null || email.trim().isEmpty())
        {
            return "Please fill up all details to proceed";
        }

        return "Valid details";

    }


}
