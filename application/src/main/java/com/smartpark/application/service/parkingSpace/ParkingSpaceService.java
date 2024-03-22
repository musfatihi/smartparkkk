package com.smartpark.application.service.parkingSpace;

import com.smartpark.application.dto.parkingSpace.ParkingSpaceReq;
import com.smartpark.application.dto.parkingSpace.ParkingSpaceResp;
import com.smartpark.application.dto.reservation.ReservationResp;
import com.smartpark.application.entity.Floor;
import com.smartpark.application.entity.ParkingSpace;
import com.smartpark.application.entity.Reservation;
import com.smartpark.application.exception.NotFoundException;
import com.smartpark.application.exception.ReferencedWarning;
import com.smartpark.application.repository.FloorRepo;
import com.smartpark.application.repository.ParkingSpaceRepo;
import com.smartpark.application.repository.ReservationRepo;
import com.smartpark.application.service.reservation.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ParkingSpaceService {

    private final ParkingSpaceRepo parkingSpaceRepository;
    private final FloorRepo floorRepository;
    private final ReservationRepo reservationRepository;

    public List<ParkingSpaceResp> findAll() {
        final List<ParkingSpace> parkingSpaces = parkingSpaceRepository.findAll(Sort.by("id"));
        return parkingSpaces.stream()
                .map(parkingSpace -> mapToDTO(parkingSpace, new ParkingSpaceResp()))
                .toList();
    }

    public ParkingSpaceResp get(final UUID id) {
        return parkingSpaceRepository.findById(id)
                .map(parkingSpace -> mapToDTO(parkingSpace, new ParkingSpaceResp()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final ParkingSpaceReq parkingSpaceReq) {
        final ParkingSpace parkingSpace = new ParkingSpace();
        mapToEntity(parkingSpaceReq, parkingSpace);
        return parkingSpaceRepository.save(parkingSpace).getId();
    }

    public void update(final UUID id, final ParkingSpaceReq parkingSpaceReq) {
        final ParkingSpace parkingSpace = parkingSpaceRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(parkingSpaceReq, parkingSpace);
        parkingSpaceRepository.save(parkingSpace);
    }

    public void delete(final UUID id) {
        parkingSpaceRepository.deleteById(id);
    }

    private ParkingSpaceResp mapToDTO(final ParkingSpace parkingSpace,
                                     final ParkingSpaceResp parkingSpaceResp) {
        parkingSpaceResp.setId(parkingSpace.getId());
        parkingSpaceResp.setNbr(parkingSpace.getNbr());
        parkingSpaceResp.setFloor(parkingSpace.getFloor() == null ? null : parkingSpace.getFloor().getId());
        parkingSpaceResp.setReservations(
                parkingSpace.getReservations().stream().map(reservation -> {
                    ReservationResp reservationResp = new ReservationResp();
                    reservationResp.setId(reservation.getId());
                    reservationResp.setRFrom(reservation.getRFrom());
                    reservationResp.setRTo(reservation.getRTo());
                    reservationResp.setClient(reservation.getClient() == null ? null : reservation.getClient().getId());
                    reservationResp.setParkingSpace(reservation.getParkingSpace() == null ? null : reservation.getParkingSpace().getId());
                    return reservationResp;
                }).collect(Collectors.toList())
        );
        return parkingSpaceResp;
    }

    public ParkingSpace mapToEntity(final ParkingSpaceReq parkingSpaceReq,
            final ParkingSpace parkingSpace) {
        parkingSpace.setNbr(parkingSpaceReq.getNbr());
        final Floor floor = parkingSpaceReq.getFloor() == null ? null : floorRepository.findById(parkingSpaceReq.getFloor())
                .orElseThrow(() -> new NotFoundException("floor not found"));
        parkingSpace.setFloor(floor);
        return parkingSpace;
    }

    public ReferencedWarning getReferencedWarning(final UUID id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final ParkingSpace parkingSpace = parkingSpaceRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Reservation parkingSpaceReservation = reservationRepository.findFirstByParkingSpace(parkingSpace);
        if (parkingSpaceReservation != null) {
            referencedWarning.setKey("parkingSpace.reservation.parkingSpace.referenced");
            referencedWarning.addParam(parkingSpaceReservation.getId());
            return referencedWarning;
        }
        return null;
    }

}
