package com.smartpark.application.service.intrfaces.client;

import com.smartpark.application.dto.client.ClientReq;
import com.smartpark.application.dto.client.ClientResp;
import com.smartpark.application.service.intrfaces.IService;

import java.util.UUID;

public interface IClientService extends IService<ClientReq, ClientResp, UUID> {
}
