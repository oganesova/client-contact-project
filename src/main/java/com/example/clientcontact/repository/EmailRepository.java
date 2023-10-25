package com.example.clientcontact.repository;

import com.example.clientcontact.entity.Client;
import com.example.clientcontact.entity.ContactType;
import com.example.clientcontact.entity.Email;
import com.example.clientcontact.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<Email,Long> {
    List<Email> findByClient(Client client);
    List<Email> findByClientAndType(Client client, ContactType type);
}
