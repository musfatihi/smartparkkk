package com.smartpark.application.controller.parkingSpace;

import com.smartpark.application.dto.parkingSpace.ParkingSpaceReq;
import com.smartpark.application.dto.parkingSpace.ParkingSpaceResp;
import com.smartpark.application.exception.ReferencedException;
import com.smartpark.application.exception.ReferencedWarning;
import com.smartpark.application.service.parkingSpace.ParkingSpaceService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@CrossOrigin
@RestController
@RequestMapping(value = "/api/parkingspaces", produces = MediaType.APPLICATION_JSON_VALUE)
public class ParkingSpaceController {

    private final ParkingSpaceService parkingSpaceService;

    public ParkingSpaceController(final ParkingSpaceService parkingSpaceService) {
        this.parkingSpaceService = parkingSpaceService;
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpaceResp>> getAllParkingSpaces() {
        return ResponseEntity.ok(parkingSpaceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpaceResp> getParkingSpace(
            @PathVariable(name = "id") final UUID id) {
        return ResponseEntity.ok(parkingSpaceService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createParkingSpace(
            @RequestBody @Valid final ParkingSpaceReq parkingSpaceReq) {
        final UUID createdId = parkingSpaceService.create(parkingSpaceReq);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> updateParkingSpace(@PathVariable(name = "id") final UUID id,
            @RequestBody @Valid final ParkingSpaceReq parkingSpaceReq) {
        parkingSpaceService.update(id, parkingSpaceReq);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteParkingSpace(@PathVariable(name = "id") final UUID id) {
        final ReferencedWarning referencedWarning = parkingSpaceService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        parkingSpaceService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
