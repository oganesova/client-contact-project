package com.example.clientcontact.repository;

import com.example.clientcontact.entity.Client;
import com.example.clientcontact.entity.ContactType;
import com.example.clientcontact.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone,Long> {
    List<Phone> findByClient(Client client);
    List<Phone> findByClientAndType(Client client, ContactType type);
}
