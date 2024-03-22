package com.smartpark.application.repository;

import com.smartpark.application.entity.Client;
import com.smartpark.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepo extends JpaRepository<Client, UUID> {
    Optional<Client> findByEmail(String email);

    boolean existsByEmailIgnoreCase(String email);
}
