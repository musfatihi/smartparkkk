package com.smartpark.application.dto.parking;

import com.smartpark.application.dto.floor.FloorResp;
import com.smartpark.application.enums.Cities;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ParkingResp {

    private UUID id;

    private String name;

    private String city;

    private List<FloorResp> floors;
}
