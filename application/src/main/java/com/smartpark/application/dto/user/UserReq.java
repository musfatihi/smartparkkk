package com.smartpark.application.dto.user;

import com.smartpark.application.dto.client.PasswordForm;
import com.smartpark.application.validation.ClientEmailUnique;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReq {
    @Email
    @ClientEmailUnique
    private String email;

    @Valid
    private PasswordForm passwordForm;

    public String getPassword(){
        return getPasswordForm().getPassword();
    }
}
