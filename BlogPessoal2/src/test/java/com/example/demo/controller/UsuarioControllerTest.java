package com.example.demo.controller;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.Entity.Usuario;
import com.example.demo.Service.UsuarioService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerTest {
	
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Test
	@Order(1)
	@DisplayName("Cadastra o usuario")
	public void CriaUmUsuario(){
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(0L,"Alexandre da Silva","alslex","ale.png","Alex232"));
		
		ResponseEntity<Usuario> resposta = testRestTemplate
				.exchange("/usuarios/cadastrar", HttpMethod.POST,requisicao,Usuario.class);
		
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		assertEquals(requisicao.getBody().getUsuario(), resposta.getBody().getUsuario());
		assertEquals(requisicao.getBody().getNome(),resposta.getBody().getNome());
		
	}
	
	@Test
	@Order(2)
	@DisplayName("Não permite duplicar usuario")
	public void naoDuplicarUsuario() {
		usuarioService.CadastrarUsuario(new Usuario(0L,"Paulo da Silva","ewqsdxs","p.png","paulo232"));
		
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(0L,"Paulo da Silva","ewqsdxs","p.png","paulo232"));
		
		ResponseEntity<Usuario> resposta = testRestTemplate
				.exchange("/usuarios/cadastrar",HttpMethod.POST,requisicao, Usuario.class);
		
		assertEquals(HttpStatus.BAD_REQUEST,resposta.getStatusCode());
	}
	
	@Test
	@Order(3)
	@DisplayName("Alterar um Usuário")
	public void deveAtualizarUmUsuario() {

		Optional<Usuario> usuarioCreate = usuarioService.CadastrarUsuario(new Usuario(0L, 
			"Juliana Andrews","ADSAUHD","juliana.jpg","juliana_andrews@email.com.br" ));

		Usuario usuarioUpdate = new Usuario(usuarioCreate.get().getId(), 
			"Juliana Andrews Ramos", "ADSAUHD","juliana.jpg", "juliana_ramos@email.com.br");
		
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(usuarioUpdate);

		ResponseEntity<Usuario> resposta = testRestTemplate
			.withBasicAuth("root", "root")
			.exchange("/usuarios/atualizar", HttpMethod.PUT, requisicao, Usuario.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(usuarioUpdate.getNome(), resposta.getBody().getNome());
		assertEquals(usuarioUpdate.getUsuario(), resposta.getBody().getUsuario());
	}

	
	@Test
	@Order(4)
	@DisplayName("Listar todos os usuarios")
	public void MostrarUsuarios() {
		
		usuarioService.CadastrarUsuario(new Usuario(0L,"Amanda da Silva Ciclano","ewqsdasdxs","a.png","amandinha232"));
		usuarioService.CadastrarUsuario(new Usuario(0L,"Elisa da Silva Pato","eduqheuqh","pato.png","patoPistola"));
		
		ResponseEntity<String> resposta = testRestTemplate
				.withBasicAuth("root", "root")
				.exchange("/usuarios/all", HttpMethod.GET,null,String.class);
		
		assertEquals(HttpStatus.OK,resposta.getStatusCode());
	}

}
