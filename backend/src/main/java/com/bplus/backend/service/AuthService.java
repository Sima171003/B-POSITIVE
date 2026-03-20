package com.bplus.backend.service;

import com.bplus.backend.dto.RegisterRequest;
import com.bplus.backend.entity.UserApplication;
import com.bplus.backend.repo.UserApplicationRepo;
import com.bplus.backend.repo.DonorApplicationRepo;
import com.bplus.backend.repo.VolunteerApplicationRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service




public class AuthService {

    @Autowired
    private UserApplicationRepo userApplicationRepo;

    @Autowired
    private DonorApplicationRepo donorApplicationRepo;

    @Autowired
    private VolunteerApplicationRepo volunteerApplicationRepo;

    public String submitUserApplication(RegisterRequest request )
    {

        String validation = request.registerValidation();

        if(!"Valid details".equals(validation)){
            return validation;
            

        }

        if(userApplicationRepo.existsByEmail(request.getEmail())){
            return "User already exists";
        }

        UserApplication application = new UserApplication();

        application.setName(request.getName());
        application.setEmail(request.getEmail());

        userApplicationRepo.save(application);

        return "User Application submitted Successfully";

    }

    public boolean userAlreadyExists(String email){

        if(userApplicationRepo.existsByEmail(email)){
            return true;
        }

        else if(donorApplicationRepo.existsByEmail(email)){
            return true;
        }

        else if(volunteerApplicationRepo.existsByEmail(email)){
            return true;
        }

        return false;
    }
    
    public boolean validateLogin(String email, String role){

        role = role.toLowerCase();

        if(role.equals("volunteer")){
            return volunteerApplicationRepo.existsByEmail(email);
        }

        if(role.equals("donor")){
            return donorApplicationRepo.existsByEmail(email);
        }

        if(role.equals("user")){
            return userApplicationRepo.existsByEmail(email);
        }

        return false;
    }
}
