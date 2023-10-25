package com.example.clientcontact.service;

import com.example.clientcontact.entity.Client;
import com.example.clientcontact.entity.ContactType;
import com.example.clientcontact.entity.Email;
import com.example.clientcontact.entity.Phone;
import com.example.clientcontact.exception.ClientNotFoundException;
import com.example.clientcontact.exception.ResourceNotFoundException;
import com.example.clientcontact.repository.ClientRepository;
import com.example.clientcontact.repository.EmailRepository;
import com.example.clientcontact.repository.PhoneRepository;
import com.example.clientcontact.service.dto.ClientDTO;
import com.example.clientcontact.service.dto.EmailDTO;
import com.example.clientcontact.service.dto.PhoneDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientService {

    private final ClientRepository clientRepository;
    private final PhoneRepository phoneRepository;
    private final EmailRepository emailRepository;

    public ClientService(ClientRepository clientRepository, PhoneRepository phoneRepository, EmailRepository emailRepository) {
        this.clientRepository = clientRepository;
        this.phoneRepository = phoneRepository;
        this.emailRepository = emailRepository;
    }

    @Transactional
    public ClientDTO saveClient(ClientDTO clientDTO) {
        log.info("Entering saveClient method.");
        Client client = ClientDTO.mapDtoToEntity(clientDTO);
        Client savedClient = clientRepository.save(client);
        log.info("Successfully completed saveClient.");
        return ClientDTO.mapEntityToDto(savedClient);
    }

    @Transactional
    public PhoneDTO createPhoneContact(Long clientId, PhoneDTO phoneDTO) {
        log.info("Entering createPhone method.");
        Client client = clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
        Phone phone = PhoneDTO.mapDtoToEntity(phoneDTO);
        phone.setClient(client);
        Phone savedPhone = phoneRepository.save(phone);
        log.info("Successfully createPhoneContact.");
        return PhoneDTO.mapEntityToDto(savedPhone);
    }
    @Transactional
    public EmailDTO createEmailContact(Long clientId, EmailDTO emailDTO) {
        log.info("Entering createEmailContact method.");
        Client client = clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
        Email email = EmailDTO.mapDtoToEntity(emailDTO);
        email.setClient(client);
        Email savedEmail = emailRepository.save(email);
        log.info("Successfully createEmailContact.");
        return EmailDTO.mapEntityToDto(savedEmail);
    }

    @Transactional(readOnly = true)
    public List<PhoneDTO> getPhoneContactsForClient(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
        List<Phone> phoneContacts = phoneRepository.findByClient(client);
        return phoneContacts.stream().map(PhoneDTO::mapEntityToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PhoneDTO> getPhoneContactsByTypeForClient(Long clientId, ContactType type) {
        Client client = clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
        List<Phone> phoneContacts = phoneRepository.findByClientAndType(client, type);
        return phoneContacts.stream().map(PhoneDTO::mapEntityToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EmailDTO> getEmailContactsByTypeForClient(Long clientId, ContactType type) {
        Client client = clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
        List<Email> emailContacts = emailRepository.findByClientAndType(client, type);
        return emailContacts.stream().map(EmailDTO::mapEntityToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EmailDTO> getEmailContactsForClient(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
        List<Email> emailContacts = emailRepository.findByClient(client);
        return emailContacts.stream().map(EmailDTO::mapEntityToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ClientDTO> getAllClients() {
        log.info("Entering getAllClients method.");
        List<ClientDTO> clients = clientRepository.findAll()
                .stream()
                .map(ClientDTO::mapEntityToDto)
                .collect(Collectors.toList());
        log.info("Successfully completed getAllClients.");
        return clients;
    }

    @Transactional(readOnly = true)
    public ClientDTO getClientById(Long clientId) {
        log.info("Entering getClientById method.");
        return clientRepository.findById(clientId)
                .map(ClientDTO::mapEntityToDto)
                .orElse(null);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public ClientDTO updateClient(Long clientId, ClientDTO updatedClient) {
        log.info("Entering updateClient method.");
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        if (updatedClient.getName() != null) {
            existingClient.setName(updatedClient.getName());
        }
        Client savedClient = clientRepository.save(existingClient);
        log.info("Successfully completed updateClient.");
        return ClientDTO.mapEntityToDto(savedClient);
    }

    @Transactional
    public void deleteClient(Long clientId) {
        log.info("Entering deleteClient method.");
        clientRepository.deleteById(clientId);
        log.info("Successfully completed deleteClient.");
    }

}

