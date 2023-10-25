package com.example.clientcontact.controller;

import com.example.clientcontact.service.EmailService;
import com.example.clientcontact.service.dto.EmailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emails")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public EmailDTO addEmail(@RequestBody EmailDTO emailDTO) {
        return emailService.createEmail(emailDTO);
    }

    @GetMapping
    public List<EmailDTO> getAllEmails() {
        return emailService.getAllEmails();
    }

    @GetMapping("/{emailId}")
    public EmailDTO getEmail(@PathVariable Long emailId) {
        return emailService.getEmailById(emailId);
    }

    @PutMapping("/{emailId}")
    public ResponseEntity<EmailDTO> updateEmail(@PathVariable Long emailId, @RequestBody EmailDTO updatedEmail) {
        EmailDTO updatedEmailDTO = emailService.updateEmail(emailId, updatedEmail);
        return ResponseEntity.ok(updatedEmailDTO);
    }

    @PatchMapping("/{emailId}")
    public ResponseEntity<EmailDTO> partialUpdateEmail(@PathVariable Long emailId, @RequestBody EmailDTO updatedEmail) {
        EmailDTO updatedEmailDTO = emailService.updateEmail(emailId, updatedEmail);
        return ResponseEntity.ok(updatedEmailDTO);
    }

    @DeleteMapping("/{emailId}")
    public void deleteEmail(@PathVariable Long emailId) {
        emailService.deleteEmail(emailId);
    }

}
