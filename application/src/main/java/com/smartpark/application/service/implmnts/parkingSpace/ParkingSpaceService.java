package com.smartpark.application.service.implmnts.parkingSpace;

import com.smartpark.application.dto.parkingSpace.ParkingSpaceReq;
import com.smartpark.application.dto.parkingSpace.ParkingSpaceResp;
import com.smartpark.application.dto.reservation.ReservationResp;
import com.smartpark.application.entity.Floor;
import com.smartpark.application.entity.ParkingSpace;
import com.smartpark.application.exception.NotFoundException;
import com.smartpark.application.repository.FloorRepo;
import com.smartpark.application.repository.ParkingSpaceRepo;
import com.smartpark.application.repository.ReservationRepo;
import com.smartpark.application.service.intrfaces.parkingspace.IParkingSpaceService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ParkingSpaceService implements IParkingSpaceService {

    private final ParkingSpaceRepo parkingSpaceRepository;
    private final FloorRepo floorRepository;
    private final ReservationRepo reservationRepository;
    private final ModelMapper modelMapper;

    public List<ParkingSpaceResp> findAll() {
        final List<ParkingSpace> parkingSpaces = parkingSpaceRepository.findAll();
        return parkingSpaces.stream()
                .map(parkingSpace -> mapToDTO(parkingSpace, new ParkingSpaceResp()))
                .toList();
    }

    @Override
    public ParkingSpaceResp save(ParkingSpaceReq parkingSpaceReq) {
        if(isCreated(parkingSpaceReq) || !isFloorValid(parkingSpaceReq))
        {
            throw new NotFoundException();
        }
        return mapToDTO(parkingSpaceRepository.save(mapToEntity(parkingSpaceReq,new ParkingSpace())),new ParkingSpaceResp());
    }

    public ParkingSpaceResp get(final UUID id) {
        return parkingSpaceRepository.findById(id)
                .map(parkingSpace -> mapToDTO(parkingSpace, new ParkingSpaceResp()))
                .orElseThrow(NotFoundException::new);
    }


    public ParkingSpaceResp update(final ParkingSpaceReq parkingSpaceReq) {
        final ParkingSpace parkingSpace = parkingSpaceRepository.findById(parkingSpaceReq.getId())
                .orElseThrow(NotFoundException::new);
        if(isCreated(parkingSpaceReq) || !isFloorValid(parkingSpaceReq))
        {
            throw new NotFoundException();
        }
        mapToEntity(parkingSpaceReq, parkingSpace);
        return mapToDTO(parkingSpaceRepository.save(parkingSpace),new ParkingSpaceResp());
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

    private boolean isCreated(ParkingSpaceReq parkingSpaceReq){
        Floor floor = new Floor();
        floor.setId(parkingSpaceReq.getFloor());
        return parkingSpaceRepository.existsByNbrAndFloor(parkingSpaceReq.getNbr(),floor);
    }

    private boolean isFloorValid(ParkingSpaceReq parkingSpaceReq){
        return floorRepository.existsById(parkingSpaceReq.getFloor());
    }
}
