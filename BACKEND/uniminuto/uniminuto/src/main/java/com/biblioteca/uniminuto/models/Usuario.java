package com.biblioteca.uniminuto.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;

    @Column(unique = true)
    @Getter @Setter
    private String username;

    @Getter @Setter
    private String password;
}