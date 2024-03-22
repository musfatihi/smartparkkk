package com.smartpark.application.dto.admin;

import com.smartpark.application.dto.client.PasswordForm;
import com.smartpark.application.dto.user.UserReq;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminReq {
    @Email
    private String email;

    @Valid
    private PasswordForm passwordForm;
}
