package com.smartpark.application.controller.auth;

import com.smartpark.application.dto.auth.AuthenticationResponse;
import com.smartpark.application.dto.user.UserReq;
import com.smartpark.application.dto.user.UserReqLogin;
import com.smartpark.application.service.implmnts.security.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping(path = "api/v1")
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody @Valid UserReq userReq
    ) {

        return ResponseEntity.ok(authService.register(userReq));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody UserReqLogin userReqLogin
    ) {
        return ResponseEntity.ok(authService.authenticate(userReqLogin));
    }

}
