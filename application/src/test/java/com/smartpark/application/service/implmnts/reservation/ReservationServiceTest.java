package com.smartpark.application.service.implmnts.reservation;

import com.smartpark.application.dto.reservation.ReservationReq;
import com.smartpark.application.entity.ParkingSpace;
import com.smartpark.application.entity.Reservation;
import com.smartpark.application.exception.NotFoundException;
import com.smartpark.application.exception.NotValidDataException;
import com.smartpark.application.repository.ClientRepo;
import com.smartpark.application.repository.ParkingSpaceRepo;
import com.smartpark.application.repository.ReservationRepo;
import com.smartpark.application.service.implmnts.parking.ParkingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    ReservationRepo reservationRepo;

    @Mock
    ClientRepo clientRepo;

    @Mock
    ParkingSpaceRepo parkingSpaceRepo;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    ReservationService reservationService;



    @Test
    void save() {

        UUID parkingSpaceUUID = UUID.randomUUID();

        //Reservation made earlier
        Reservation reservation = new Reservation();
        reservation.setRFrom(LocalDateTime.now());
        reservation.setRTo(LocalDateTime.now().plusHours(2));

        //New Reservation
        ReservationReq reservationReq = new ReservationReq();
        reservationReq.setRFrom(LocalDateTime.now().plusHours(1));
        reservationReq.setRTo(LocalDateTime.now().plusHours(2));
        reservationReq.setParkingSpace(parkingSpaceUUID);

        when(parkingSpaceRepo.existsById(reservationReq.getParkingSpace())).thenReturn(true);

        when(reservationRepo.findAllByParkingSpaceId(parkingSpaceUUID)).thenReturn(List.of(reservation));


        assertThrows(NotFoundException.class, () -> {
            reservationService.save(reservationReq);
        });


    }
}