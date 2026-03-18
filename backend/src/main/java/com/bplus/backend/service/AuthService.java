package com.bplus.backend.service;

import com.bplus.backend.dto.RegisterRequest;
import com.bplus.backend.model.UserApplication;
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
        else
        {

            UserApplication application = new UserApplication();

            application.setName(request.getName());
            //application.setEmail(request.getEmail());
            //application.setBloodGroup(request.getBloodGroup());
            application.setPhone(request.getPhone());
            application.setLoc(request.getLoc());

            userApplicationRepo.save(application);

            return "User Application submitted Successfully";
        }

        


    }
    
}
