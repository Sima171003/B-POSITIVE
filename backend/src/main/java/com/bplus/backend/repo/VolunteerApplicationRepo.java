package com.bplus.backend.repo;

import com.bplus.backend.model.VolunteerApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerApplicationRepo extends JpaRepository {
    boolean existsByEmail(String email);
}
