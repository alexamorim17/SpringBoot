package br.com.minhaLojaDeGames.minhaLojaDeGames.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity //Define 
@Table(name = "tb_produto")
public class Produto{
	
	@Id// Define a chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY)// Auto-increment
	private Long id;
	
	@Size(min = 20, max = 255)// Define o mínimo e máximo
	@NotNull //Define que o atributo não pode ser nulo
	private String sinopse;
	
	private double preco; 
	
	private boolean multiplayer;//Define se é multiplayer ou não
	
	@Size(min = 3, max = 15)// Define o mínimo e máximo
	@NotNull //Define que o atributo não pode ser nulo
	private String nome;// Define o nome do jogo
	
	@ManyToOne() // Relação de um para muitos
	@JsonIgnoreProperties("produto") // Impede de ocorrer um looping
    public Categoria categoria;
	

	
	
	//Getters e Setters
	
	
	
	public long getId() {
		return id;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSinopse() {
		return sinopse;
	}
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	public boolean isMultiplayer() {
		return multiplayer;
	}
	public void setMultiplayer(boolean multiplayer) {
		this.multiplayer = multiplayer;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
}
