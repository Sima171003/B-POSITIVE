package com.bplus.backend.service;

import com.bplus.backend.Entity.UserApplication;
import com.bplus.backend.dto.RegisterRequest;
import com.bplus.backend.repo.UserApplicationRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service




public class AuthService {

    @Autowired

    private UserApplicationRepo userApplicationRepo;

    


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

    public UserApplication getUserByEmail(String email){
        return userApplicationRepo.findByEmail(email);
    }
    
}
