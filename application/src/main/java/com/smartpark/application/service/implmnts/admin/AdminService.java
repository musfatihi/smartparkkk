package com.smartpark.application.service.implmnts.admin;

import com.smartpark.application.dto.admin.AdminReq;
import com.smartpark.application.dto.admin.AdminResp;
import com.smartpark.application.dto.parking.ParkingResp;
import com.smartpark.application.entity.Admin;
import com.smartpark.application.entity.Parking;
import com.smartpark.application.exception.NotFoundException;
import com.smartpark.application.repository.AdminRepo;
import com.smartpark.application.service.intrfaces.admin.IAdminService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AdminService implements IAdminService {
    private AdminRepo adminRepo;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    public List<AdminResp> findAll(){
        return List.of(
                modelMapper.map(adminRepo.findAll(), AdminResp[].class)
        );
    }

    @Override
    public AdminResp save(AdminReq adminReq) {
        return modelMapper.map(adminRepo.save(modelMapper.map(adminReq, Admin.class)), AdminResp.class);
    }

    public void delete(UUID id){
        adminRepo.deleteById(id);
    }

    public AdminResp get(final UUID id) {
        return adminRepo.findById(id)
                .map(admin -> mapToDTO(admin, new AdminResp()))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public AdminResp update(AdminReq adminReq) {
        return null;
    }


    public UUID create(final AdminReq adminReq) {
        final Admin admin = new Admin();
        mapToEntity(adminReq, admin);
        return adminRepo.save(admin).getId();
    }

    private AdminResp mapToDTO(final Admin admin, final AdminResp adminResp) {
        adminResp.setEmail(admin.getEmail());
        return adminResp;
    }

    private Admin mapToEntity(final AdminReq adminReq, final Admin admin) {
        admin.setEmail(adminReq.getEmail());
        admin.setPassword(passwordEncoder.encode(adminReq.getPasswordForm().getPassword()));
        return admin;
    }

    public boolean emailExists(final String email) {
        return adminRepo.existsByEmailIgnoreCase(email);
    }

}
