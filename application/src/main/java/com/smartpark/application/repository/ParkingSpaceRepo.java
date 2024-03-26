package com.smartpark.application.repository;

import com.smartpark.application.entity.Floor;
import com.smartpark.application.entity.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface ParkingSpaceRepo extends JpaRepository<ParkingSpace, UUID> {
    ParkingSpace findFirstByFloor(Floor floor);

    boolean existsByNbrAndFloor(String nbr, Floor floor);
}
