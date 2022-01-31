package br.com.minhaLojaDeGames.minhaLojaDeGames.Service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.minhaLojaDeGames.minhaLojaDeGames.Model.Usuario;
import br.com.minhaLojaDeGames.minhaLojaDeGames.Model.UsuarioLogin;
import br.com.minhaLojaDeGames.minhaLojaDeGames.Repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	public UsuarioRepository repositorio;

	public Optional<Usuario> CadastrarUsuario(Usuario usuario) {
		if (usuario.getIdade() <= 18) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário menor de idade !", null);}
		if (repositorio.findByUsuario(usuario.getUsuario()).isPresent()) {
			return Optional.empty();
		}
		usuario.setSenha(criptografarSenha(usuario.getSenha()));

		return Optional.of(repositorio.save(usuario));

	}

	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {

		Optional<Usuario> usuario = repositorio.findByUsuario(usuarioLogin.get().getUsuario());

		if (usuario.isPresent()) {
			if (compararSenha(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {

				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setToken(gerartoken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));
				usuarioLogin.get().setSenha(usuario.get().getSenha());

				return usuarioLogin;

			}
		}

		return Optional.empty();

	}

	public Optional<Usuario> atualizarUsuario(Usuario usuario) {

		if (repositorio.findById(usuario.getId()).isPresent()) {

			Optional<Usuario> buscaUsuario = repositorio.findByUsuario(usuario.getUsuario());

			if ((buscaUsuario.isPresent()) && (buscaUsuario.get().getId() != usuario.getId()))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

			usuario.setSenha(criptografarSenha(usuario.getSenha()));

			return Optional.ofNullable(repositorio.save(usuario));

		}

		return Optional.empty();

	}

	public String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);

	}

	public boolean compararSenha(String senhadoBanco, String senhaDigitada) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.matches(senhadoBanco, senhaDigitada);

	}

	public String gerartoken(String usuario, String senha) {

		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic64" + new String(tokenBase64);
	}
}
