package com.smartpark.application.service.floor;

import com.smartpark.application.dto.floor.FloorReq;
import com.smartpark.application.dto.floor.FloorResp;
import com.smartpark.application.dto.parking.ParkingResp;
import com.smartpark.application.dto.parkingSpace.ParkingSpaceResp;
import com.smartpark.application.entity.Floor;
import com.smartpark.application.entity.Parking;
import com.smartpark.application.entity.ParkingSpace;
import com.smartpark.application.exception.NotFoundException;
import com.smartpark.application.exception.ReferencedWarning;
import com.smartpark.application.repository.FloorRepo;
import com.smartpark.application.repository.ParkingRepo;
import com.smartpark.application.repository.ParkingSpaceRepo;
import com.smartpark.application.service.intrfaces.IService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class FloorService implements IService<FloorReq,FloorResp,UUID> {

    private final FloorRepo floorRepository;
    private final ParkingRepo parkingRepository;
    private final ParkingSpaceRepo parkingSpaceRepository;


    public List<FloorResp> findAll() {
        final List<Floor> floors = floorRepository.findAll(Sort.by("id"));
        return floors.stream()
                .map(floor -> mapToDTO(floor, new FloorResp()))
                .toList();
    }

    public FloorResp get(final UUID id) {
        return floorRepository.findById(id)
                .map(floor -> mapToDTO(floor, new FloorResp()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final @Valid FloorReq floorReq) {
        return floorRepository.save(
                mapToEntity(floorReq,new Floor())
        ).getId();
    }

    public void update(final UUID id, final @Valid FloorReq floorReq) {
        final Floor floor = floorRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(floorReq, floor);
        floorRepository.save(floor);
    }

    public void delete(final UUID id) {
        floorRepository.deleteById(id);
    }

    public FloorResp mapToDTO(final Floor floor, final FloorResp floorResp) {
        floorResp.setId(floor.getId());
        floorResp.setNbr(floor.getNbr());
        floorResp.setParking(floor.getParking() == null ? null : floor.getParking().getId());
        floorResp.setParkingSpaces(
        floor.getParkingSpaces().stream()
                .map(parkingSpace -> { // This is the map function with lambda expression
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

    public ReferencedWarning getReferencedWarning(final UUID id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Floor floor = floorRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final ParkingSpace floorParkingSpace = parkingSpaceRepository.findFirstByFloor(floor);
        if (floorParkingSpace != null) {
            referencedWarning.setKey("floor.parkingSpace.floor.referenced");
            referencedWarning.addParam(floorParkingSpace.getId());
            return referencedWarning;
        }
        return null;
    }

}
