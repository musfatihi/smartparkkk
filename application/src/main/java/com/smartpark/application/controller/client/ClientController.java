package com.smartpark.application.controller.client;

import com.smartpark.application.dto.client.ClientResp;
import com.smartpark.application.service.intrfaces.client.IClientService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/clients", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ClientController {

    private final IClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientResp>> getAllClients() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResp> getClient(@PathVariable(name = "id") final UUID id) {
        return ResponseEntity.ok(clientService.get(id));
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteClient(@PathVariable(name = "id") final UUID id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
