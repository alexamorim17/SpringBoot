package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.example.demo.Entity.Usuario;
import com.example.demo.Repository.UsuarioRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository repository;

	@BeforeAll
	void start() {

		repository.save(new Usuario(0L, "Alexandre da Silva", "alslex", "ale.png", "Alex232 Silva"));
		repository.save(new Usuario(0L, "Yuri Oliveira", "173921s", "yuri.png", "Mizera Silva"));
		repository.save(new Usuario(0L, "Mirella da Silva", "fofsasx", "mih.png", "Pedregulho Silva"));
		repository.save(new Usuario(0L, "Pedro da Silva", "xavsxz", "pedroso.png", "Pedregulho"));
	}

	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {

		Optional<Usuario> usuario = repository.findByUsuario("Pedregulho");
		assertTrue(usuario.get().getUsuario().equals("Pedregulho"));
	}

	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {

		List<Usuario> listadeUsuarios = repository.findAllByUsuarioContainingIgnoringCase("Silva");
		assertEquals(3, listadeUsuarios.size());
		assertTrue(listadeUsuarios.get(0).getUsuario().equals("Alex232 Silva"));
		assertTrue(listadeUsuarios.get(1).getUsuario().equals("Mizera Silva"));
		assertTrue(listadeUsuarios.get(2).getUsuario().equals("Pedregulho Silva"));

	}

}
