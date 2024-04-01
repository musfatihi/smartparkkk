package com.smartpark.application.dto.reservation;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ReservationResp {
    private UUID id;

    private LocalDateTime rFrom;

    private LocalDateTime rTo;

    private UUID client;

    private UUID parkingSpace;

    private String parkingSpaceNbr;

    private  int floorNbr;

    private String parkingName;
}
