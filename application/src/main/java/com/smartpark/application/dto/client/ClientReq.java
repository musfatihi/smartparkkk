package com.smartpark.application.dto.client;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ClientReq {

    private UUID id;

    @Email
    private String email;

    @Valid
    private PasswordForm passwordForm;

    public String getPassword(){
        return passwordForm.getPassword();
    }
}
