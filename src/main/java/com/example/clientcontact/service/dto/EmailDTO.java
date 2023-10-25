package com.example.clientcontact.service.dto;

import com.example.clientcontact.entity.ContactType;
import com.example.clientcontact.entity.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailDTO {
    private Long id;
    private String email;
    private ContactType type;
    public static EmailDTO mapEntityToDto(Email email) {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setId(email.getId());
        emailDTO.setEmail(email.getEmailAddress());
        emailDTO.setType(email.getType());
        return emailDTO;
    }

    public static Email mapDtoToEntity(EmailDTO emailDTO) {
        Email email = new Email();
        email.setId(emailDTO.getId());
        email.setEmailAddress(emailDTO.getEmail());
        email.setType(emailDTO.getType());
        return email;
    }

}
