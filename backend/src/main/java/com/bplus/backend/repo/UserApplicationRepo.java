package com.bplus.backend.repo;

import com.bplus.backend.model.UserApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository


public interface UserApplicationRepo extends JpaRepository<UserApplication, Long> {
    
}
