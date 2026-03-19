package com.bplus.backend.repo;

import com.bplus.backend.model.DonorApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository


public interface DonorApplicationRepo extends JpaRepository<DonorApplication, Long> {
    boolean existsByEmail(String email);
}
