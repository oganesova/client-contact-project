package com.example.clientcontact.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emailAddress;
    @Enumerated(EnumType.STRING)
    private ContactType type;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
