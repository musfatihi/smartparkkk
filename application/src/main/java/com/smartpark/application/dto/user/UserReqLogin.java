package com.smartpark.application.dto.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReqLogin {
    @Email
    private String email;

    @NotEmpty
    private String password;
}
