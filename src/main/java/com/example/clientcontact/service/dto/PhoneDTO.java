package com.example.clientcontact.service.dto;

import com.example.clientcontact.entity.ContactType;
import com.example.clientcontact.entity.Phone;
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
public class PhoneDTO {
    private Long id;
    private String phoneNumber;
    private ContactType type;
    public static PhoneDTO mapEntityToDto(Phone phone) {
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setId(phone.getId());
        phoneDTO.setPhoneNumber(phone.getPhoneNumber());
        phoneDTO.setType(phone.getType());
        return phoneDTO;
    }

    public static Phone mapDtoToEntity(PhoneDTO phoneDTO) {
        Phone phone = new Phone();
        phone.setId(phoneDTO.getId());
        phone.setPhoneNumber(phoneDTO.getPhoneNumber());
        phone.setType(phoneDTO.getType());
        return phone;
    }

}
