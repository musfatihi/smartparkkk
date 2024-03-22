package com.smartpark.application.dto.client;

import com.smartpark.application.validation.PasswordMatches;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PasswordMatches
public class PasswordForm {
    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmedPassword;
}
