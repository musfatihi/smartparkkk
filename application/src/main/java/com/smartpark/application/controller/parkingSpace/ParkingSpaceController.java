package com.smartpark.application.controller.parkingSpace;

import com.smartpark.application.dto.parkingSpace.ParkingSpaceReq;
import com.smartpark.application.dto.parkingSpace.ParkingSpaceResp;
import com.smartpark.application.exception.ReferencedException;
import com.smartpark.application.exception.ReferencedWarning;
import com.smartpark.application.service.implmnts.parkingSpace.ParkingSpaceService;
import com.smartpark.application.service.intrfaces.parkingspace.IParkingSpaceService;
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
@RequestMapping(value = "/api/v1/parkingspaces", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ParkingSpaceController {

    private final IParkingSpaceService parkingSpaceService;

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
    public ResponseEntity<ParkingSpaceResp> createParkingSpace(
            @RequestBody @Valid final ParkingSpaceReq parkingSpaceReq) {
        return new ResponseEntity<>(parkingSpaceService.save(parkingSpaceReq), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<ParkingSpaceResp> updateParkingSpace(@RequestBody @Valid final ParkingSpaceReq parkingSpaceReq) {
        return new ResponseEntity<>(parkingSpaceService.update(parkingSpaceReq),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<String> deleteParkingSpace(@PathVariable(name = "id") final UUID id) {
        parkingSpaceService.delete(id);
        return ResponseEntity.ok("Parking Space deleted successfully");
    }

}
