package com.smartpark.application.controller.floor;

import com.smartpark.application.dto.floor.FloorReq;
import com.smartpark.application.dto.floor.FloorResp;
import com.smartpark.application.service.intrfaces.floor.IFloorService;
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
@RequestMapping(value = "/api/v1/floors", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class FloorController {

    private final IFloorService floorService;

    @GetMapping
    public ResponseEntity<List<FloorResp>> getAllFloors() {
        return ResponseEntity.ok(floorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FloorResp> getFloor(@PathVariable(name = "id") final UUID id) {
        return ResponseEntity.ok(floorService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<FloorResp> createFloor(@RequestBody @Valid final FloorReq floorReq) {
        return new ResponseEntity<>( floorService.save(floorReq), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<FloorResp> updateFloor(@RequestBody @Valid final FloorReq floorReq) {
        return new ResponseEntity<>(floorService.update(floorReq),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<String> deleteFloor(@PathVariable(name = "id") final UUID id) {
        floorService.delete(id);
        return ResponseEntity.ok("Floor deleted successfully");
    }

}
