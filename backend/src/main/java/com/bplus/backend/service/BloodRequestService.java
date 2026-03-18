// package com.bplus.backend.service;

// import com.bplus.backend.dto.BloodRequestDTO;
// import com.bplus.backend.model.BloodRequestApplication;
// import com.bplus.backend.repo.BloodRequestRepo;

// import org.springframework.beans.factory.annotation.Autowired;;
// import org.springframework.stereotype.Service;

// @Service

// public class BloodRequestService {

//     @Autowired
//     private BloodRequestRepo bloodRequestRepo;

//     public String createRequest(BloodRequestDTO dto)
//     {
//         String validation = dto.validate();

//         if(!"Valid".equals(validation))
//         {
//             return validation;
//         }

//         BloodRequestApplication request = new BloodRequestApplication();

//         request.setPatientName(dto.getPatientName());
//         request.setBloodGroup(dto.getBloodGroup())
//         request.setUnitsRequired(dto.getUnitsRequired())
//         request.setHospitalName(dto.getHospitalName())
//         request.setLocation(dto.getLocation())
//         request.setContactNumber(dto.getContactNumber())

//         bloodRequestRepo.save(request);

//         return "Blood request is submitted successfully";

//     }
    
// }
