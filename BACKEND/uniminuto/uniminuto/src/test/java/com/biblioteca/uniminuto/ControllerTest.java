package com.biblioteca.uniminuto;

import com.biblioteca.uniminuto.controller.Controller;
import com.biblioteca.uniminuto.models.Libro;
import com.biblioteca.uniminuto.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ControllerTest {

    @Mock
    private Repository libroRepository;

    @InjectMocks
    private Controller libroController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetLibros() {
        // Preparar los datos de prueba
        List<Libro> libros = new ArrayList<>();
        libros.add(new Libro(1, "Libro 1", "Autor 1"));
        libros.add(new Libro(2, "Libro 2", "Autor 2"));

        when(libroRepository.findAll()).thenReturn(libros);

        // Ejecutar el método a probar
        List<Libro> resultado = libroController.getLibros();

        // Verificar el resultado esperado
        assertEquals(2, resultado.size());
        assertEquals("Libro 1", resultado.get(0).getTitulo());
        assertEquals("Autor 1", resultado.get(0).getAutor());
    }

    @Test
    public void testAdicionarLibro() {
        // Preparar los datos de prueba
        Libro libro = new Libro();
        libro.setTitulo("Nuevo Libro");
        libro.setAutor("Nuevo Autor");

        when(libroRepository.save(libro)).thenReturn(libro);

        // Ejecutar el método a probar
        String resultado = libroController.adicionar(libro);

        // Verificar el resultado esperado
        assertEquals("Grabado", resultado);
    }

    @Test
    public void testEditarLibro() {
        // Preparar los datos de prueba
        Libro libroExistente = new Libro(1, "Titulo Original", "Autor Original");
        Libro libroActualizado = new Libro(1, "Titulo Actualizado", "Autor Actualizado");

        when(libroRepository.findById(1L)).thenReturn(Optional.of(libroExistente));
        when(libroRepository.save(libroExistente)).thenReturn(libroActualizado);

        // Ejecutar el método a probar
        String resultado = libroController.editar(1L, libroActualizado);

        // Verificar el resultado esperado
        assertEquals("Editado", resultado);
        assertEquals("Titulo Actualizado", libroExistente.getTitulo());
    }

    @Test
    public void testBorrarLibro() {
        // Preparar los datos de prueba
        Libro libro = new Libro(1, "Libro a Borrar", "Autor a Borrar");

        when(libroRepository.findById(1L)).thenReturn(Optional.of(libro));
        doNothing().when(libroRepository).delete(libro);

        // Ejecutar el método a probar
        String resultado = libroController.borrar(1L);

        // Verificar el resultado esperado
        assertEquals("Eliminado", resultado);
        verify(libroRepository, times(1)).delete(libro);
    }
}
