package com.smartpark.application.dto.floor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class FloorReq {

    @PositiveOrZero
    private Integer nbr;

    @NotNull
    private UUID parking;
}
