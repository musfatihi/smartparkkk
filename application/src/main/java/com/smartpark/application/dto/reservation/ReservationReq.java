package com.smartpark.application.dto.reservation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
public class ReservationReq {

    private UUID id;

    @NotNull
    @JsonProperty("rFrom")
    @Future
    private LocalDateTime rFrom;

    @NotNull
    @JsonProperty("rTo")
    @Future
    private LocalDateTime rTo;

    //@NotNull
    private UUID client;

    @NotNull
    private UUID parkingSpace;

}
