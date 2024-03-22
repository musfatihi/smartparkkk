package com.smartpark.application.dto.client;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ClientResp {
    private UUID id;
    private String email;
}
