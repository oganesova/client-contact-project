package com.example.clientcontact.controller;

import com.example.clientcontact.service.PhoneService;
import com.example.clientcontact.service.dto.PhoneDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phones")
public class PhoneController {
    private final PhoneService phoneService;

    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @PostMapping
    public PhoneDTO addPhone(@RequestBody PhoneDTO phoneDTO) {
        return phoneService.createPhone(phoneDTO);
    }

    @GetMapping
    public List<PhoneDTO> getAllPhones() {
        return phoneService.getAllPhones();
    }

    @GetMapping("/{phoneId}")
    public PhoneDTO getPhone(@PathVariable Long phoneId) {
        return phoneService.getPhoneById(phoneId);
    }

    @PutMapping("/{phoneId}")
    public ResponseEntity<PhoneDTO> updatePhone(@PathVariable Long phoneId, @RequestBody PhoneDTO updatedPhone) {
        PhoneDTO updatedPhoneDTO = phoneService.updatePhone(phoneId, updatedPhone);
        return ResponseEntity.ok(updatedPhoneDTO);
    }

    @PatchMapping("/{phoneId}")
    public ResponseEntity<PhoneDTO> partialUpdatePhone(@PathVariable Long phoneId, @RequestBody PhoneDTO updatedPhone) {
        PhoneDTO updatedPhoneDTO = phoneService.updatePhone(phoneId, updatedPhone);
        return ResponseEntity.ok(updatedPhoneDTO);
    }
    @DeleteMapping("/{phoneId}")
    public void deletePhone(@PathVariable Long phoneId) {
        phoneService.deletePhone(phoneId);
    }
}
