package com.smartpark.application.dto.admin;

import com.smartpark.application.dto.client.PasswordForm;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class AdminReq {

    private UUID id;

    @Email
    private String email;

    @Valid
    private PasswordForm passwordForm;
}
