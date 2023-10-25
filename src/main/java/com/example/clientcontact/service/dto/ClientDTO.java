package com.example.clientcontact.service.dto;

import com.example.clientcontact.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDTO {

    private Long id;
    private String name;
    private List<EmailDTO> emailAddresses=new ArrayList<>();
    private List<PhoneDTO> phoneNumbers=new ArrayList<>();
    public static ClientDTO mapEntityToDto(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setEmailAddresses(client.getEmailAddresses().stream()
                .map(EmailDTO::mapEntityToDto)
                .collect(Collectors.toList()));
        clientDTO.setPhoneNumbers(client.getPhoneNumbers().stream()
                .map(PhoneDTO::mapEntityToDto)
                .collect(Collectors.toList()));
        return clientDTO;
    }

    public static Client mapDtoToEntity(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setName(clientDTO.getName());
        client.setEmailAddresses(clientDTO.getEmailAddresses().stream()
                .map(EmailDTO::mapDtoToEntity)
                .collect(Collectors.toList()));
        client.setPhoneNumbers(clientDTO.getPhoneNumbers().stream()
                .map(PhoneDTO::mapDtoToEntity)
                .collect(Collectors.toList()));
        return client;
    }

}

