package com.smartpark.application.dto.floor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class FloorReq {

    private UUID id;

    @PositiveOrZero
    private Integer nbr;

    @NotNull
    private UUID parking;
}
