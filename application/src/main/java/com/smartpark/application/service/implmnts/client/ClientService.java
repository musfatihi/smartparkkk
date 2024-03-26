package com.smartpark.application.service.implmnts.client;

import com.smartpark.application.dto.client.ClientReq;
import com.smartpark.application.dto.client.ClientResp;
import com.smartpark.application.dto.reservation.ReservationResp;
import com.smartpark.application.entity.Client;
import com.smartpark.application.exception.NotFoundException;
import com.smartpark.application.repository.ClientRepo;
import com.smartpark.application.repository.ReservationRepo;
import com.smartpark.application.service.intrfaces.client.IClientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService implements IClientService {

    private ClientRepo clientRepo;
    private ReservationRepo reservationRepo;
    private ModelMapper modelMapper;

    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<ClientResp> findAll(){
        final List<Client> clients = clientRepo.findAll();
        return clients.stream()
                .map(client -> mapToDTO(client, new ClientResp()))
                .toList();
    }

    @Override
    public ClientResp save(ClientReq clientReq) {
        return null;
    }


    public void delete(UUID id){
        clientRepo.deleteById(id);
    }

    public ClientResp get(final UUID id) {
        return clientRepo.findById(id)
                .map(client -> mapToDTO(client, new ClientResp()))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public ClientResp update(ClientReq clientReq) {
        return null;
    }



    private ClientResp mapToDTO(final Client client, final ClientResp clientResp) {
        clientResp.setId(client.getId());
        clientResp.setEmail(client.getEmail());
        clientResp.setReservations(client.getReservations().stream().map(reservation -> {
            ReservationResp reservationResp = new ReservationResp();
            reservationResp.setId(reservation.getId());
            reservationResp.setRFrom(reservation.getRFrom());
            reservationResp.setRTo(reservation.getRTo());
            reservationResp.setClient(reservation.getClient() == null ? null : reservation.getClient().getId());
            reservationResp.setParkingSpace(reservation.getParkingSpace() == null ? null : reservation.getParkingSpace().getId());

            return reservationResp;
        }).collect(Collectors.toList()));
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

}
