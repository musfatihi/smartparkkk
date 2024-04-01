package com.smartpark.application.service.intrfaces.parkingspace;

import com.smartpark.application.dto.parkingSpace.ParkingSpaceReq;
import com.smartpark.application.dto.parkingSpace.ParkingSpaceResp;
import com.smartpark.application.service.intrfaces.IService;

import java.util.UUID;

public interface IParkingSpaceService extends IService<ParkingSpaceReq, ParkingSpaceResp, UUID> {
}
