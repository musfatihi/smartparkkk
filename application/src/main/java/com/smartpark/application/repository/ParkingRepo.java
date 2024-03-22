package com.smartpark.application.repository;

import com.smartpark.application.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParkingRepo extends JpaRepository<Parking, UUID> {
}
