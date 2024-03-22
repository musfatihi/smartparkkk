package com.smartpark.application.repository;

import com.smartpark.application.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminRepo extends JpaRepository<Admin, UUID> {
    Optional<Admin> findByEmail(String email);

    boolean existsByEmailIgnoreCase(String email);

}
