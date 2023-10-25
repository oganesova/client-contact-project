package com.example.clientcontact.controller;

import com.example.clientcontact.entity.ContactType;
import com.example.clientcontact.service.ClientService;
import com.example.clientcontact.service.dto.ClientDTO;
import com.example.clientcontact.service.dto.EmailDTO;
import com.example.clientcontact.service.dto.PhoneDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ClientDTO addClient(@RequestBody ClientDTO clientDTO) {
        return clientService.saveClient(clientDTO);
    }

    @GetMapping
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/phone/{clientId}")
    public List<PhoneDTO> getPhoneContactsForClient(@PathVariable Long clientId) {
        return clientService.getPhoneContactsForClient(clientId);
    }

    @GetMapping("/email/{clientId}")
    public List<EmailDTO> getEmailContactsForClient(@PathVariable Long clientId) {
        return clientService.getEmailContactsForClient(clientId);
    }

    @GetMapping("/phone/{clientId}/{type}")
    public List<PhoneDTO> getPhoneContactsByTypeForClient(@PathVariable Long clientId, @PathVariable ContactType type) {
        return clientService.getPhoneContactsByTypeForClient(clientId, type);
    }

    @GetMapping("/email/{clientId}/{type}")
    public List<EmailDTO> getEmailContactsByTypeForClient(@PathVariable Long clientId, @PathVariable ContactType type) {
        return clientService.getEmailContactsByTypeForClient(clientId, type);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long clientId, @RequestBody ClientDTO updatedClient) {
        ClientDTO updatedClientDTO = clientService.updateClient(clientId, updatedClient);
        return ResponseEntity.ok(updatedClientDTO);
    }

    @PostMapping("/phone/{clientId}")
    public ResponseEntity<PhoneDTO> createPhoneContact(@PathVariable Long clientId, @RequestBody PhoneDTO phoneDTO) {
        PhoneDTO savedPhoneDTO = clientService.createPhoneContact(clientId, phoneDTO);
        return new ResponseEntity<>(savedPhoneDTO, HttpStatus.CREATED);
    }

    @PostMapping("/email/{clientId}")
    public ResponseEntity<EmailDTO> createEmailContact(@PathVariable Long clientId, @RequestBody EmailDTO emailDTO) {
        EmailDTO savedEmailDTO = clientService.createEmailContact(clientId, emailDTO);
        return new ResponseEntity<>(savedEmailDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/{clientId}")
    public ResponseEntity<ClientDTO> partialUpdateClient(@PathVariable Long clientId, @RequestBody ClientDTO updatedClient) {
        ClientDTO updatedClientDTO = clientService.updateClient(clientId, updatedClient);
        return ResponseEntity.ok(updatedClientDTO);
    }

    @GetMapping("/{clientId}")
    public ClientDTO getClient(@PathVariable Long clientId) {
        return clientService.getClientById(clientId);
    }

    @DeleteMapping("/{clientId}")
    public void deleteClient(@PathVariable Long clientId) {
        clientService.deleteClient(clientId);
    }
}
