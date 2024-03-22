package com.smartpark.application.repository;

import com.smartpark.application.entity.Floor;
import com.smartpark.application.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface FloorRepo extends JpaRepository<Floor, UUID> {

    Floor findFirstByParking(Parking parking);
}
