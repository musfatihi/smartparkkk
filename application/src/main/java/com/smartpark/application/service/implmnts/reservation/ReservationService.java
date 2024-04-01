package com.smartpark.application.service.implmnts.reservation;


import com.smartpark.application.dto.reservation.ReservationReq;
import com.smartpark.application.dto.reservation.ReservationResp;
import com.smartpark.application.entity.Client;
import com.smartpark.application.entity.ParkingSpace;
import com.smartpark.application.entity.Reservation;
import com.smartpark.application.exception.NotFoundException;
import com.smartpark.application.exception.NotValidDataException;
import com.smartpark.application.repository.ClientRepo;
import com.smartpark.application.repository.ParkingSpaceRepo;
import com.smartpark.application.repository.ReservationRepo;
import com.smartpark.application.service.intrfaces.reservation.IReservationService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
public class ReservationService implements IReservationService {

    private final ReservationRepo reservationRepository;
    private final ClientRepo clientRepository;
    private final ParkingSpaceRepo parkingSpaceRepository;

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public List<ReservationResp> findAll() {
        final List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(reservation -> mapToDTO(reservation, new ReservationResp()))
                .toList();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    public List<ReservationResp> findMyReservations(UUID idClient) {
        final List<Reservation> reservations = reservationRepository.findAllByClientId(idClient);
        return reservations.stream()
                .map(reservation -> mapToDTO(reservation, new ReservationResp()))
                .toList();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    public ReservationResp save(ReservationReq reservationReq) {
        if(!isReservationRequestTimeRangeValid(reservationReq) || !isParkingSpaceValid(reservationReq))
        {
            throw new NotValidDataException();
        }

        isParkingSpaceAvailable(reservationReq);

        final Reservation reservation = new Reservation();
        mapToEntity(reservationReq, reservation);

        return mapToDTO(reservationRepository.save(reservation),new ReservationResp());

    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ReservationResp get(final UUID id) {
        return reservationRepository.findById(id)
                .map(reservation -> mapToDTO(reservation, new ReservationResp()))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public ReservationResp update(ReservationReq reservationReq) {
        return null;
    }


    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public void delete(final UUID id) {
        reservationRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        reservationRepository.deleteById(id);
    }

    public ReservationResp mapToDTO(final Reservation reservation,
                                    final ReservationResp reservationResp) {
        reservationResp.setId(reservation.getId());
        reservationResp.setRFrom(reservation.getRFrom());
        reservationResp.setRTo(reservation.getRTo());
        reservationResp.setClient(reservation.getClient() == null ? null : reservation.getClient().getId());
        reservationResp.setParkingSpace(reservation.getParkingSpace() == null ? null : reservation.getParkingSpace().getId());
        reservationResp.setParkingSpaceNbr(reservation.getParkingSpace().getNbr());
        reservationResp.setFloorNbr(reservation.getParkingSpace().getFloor().getNbr());
        reservationResp.setParkingName(reservation.getParkingSpace().getFloor().getParking().getName());

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


        List<Reservation> parkingSpaceReservations = reservationRepository.findAllByParkingSpaceId(reservationReq.getParkingSpace());



        for (Reservation reservation :parkingSpaceReservations) {

            if((reservationReq.getRFrom().isAfter(reservation.getRFrom()) && reservationReq.getRFrom().isBefore(reservation.getRTo()))
            || (reservationReq.getRTo().isAfter(reservation.getRFrom()) && reservationReq.getRTo().isBefore(reservation.getRTo()))
            || reservationReq.getRFrom().isEqual(reservation.getRFrom())
            || reservationReq.getRTo().isEqual(reservation.getRTo())
            )
            {
                throw new NotFoundException("parking Space Not Available");
            }

        }


        return true;
    }

    private boolean isParkingSpaceValid(ReservationReq reservationReq){
        return parkingSpaceRepository.existsById(reservationReq.getParkingSpace());
    }

    private boolean isReservationRequestTimeRangeValid(ReservationReq reservationReq){
        return reservationReq.getRTo().isAfter(reservationReq.getRFrom());
    }

}
