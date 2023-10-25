package com.example.clientcontact.service;

import com.example.clientcontact.entity.Email;
import com.example.clientcontact.exception.ResourceNotFoundException;
import com.example.clientcontact.repository.EmailRepository;
import com.example.clientcontact.service.dto.EmailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmailService {
    private final EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }


    @Transactional
    public EmailDTO createEmail(EmailDTO emailDTO) {
        log.info("Entering createEmail method.");
        Email email = EmailDTO.mapDtoToEntity(emailDTO);
        Email savedEmail = emailRepository.save(email);
        log.info("Successfully completed createEmail.");
        return EmailDTO.mapEntityToDto(savedEmail);
    }

    @Transactional(readOnly = true)
    public List<EmailDTO> getAllEmails() {
        log.info("Entering getAllEmails method.");
        List<EmailDTO> emails = emailRepository.findAll()
                .stream()
                .map(EmailDTO::mapEntityToDto)
                .collect(Collectors.toList());
        log.info("Successfully completed getAllEmails.");
        return emails;
    }

    @Transactional(readOnly = true)
    public EmailDTO getEmailById(Long emailId) {
        log.info("Entering getEmailById method.");
        return emailRepository.findById(emailId)
                .map(EmailDTO::mapEntityToDto)
                .orElse(null);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public EmailDTO updateEmail(Long emailId, EmailDTO updatedEmail) {
        log.info("Entering updateEmail method.");
        Email existingEmail = emailRepository.findById(emailId)
                .orElseThrow(() -> new ResourceNotFoundException("Email not found"));
        if (updatedEmail.getEmail() != null) {
            existingEmail.setEmailAddress(updatedEmail.getEmail());
        }
        Email savedEmail = emailRepository.save(existingEmail);
        log.info("Successfully completed updateEmail.");
        return EmailDTO.mapEntityToDto(savedEmail);
    }

    @Transactional
    public void deleteEmail(Long emailId) {
        log.info("Entering deleteEmail method.");
        emailRepository.deleteById(emailId);
        log.info("Successfully completed deleteEmail.");
    }
}
