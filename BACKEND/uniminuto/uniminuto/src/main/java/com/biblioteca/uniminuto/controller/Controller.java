package com.biblioteca.uniminuto.controller;

import com.biblioteca.uniminuto.models.Libro;
import com.biblioteca.uniminuto.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {


    @Autowired
    private Repository repository;


    @GetMapping()
    public String index(){
        return "CONECTADO";
    }

    @GetMapping("libros")
    public List<Libro> getLibros(){
        return repository.findAll();
    }

    @PostMapping("adicionar")
    public String adicionar(@RequestBody Libro libro){
        repository.save(libro);
        return "Grabado";
    }

    @PutMapping("editar/{id}")
    public String editar(@PathVariable Long id, @RequestBody Libro libro){
        Libro updateLibro = repository.findById(id).get();
        updateLibro.setAutor(libro.getAutor());
        updateLibro.setTitulo(libro.getTitulo());
        repository.save(updateLibro);
        return "Editado";
    }

    @DeleteMapping("borrar/{id}")
    public String borrar(@PathVariable Long id){
        Libro deleteLibro = repository.findById(id).get();
        repository.delete(deleteLibro);
        return "Eliminado";
    }
}
