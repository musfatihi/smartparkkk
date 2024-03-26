package com.smartpark.application.dto.parking;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ParkingReq {

    private UUID id;

    @NotEmpty
    private String name;
    @NotEmpty
    private String city;
}
