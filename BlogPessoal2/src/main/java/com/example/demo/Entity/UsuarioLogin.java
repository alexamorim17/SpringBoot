package com.example.demo.Entity;

import java.math.BigDecimal;

public class UsuarioLogin {


	private BigDecimal id;

	private String nome;

	private String usuario;

	private String senha;

	

	private String token;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal bigDecimal) {
		this.id = bigDecimal;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	
}
