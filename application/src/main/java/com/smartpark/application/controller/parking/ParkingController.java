package com.smartpark.application.controller.parking;

import com.smartpark.application.dto.parking.ParkingReq;
import com.smartpark.application.dto.parking.ParkingResp;
import com.smartpark.application.service.intrfaces.parking.IParkingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/parkings")
@AllArgsConstructor
public class ParkingController {

    private IParkingService parkingService;

    @GetMapping()
    public ResponseEntity<List<ParkingResp>> getParkings()
    {
        return ResponseEntity.ok(
                parkingService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingResp> getParking(@PathVariable(name = "id") final UUID id) {
        return ResponseEntity.ok(parkingService.get(id));
    }

    @PostMapping()
    public ResponseEntity<ParkingResp> addParking(@RequestBody @Valid ParkingReq parkingReq)
    {
        return ResponseEntity.ok(
                parkingService.save(parkingReq)
        );
    }

    @PutMapping()
    public ResponseEntity<ParkingResp> updateParking(@RequestBody @Valid ParkingReq parkingReq)
    {
        return ResponseEntity.ok(
                parkingService.update(parkingReq)
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteParking(@PathVariable UUID id)
    {
        parkingService.delete(id);
        return ResponseEntity.ok("Parking deleted successfully");
    }

}
