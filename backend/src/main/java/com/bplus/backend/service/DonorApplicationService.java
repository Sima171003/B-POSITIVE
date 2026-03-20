package com.bplus.backend.service;

import com.bplus.backend.dto.DonorApplicationRequest;
import com.bplus.backend.entity.DonorApplication;
import com.bplus.backend.repo.DonorApplicationRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service




public class DonorApplicationService {

    @Autowired

    private DonorApplicationRepo donorApplicationRepo;

    public String submitDonorApplication(DonorApplicationRequest request )
    {

        DonorApplication application = new DonorApplication();

        application.setName(request.getName());
        //application.setEmail(request.getEmail());
        application.setBloodGroup(request.getBloodGroup());
        application.setPhone(request.getPhone());
        application.setCity(request.getCity());

        donorApplicationRepo.save(application);

        return "Donor Application submitted Successfully";


    }
    
}
