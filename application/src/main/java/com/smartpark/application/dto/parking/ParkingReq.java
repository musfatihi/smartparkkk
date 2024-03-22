package com.smartpark.application.dto.parking;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingReq {
    @NotEmpty
    private String name;
    @NotEmpty
    private String city;
}
