package br.com.minhaLojaDeGames.minhaLojaDeGames.Controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.minhaLojaDeGames.minhaLojaDeGames.Model.Usuario;
import br.com.minhaLojaDeGames.minhaLojaDeGames.Model.UsuarioLogin;
import br.com.minhaLojaDeGames.minhaLojaDeGames.Repository.UsuarioRepository;
import br.com.minhaLojaDeGames.minhaLojaDeGames.Service.UsuarioService;

@RestController
@RequestMapping("usuarios/")
@CrossOrigin(origins = "*", allowedHeaders = "*")	
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	UsuarioRepository usuarioRepositorio;
	
	
	@PostMapping("/logar")
	ResponseEntity<UsuarioLogin> login(@RequestBody @Valid Optional<UsuarioLogin> user){
		return  usuarioService.autenticarUsuario(user)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
		
	}
	
	@PostMapping("/cadastrar")
	ResponseEntity<Usuario> cadastrar(@RequestBody @Valid Usuario usuario){
		return usuarioService.CadastrarUsuario(usuario)
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioService.atualizarUsuario(usuario)
			.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
}
