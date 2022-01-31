package br.com.minhaLojaDeGames.minhaLojaDeGames.Model;



public class UsuarioLogin {
	
	private Long id;
	
	private String nome;
	

	private String senha;
	
	
	private String usuario;
	
	
	private String email;

	
	private String token;
	
	private int idade;


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}


	public Long getId() {
		return id;
	}


	public int getIdade() {
		return idade;
	}


	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	
	
	
	
}
