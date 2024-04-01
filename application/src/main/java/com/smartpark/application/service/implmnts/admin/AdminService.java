package com.smartpark.application.service.implmnts.admin;

import com.smartpark.application.dto.admin.AdminReq;
import com.smartpark.application.dto.admin.AdminResp;
import com.smartpark.application.entity.Admin;
import com.smartpark.application.exception.NotFoundException;
import com.smartpark.application.exception.NotValidDataException;
import com.smartpark.application.repository.AdminRepo;
import com.smartpark.application.service.intrfaces.admin.IAdminService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AdminService implements IAdminService {

    private AdminRepo adminRepo;
    private PasswordEncoder passwordEncoder;

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public List<AdminResp> findAll(){
        final List<Admin> admins = adminRepo.findAll();
        return admins.stream()
                .map(admin -> mapToDTO(admin, new AdminResp()))
                .toList();
    }

    @Override
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public AdminResp save(AdminReq adminReq) {
        if(emailDoesntExist(adminReq.getEmail()))
        {
            return mapToDTO(adminRepo.save(mapToEntity(adminReq,new Admin())),new AdminResp());
        }

        throw new NotValidDataException();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public void delete(UUID id){
        adminRepo.findById(id)
                .orElseThrow(NotFoundException::new);
        adminRepo.deleteById(id);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public AdminResp get(final UUID id) {
        return adminRepo.findById(id)
                .map(admin -> mapToDTO(admin, new AdminResp()))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public AdminResp update(AdminReq adminReq) {
        Admin admin = adminRepo.findById(adminReq.getId())
                      .orElseThrow(NotFoundException::new);
        return mapToDTO(adminRepo.save(mapToEntity(adminReq,admin)),new AdminResp());
    }

    private AdminResp mapToDTO(final Admin admin, final AdminResp adminResp) {
        adminResp.setEmail(admin.getEmail());
        adminResp.setId(admin.getId());
        return adminResp;
    }

    private Admin mapToEntity(final AdminReq adminReq, final Admin admin) {
        admin.setEmail(adminReq.getEmail());
        admin.setPassword(passwordEncoder.encode(adminReq.getPasswordForm().getPassword()));
        return admin;
    }

    private boolean emailDoesntExist(final String email) {
        return !adminRepo.existsByEmailIgnoreCase(email);
    }

}
