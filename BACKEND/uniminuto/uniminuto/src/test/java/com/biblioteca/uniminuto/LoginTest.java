package com.biblioteca.uniminuto;


import com.biblioteca.uniminuto.controller.UsuarioController;
import com.biblioteca.uniminuto.models.Usuario;
import com.biblioteca.uniminuto.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class LoginTest {

	@Mock
	private UsuarioRepository usuarioRepository;

	@InjectMocks
	private UsuarioController usuarioController;

	@BeforeEach
	public void setUp() {
		// Inicializa los mocks
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testLoginExitoso() {
		// Preparar los datos de prueba
		Usuario usuario = new Usuario();
		usuario.setUsername("usuario_prueba");
		usuario.setPassword("password123");

		// Simulamos el comportamiento del repositorio
		when(usuarioRepository.findByUsername("usuario_error")).thenReturn(Optional.of(usuario));

		// Ejecutar el método a probar
		String resultado = usuarioController.login(usuario);

		// Verificar el resultado esperado
		assertEquals("Login exitoso", resultado);
	}

	@Test
	public void testLoginFalloUsuarioNoEncontrado() {
		// Simular el comportamiento del repositorio para un usuario no existente
		when(usuarioRepository.findByUsername("usuario_no_existente")).thenReturn(Optional.empty());

		// Ejecutar el método
		Usuario usuarioFalso = new Usuario();
		usuarioFalso.setUsername("usuario_no_existente");
		usuarioFalso.setPassword("passwordIncorrecta");
		String resultado = usuarioController.login(usuarioFalso);

		// Verificar el resultado esperado
		assertEquals("Usuario no encontrado", resultado);
	}
}
