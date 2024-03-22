package com.smartpark.application.dto.parkingSpace;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ParkingSpaceReq {

    private UUID id;

    @NotNull
    @Size(max = 255)
    private String nbr;

    @NotNull
    private UUID floor;

}