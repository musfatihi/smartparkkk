package com.smartpark.application.dto.floor;

import java.util.List;
import java.util.UUID;

import com.smartpark.application.dto.parkingSpace.ParkingSpaceResp;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FloorResp {

    private UUID id;

    private Integer nbr;

    private UUID parking;

    private String parkingName;

    private List<ParkingSpaceResp> parkingSpaces;

}
