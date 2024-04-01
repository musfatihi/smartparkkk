package com.smartpark.application.service.implmnts.security;


import com.smartpark.application.dto.auth.AuthenticationResponse;
import com.smartpark.application.dto.user.UserReq;
import com.smartpark.application.dto.user.UserReqLogin;
import com.smartpark.application.entity.Client;
import com.smartpark.application.entity.User;
import com.smartpark.application.repository.AdminRepo;
import com.smartpark.application.repository.ClientRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

    private final ClientRepo clientRepository;
    private final AdminRepo adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(ClientRepo clientRepository,
                                 AdminRepo adminRepository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 AuthenticationManager authenticationManager) {
        this.clientRepository = clientRepository;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(UserReq userReq) {

        if(clientRepository.findByEmail(userReq.getEmail()).isPresent()) {
            return new AuthenticationResponse(null, "User already exist");
        }

        Client client = new Client();
        client.setEmail(userReq.getEmail());
        client.setPassword(passwordEncoder.encode(userReq.getPassword()));


        client = clientRepository.save(client);
        client.setAuthority(new SimpleGrantedAuthority("ROLE_CLIENT"));

        String jwt = jwtService.generateToken(client);

        return new AuthenticationResponse(jwt, "User registration was successful");

    }

    public AuthenticationResponse authenticate(UserReqLogin userReqLogin) {

        boolean found = false;
        User user=null;

        if(clientRepository.findByEmail(userReqLogin.getEmail()).isEmpty() &&
           adminRepository.findByEmail(userReqLogin.getEmail()).isEmpty())
        {
            throw new UsernameNotFoundException("User not found");
        }
        else{
            if(clientRepository.findByEmail(userReqLogin.getEmail()).isPresent())
            {
                user = clientRepository.findByEmail(userReqLogin.getEmail()).get();
                found=true;
            }
            if(adminRepository.findByEmail(userReqLogin.getEmail()).isPresent() && !found)
            {
                user = adminRepository.findByEmail(userReqLogin.getEmail()).get();
            }
        }


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userReqLogin.getEmail(),
                        userReqLogin.getPassword()
                )
        );

        String jwt = jwtService.generateToken(user);

        return new AuthenticationResponse(jwt, "User login was successful");

    }

}
