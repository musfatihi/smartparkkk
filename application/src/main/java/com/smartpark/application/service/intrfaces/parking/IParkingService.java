package com.smartpark.application.service.intrfaces.parking;

import com.smartpark.application.dto.parking.ParkingReq;
import com.smartpark.application.dto.parking.ParkingResp;
import com.smartpark.application.service.intrfaces.IService;

import java.util.UUID;

public interface IParkingService extends IService<ParkingReq, ParkingResp, UUID> {
}
