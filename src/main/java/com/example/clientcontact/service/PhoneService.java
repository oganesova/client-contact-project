package com.example.clientcontact.service;

import com.example.clientcontact.entity.Phone;
import com.example.clientcontact.exception.ResourceNotFoundException;
import com.example.clientcontact.repository.PhoneRepository;
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
public class PhoneService {
    private final PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Transactional
    public PhoneDTO createPhone(PhoneDTO phoneDTO) {
        log.info("Entering createPhone method.");
        Phone phone = PhoneDTO.mapDtoToEntity(phoneDTO);
        Phone savedPhone = phoneRepository.save(phone);
        log.info("Successfully completed createPhone.");
        return PhoneDTO.mapEntityToDto(savedPhone);
    }

    @Transactional(readOnly = true)
    public List<PhoneDTO> getAllPhones() {
        log.info("Entering getAllPhones method.");
        List<PhoneDTO> phones = phoneRepository.findAll()
                .stream()
                .map(PhoneDTO::mapEntityToDto)
                .collect(Collectors.toList());
        log.info("Successfully completed getAllPhones.");
        return phones;
    }

    @Transactional(readOnly = true)
    public PhoneDTO getPhoneById(Long phoneId) {
        log.info("Entering getPhoneById method.");
        return phoneRepository.findById(phoneId)
                .map(PhoneDTO::mapEntityToDto)
                .orElse(null);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public PhoneDTO updatePhone(Long phoneId, PhoneDTO updatedPhone) {
        log.info("Entering updatePhone method.");
        Phone existingPhone = phoneRepository.findById(phoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Phone not found"));
        if (updatedPhone.getPhoneNumber() != null) {
            existingPhone.setPhoneNumber(updatedPhone.getPhoneNumber());
        }
        Phone savedPhone = phoneRepository.save(existingPhone);
        log.info("Successfully completed updatePhone.");
        return PhoneDTO.mapEntityToDto(savedPhone);
    }

    @Transactional
    public void deletePhone(Long phoneId) {
        log.info("Entering deletePhone method.");
        phoneRepository.deleteById(phoneId);
        log.info("Successfully completed deletePhone.");
    }
}
