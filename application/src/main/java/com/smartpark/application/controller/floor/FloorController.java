package com.smartpark.application.controller.floor;

import com.smartpark.application.dto.floor.FloorReq;
import com.smartpark.application.dto.floor.FloorResp;
import com.smartpark.application.exception.ReferencedException;
import com.smartpark.application.exception.ReferencedWarning;
import com.smartpark.application.service.floor.FloorService;
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

    private final FloorService floorService;

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
    public ResponseEntity<UUID> createFloor(@RequestBody @Valid final FloorReq floorReq) {
        final UUID createdId = floorService.create(floorReq);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> updateFloor(@PathVariable(name = "id") final UUID id,
            @RequestBody @Valid final FloorReq floorReq) {
        floorService.update(id, floorReq);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteFloor(@PathVariable(name = "id") final UUID id) {
        final ReferencedWarning referencedWarning = floorService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        floorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
