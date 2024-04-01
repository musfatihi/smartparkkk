package com.smartpark.application.dto.parkingSpace;

import com.smartpark.application.dto.reservation.ReservationResp;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ParkingSpaceResp {
    private UUID id;
    private String nbr;
    private UUID floor;
    private int floorNbr;
    private String parkingName;
    private List<ReservationResp> reservations;
}
