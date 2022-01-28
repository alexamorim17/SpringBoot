package com.example.demo.Service;

import java.nio.charset.Charset;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.Entity.Usuario;
import com.example.demo.Entity.UsuarioLogin;
import com.example.demo.Repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	public UsuarioRepository usuarioRepository;
	
	
	public Optional<Usuario> CadastrarUsuario(Usuario usuario){
		if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
			return Optional.empty();}
			usuario.setSenha(criptografarSenha(usuario.getSenha()));

			return Optional.of(usuarioRepository.save(usuario));
		
	
		 
	}

	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {

		Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());

		if (usuario.isPresent()) {
			if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {

				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setToken(gerarBasicToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));
				usuarioLogin.get().setSenha(usuario.get().getSenha());

				return usuarioLogin;

			}
		}	
		
		return Optional.empty();
		
	}
	
	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.encode(senha);
		
		
	}
	
	private boolean compararSenhas(String senha, String senhadoBanco) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.matches(senha, senhadoBanco);

	}
	
	private String gerarBasicToken(String usuario,String senha) {
		
		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
				return "Basic" + new String(tokenBase64);
	}


	public Optional<Usuario> atualizarUsuario(Usuario usuario) {

		
		if(usuarioRepository.findById(usuario.getId()).isPresent()) {
			
			Optional<Usuario> buscaUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
			
			if ( (buscaUsuario.isPresent()) && ( buscaUsuario.get().getId() != usuario.getId()))
				throw new ResponseStatusException(
						HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
			
			usuario.setSenha(criptografarSenha(usuario.getSenha()));

			return Optional.ofNullable(usuarioRepository.save(usuario));
			
		}
		
			return Optional.empty();
	
	}	
}