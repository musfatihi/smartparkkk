package com.smartpark.application.service.implmnts.parking;


import com.smartpark.application.dto.floor.FloorResp;
import com.smartpark.application.dto.parking.ParkingReq;
import com.smartpark.application.dto.parking.ParkingResp;
import com.smartpark.application.entity.Parking;
import com.smartpark.application.exception.NotFoundException;
import com.smartpark.application.repository.ParkingRepo;
import com.smartpark.application.service.implmnts.floor.FloorService;
import com.smartpark.application.service.intrfaces.parking.IParkingService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ParkingService implements IParkingService {

    private ParkingRepo parkingRepo;

    private ModelMapper modelMapper;

    private FloorService floorService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CLIENT')")
    public List<ParkingResp> findAll() {
        final List<Parking> parkings = parkingRepo.findAll();
        return parkings.stream()
                .map(parking -> mapToDTO(parking, new ParkingResp()))
                .toList();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ParkingResp get(final UUID id) {
        return parkingRepo.findById(id)
                .map(parking -> mapToDTO(parking, new ParkingResp()))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ParkingResp update(ParkingReq parkingReq) {
        parkingRepo.findById(parkingReq.getId())
                .orElseThrow(NotFoundException::new);
        return modelMapper.map(parkingRepo.save(modelMapper.map(parkingReq, Parking.class)),ParkingResp.class);

    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ParkingResp save(ParkingReq parkingReq) {
        return modelMapper.map(parkingRepo.save(modelMapper.map(parkingReq, Parking.class)),ParkingResp.class);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public void delete(UUID id) {
        parkingRepo.findById(id)
                .orElseThrow(NotFoundException::new);
        parkingRepo.deleteById(id);
    }


    private ParkingResp mapToDTO(final Parking parking, final ParkingResp parkingResp) {
        parkingResp.setId(parking.getId());
        parkingResp.setName(parking.getName());
        parkingResp.setCity(parking.getCity());

        parkingResp.setFloors(
                parking.getFloors().stream()
                        .map(floor -> {
                            FloorResp floorResp = floorService.mapToDTO(floor,new FloorResp());
                            return floorResp;
                        })
                        .collect(Collectors.toList())
        );

        return parkingResp;
    }
}
