package br.com.minhaLojaDeGames.minhaLojaDeGames.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity //Define 
@Table(name = "tb_categoria")//Define o nome da tabela
public class Categoria{
	
	@Id// Define a chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY)// Auto-increment
	private Long id;
	
	private String descricao;
	
	@Size(min = 3, max = 15)// Define o mínimo e máximo
	@NotNull //Define que o atributo não pode ser nulo
	private String nome;// Define o nome do jogo
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	
	
	
	}
	

