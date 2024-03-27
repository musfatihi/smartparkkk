package com.smartpark.application.dto.admin;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class AdminResp {
    private UUID id;
    private String email;
}
