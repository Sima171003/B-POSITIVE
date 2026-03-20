package com.bplus.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bplus.backend.entity.VolunteerApplication;

@Repository
public interface VolunteerApplicationRepo extends JpaRepository<VolunteerApplication,Long> {
    boolean existsByEmail(String email);
}
