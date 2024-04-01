package com.smartpark.application.service.intrfaces.reservation;

import com.smartpark.application.dto.reservation.ReservationReq;
import com.smartpark.application.dto.reservation.ReservationResp;
import com.smartpark.application.service.intrfaces.IService;

import java.util.List;
import java.util.UUID;

public interface IReservationService extends IService<ReservationReq, ReservationResp, UUID> {
    List<ReservationResp> findMyReservations(UUID idClient);
}
