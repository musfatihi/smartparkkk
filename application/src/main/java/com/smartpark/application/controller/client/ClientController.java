package com.smartpark.application.controller.client;

import com.smartpark.application.dto.client.ClientReq;
import com.smartpark.application.dto.client.ClientResp;
import com.smartpark.application.service.intrfaces.client.IClientService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PutMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<ClientResp> updateClient(@RequestBody @Valid final ClientReq clientReq) {
        return new ResponseEntity<>(clientService.update(clientReq), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<String> deleteClient(@PathVariable(name = "id") final UUID id) {
        clientService.delete(id);
        return new ResponseEntity<>("Client deleted successfully", HttpStatus.ACCEPTED);
    }
}
