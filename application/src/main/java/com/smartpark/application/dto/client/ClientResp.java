package com.smartpark.application.dto.client;

import com.smartpark.application.dto.reservation.ReservationResp;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class ClientResp {
    private UUID id;
    private String email;
    private List<ReservationResp> reservations;
}
