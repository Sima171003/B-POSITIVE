package com.bplus.backend.dto;

public class RegisterRequest {
    private String name;
    private String phone;
    private String loc;

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

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getLoc()
    {
        return loc;
    }

    public void setLoc(String loc)
    {
        this.loc = loc;
    }

    public String registerValidation()
    {

        if(name == null || phone == null || loc == null)
        {
            return "Please fill up all details to proceed";
        }

        return "Valid details";

    }


}
