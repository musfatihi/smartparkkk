package com.smartpark.application.service.intrfaces.floor;

import com.smartpark.application.dto.floor.FloorReq;
import com.smartpark.application.dto.floor.FloorResp;
import com.smartpark.application.service.intrfaces.IService;

import java.util.UUID;

public interface IFloorService extends IService<FloorReq, FloorResp, UUID> {
}
