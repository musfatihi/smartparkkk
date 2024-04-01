package com.smartpark.application.repository;

import com.smartpark.application.entity.Client;
import com.smartpark.application.entity.ParkingSpace;
import com.smartpark.application.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface ReservationRepo extends JpaRepository<Reservation, UUID> {

    Reservation findFirstByClient(Client client);

    Reservation findFirstByParkingSpace(ParkingSpace parkingSpace);

    List<Reservation> findAllByParkingSpaceId(UUID id);

    List<Reservation> findAllByParkingSpace(ParkingSpace parkingSpace);

    List<Reservation> findAllByClientId(UUID id);

}
