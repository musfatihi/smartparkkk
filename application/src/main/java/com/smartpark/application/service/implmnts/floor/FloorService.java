package com.smartpark.application.service.implmnts.floor;

import com.smartpark.application.dto.floor.FloorReq;
import com.smartpark.application.dto.floor.FloorResp;
import com.smartpark.application.dto.parkingSpace.ParkingSpaceResp;
import com.smartpark.application.entity.Floor;
import com.smartpark.application.entity.Parking;
import com.smartpark.application.exception.NotFoundException;
import com.smartpark.application.exception.NotValidDataException;
import com.smartpark.application.repository.FloorRepo;
import com.smartpark.application.repository.ParkingRepo;
import com.smartpark.application.repository.ParkingSpaceRepo;
import com.smartpark.application.service.intrfaces.floor.IFloorService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class FloorService implements IFloorService {

    private final FloorRepo floorRepository;
    private final ParkingRepo parkingRepository;
    private final ParkingSpaceRepo parkingSpaceRepository;


    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public List<FloorResp> findAll() {
        final List<Floor> floors = floorRepository.findAll();
        return floors.stream()
                .map(floor -> mapToDTO(floor, new FloorResp()))
                .toList();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public FloorResp save(FloorReq floorReq){
        if(!isParkingValid(floorReq) || isCreated(floorReq))
        {
            throw new NotValidDataException();
        }
        return mapToDTO(floorRepository.save(mapToEntity(floorReq,new Floor())),new FloorResp());
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public FloorResp get(final UUID id) {
        return floorRepository.findById(id)
                .map(floor -> mapToDTO(floor, new FloorResp()))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public FloorResp update(FloorReq floorReq) {
        final Floor floor = floorRepository.findById(floorReq.getId())
                .orElseThrow(NotFoundException::new);
        if(!isParkingValid(floorReq)) {
            throw new NotValidDataException();
        }
        mapToEntity(floorReq, floor);
        return mapToDTO(floorRepository.save(floor),new FloorResp());
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public void delete(final UUID id) {
        floorRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        floorRepository.deleteById(id);
    }

    public FloorResp mapToDTO(final Floor floor, final FloorResp floorResp) {
        floorResp.setId(floor.getId());
        floorResp.setNbr(floor.getNbr());
        floorResp.setParking(floor.getParking() == null ? null : floor.getParking().getId());
        floorResp.setParkingName(floor.getParking() == null ? null : floor.getParking().getName());

        floorResp.setParkingSpaces(
                floor.getParkingSpaces().stream()
                .map(parkingSpace -> {
                    ParkingSpaceResp dto = new ParkingSpaceResp();
                    dto.setId(parkingSpace.getId());
                    dto.setNbr(parkingSpace.getNbr());
                    dto.setFloor(parkingSpace.getFloor().getId());
                    return dto;
                })
                .collect(Collectors.toList())
        );

        return floorResp;
    }

    private Floor mapToEntity(final FloorReq floorReq, final Floor floor) {
        final Parking parking = floorReq.getParking() == null ? null : parkingRepository.findById(floorReq.getParking())
                .orElseThrow(() -> new NotFoundException("parking not found"));
        floor.setParking(parking);
        floor.setNbr(floorReq.getNbr());
        return floor;
    }

    private boolean isCreated(FloorReq floorReq){
       Parking parking = new Parking();
       parking.setId(floorReq.getParking());
        return floorRepository.existsByNbrAndParking(floorReq.getNbr(),parking);
    }

    private boolean isParkingValid(FloorReq floorReq){
        return parkingRepository.existsById(floorReq.getParking());
    }
}
