package com.smartpark.application.controller.admin;

import com.smartpark.application.dto.admin.AdminReq;
import com.smartpark.application.dto.admin.AdminResp;
import com.smartpark.application.service.intrfaces.admin.IAdminService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/admins", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AdminController {

    private final IAdminService adminService;

    @GetMapping
    public ResponseEntity<List<AdminResp>> getAllAdmins() {
        return ResponseEntity.ok(adminService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminResp> getAdmin(@PathVariable(name = "id") final UUID id) {
        return ResponseEntity.ok(adminService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<AdminResp> createAdmin(@RequestBody @Valid final AdminReq adminReq) {
        return new ResponseEntity<>(adminService.save(adminReq), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<String> deleteAdmin(@PathVariable(name = "id") final UUID id) {
        adminService.delete(id);
        return new ResponseEntity<>("Admin Deleted Successfully", HttpStatus.ACCEPTED);
    }

}
