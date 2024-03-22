package com.smartpark.application.service.client;

import com.smartpark.application.dto.client.ClientReq;
import com.smartpark.application.dto.client.ClientResp;
import com.smartpark.application.entity.Client;
import com.smartpark.application.entity.Reservation;
import com.smartpark.application.exception.NotFoundException;
import com.smartpark.application.exception.ReferencedWarning;
import com.smartpark.application.repository.ClientRepo;
import com.smartpark.application.repository.ReservationRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientService {

    private ClientRepo clientRepo;
    private ReservationRepo reservationRepo;
    private ModelMapper modelMapper;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<ClientResp> findAll(){
        return List.of(
                modelMapper.map(clientRepo.findAll(), ClientResp[].class)
        );
    }


    public void delete(UUID id){
        clientRepo.deleteById(id);
    }

    public ClientResp get(final UUID id) {
        return clientRepo.findById(id)
                .map(client -> mapToDTO(client, new ClientResp()))
                .orElseThrow(NotFoundException::new);
    }



    private ClientResp mapToDTO(final Client client, final ClientResp clientResp) {
        clientResp.setEmail(client.getEmail());
        return clientResp;
    }

    private Client mapToEntity(final ClientReq clientReq, final Client client) {
        client.setEmail(clientReq.getEmail());
        client.setPassword(clientReq.getPassword());
        return client;
    }

    public boolean emailExists(final String email) {
        return clientRepo.existsByEmailIgnoreCase(email);
    }

    public ReferencedWarning getReferencedWarning(final UUID id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Client client = clientRepo.findById(id)
                .orElseThrow(NotFoundException::new);
        final Reservation clientReservation = reservationRepo.findFirstByClient(client);
        if (clientReservation != null) {
            referencedWarning.setKey("client.reservation.client.referenced");
            referencedWarning.addParam(clientReservation.getId());
            return referencedWarning;
        }
        return null;
    }
}
