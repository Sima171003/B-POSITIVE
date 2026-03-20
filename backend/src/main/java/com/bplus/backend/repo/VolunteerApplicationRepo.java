package com.bplus.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bplus.backend.entity.VolunteerApplication;

@Repository
<<<<<<< HEAD
public interface VolunteerApplicationRepo extends JpaRepository<VolunteerApplication, Long> {
=======
public interface VolunteerApplicationRepo extends JpaRepository<VolunteerApplication,Long> {
>>>>>>> 599d19550945772cea8ed46372f64214d0f000ce
    boolean existsByEmail(String email);
}
