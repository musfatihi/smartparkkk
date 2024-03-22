package com.smartpark.application.service;


import com.smartpark.application.entity.User;
import com.smartpark.application.repository.AdminRepo;
import com.smartpark.application.repository.ClientRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private final ClientRepo clientRepository;

    private final AdminRepo adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if(clientRepository.findByEmail(email).isPresent())
        {
            User user = clientRepository.findByEmail(email).get();
            user.setAuthority(new SimpleGrantedAuthority("ROLE_CLIENT"));
            return user;
        }
        if(adminRepository.findByEmail(email).isPresent())
        {
            User user = adminRepository.findByEmail(email).get();
            user.setAuthority(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return user;

        }
        throw new UsernameNotFoundException("User not found");
    }
}
