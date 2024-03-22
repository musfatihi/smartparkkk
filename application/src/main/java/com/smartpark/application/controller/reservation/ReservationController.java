package com.smartpark.application.controller.reservation;

import com.smartpark.application.dto.reservation.ReservationReq;
import com.smartpark.application.dto.reservation.ReservationResp;
import com.smartpark.application.service.reservation.ReservationService;
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
@RequestMapping(value = "/api/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;


    @GetMapping
    public ResponseEntity<List<ReservationResp>> getAllReservations() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResp> getReservation(@PathVariable(name = "id") final UUID id) {
        return ResponseEntity.ok(reservationService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createReservation(
            @RequestBody @Valid final ReservationReq reservationReq) {
        final UUID createdId = reservationService.create(reservationReq);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> updateReservation(@PathVariable(name = "id") final UUID id,
            @RequestBody @Valid final ReservationReq reservationReq) {
        reservationService.update(id, reservationReq);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<String> deleteReservation(@PathVariable(name = "id") final UUID id) {
        reservationService.delete(id);
        return new ResponseEntity<>("Reservation Deleted Successfully", HttpStatus.ACCEPTED);
    }

}
