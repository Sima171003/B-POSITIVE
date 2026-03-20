package com.bplus.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bplus.backend.Entity.DonorApplication;


@Repository


public interface DonorApplicationRepo extends JpaRepository<DonorApplication, Long> {
    
}
