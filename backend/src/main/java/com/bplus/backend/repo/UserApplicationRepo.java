package com.bplus.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bplus.backend.Entity.UserApplication;


@Repository


public interface UserApplicationRepo extends JpaRepository<UserApplication, Long> {
    boolean existsByEmail(String email);
    UserApplication findByEmail(String email);
}
