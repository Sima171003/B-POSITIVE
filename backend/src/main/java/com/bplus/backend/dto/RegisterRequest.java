package com.bplus.backend.dto;

public class RegisterRequest {
    private String name;
    private String email;

    public RegisterRequest()
    {

    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String registerValidation()
    {

        if(name == null || name.trim().isEmpty() || email == null || email.trim().isEmpty())
        {
            return "Please fill up all details to proceed";
        }

        return "Valid details";

    }


}
