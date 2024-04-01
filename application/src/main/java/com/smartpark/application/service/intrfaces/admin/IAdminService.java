package com.smartpark.application.service.intrfaces.admin;

import com.smartpark.application.dto.admin.AdminReq;
import com.smartpark.application.dto.admin.AdminResp;
import com.smartpark.application.service.intrfaces.IService;

import java.util.UUID;

public interface IAdminService extends IService<AdminReq, AdminResp, UUID> {
}
