package com.smartpark.application.service.reservation;


import com.smartpark.application.dto.reservation.ReservationReq;
import com.smartpark.application.dto.reservation.ReservationResp;
import com.smartpark.application.entity.Client;
import com.smartpark.application.entity.ParkingSpace;
import com.smartpark.application.entity.Reservation;
import com.smartpark.application.exception.NotFoundException;
import com.smartpark.application.repository.ClientRepo;
import com.smartpark.application.repository.ParkingSpaceRepo;
import com.smartpark.application.repository.ReservationRepo;
import com.smartpark.application.service.parkingSpace.ParkingSpaceService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepo reservationRepository;
    private final ClientRepo clientRepository;
    private final ParkingSpaceRepo parkingSpaceRepository;
    private final ParkingSpaceService parkingSpaceService;

    public List<ReservationResp> findAll() {
        final List<Reservation> reservations = reservationRepository.findAll(Sort.by("id"));
        return reservations.stream()
                .map(reservation -> mapToDTO(reservation, new ReservationResp()))
                .toList();
    }

    public ReservationResp get(final UUID id) {
        return reservationRepository.findById(id)
                .map(reservation -> mapToDTO(reservation, new ReservationResp()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final ReservationReq reservationReq) {

        isParkingSpaceAvailable(reservationReq);

        final Reservation reservation = new Reservation();
        mapToEntity(reservationReq, reservation);
        return reservationRepository.save(reservation).getId();

    }

    public void update(final UUID id, final ReservationReq reservationReq) {
        final Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(reservationReq, reservation);
        reservationRepository.save(reservation);
    }

    public void delete(final UUID id) {
        reservationRepository.deleteById(id);
    }

    public ReservationResp mapToDTO(final Reservation reservation,
                                    final ReservationResp reservationResp) {
        reservationResp.setId(reservation.getId());
        reservationResp.setRFrom(reservation.getRFrom());
        reservationResp.setRTo(reservation.getRTo());
        reservationResp.setClient(reservation.getClient() == null ? null : reservation.getClient().getId());
        reservationResp.setParkingSpace(reservation.getParkingSpace() == null ? null : reservation.getParkingSpace().getId());
        return reservationResp;
    }

    private Reservation mapToEntity(final ReservationReq reservationReq,
            final Reservation reservation) {
        reservation.setRFrom(reservationReq.getRFrom());
        reservation.setRTo(reservationReq.getRTo());
        final Client client = reservationReq.getClient() == null ? null : clientRepository.findById(reservationReq.getClient())
                .orElseThrow(() -> new NotFoundException("client not found"));
        reservation.setClient(client);
        final ParkingSpace parkingSpace = reservationReq.getParkingSpace() == null ? null : parkingSpaceRepository.findById(reservationReq.getParkingSpace())
                .orElseThrow(() -> new NotFoundException("parkingSpace not found"));
        reservation.setParkingSpace(parkingSpace);
        return reservation;
    }


    private boolean isParkingSpaceAvailable(ReservationReq reservationReq){

        ParkingSpace parkingSpace = new ParkingSpace();
        parkingSpace.setId(reservationReq.getParkingSpace());
        List<Reservation> parkingSpaceReservations = reservationRepository.findAllByParkingSpace(parkingSpace);

        for (Reservation reservation :parkingSpaceReservations) {

            if((reservationReq.getRFrom().isAfter(reservation.getRFrom()) && reservationReq.getRFrom().isBefore(reservation.getRTo()))
            || (reservationReq.getRTo().isAfter(reservation.getRFrom()) && reservationReq.getRTo().isBefore(reservation.getRTo()))
            )
            {
                throw new NotFoundException("parkingSpace Not Available");
            }
        }


        return true;
    }

}
