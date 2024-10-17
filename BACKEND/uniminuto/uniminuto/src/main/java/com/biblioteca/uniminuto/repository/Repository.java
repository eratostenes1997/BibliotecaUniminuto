package com.biblioteca.uniminuto.repository;

import com.biblioteca.uniminuto.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Libro, Long> {

}


