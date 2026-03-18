package com.bplus.backend.controller;

import com.bplus.backend.dto.DonorApplicationRequest;
import com.bplus.backend.service.DonorApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/donor")
public class DonorApplicationController {

    @Autowired
    private DonorApplicationService donorApplicationService;

    @PostMapping("/apply")
    public String applyForDonor(@RequestBody DonorApplicationRequest request) {

        //TODO: process POST request
        donorApplicationService.submitDonorApplication(request);
        
        return "Donor application submitted successfully.";
    }
    

    
}
