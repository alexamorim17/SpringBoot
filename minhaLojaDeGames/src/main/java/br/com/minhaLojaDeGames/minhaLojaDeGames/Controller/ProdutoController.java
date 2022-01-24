package br.com.minhaLojaDeGames.minhaLojaDeGames.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.minhaLojaDeGames.minhaLojaDeGames.Model.Produto;
import br.com.minhaLojaDeGames.minhaLojaDeGames.Repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin("*")

public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	//Serviço de injeção de dependencia do Spring
	// Não é possível instanciar um interface, para isso é necessário usar @Autowired 
	
	@GetMapping
	public ResponseEntity<List<Produto>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Produto> GetById(@PathVariable long id){
		return repository.findById(id).map(resp-> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}	
	@GetMapping	("/descricao/{descricao}")
	public ResponseEntity<List<Produto>> getByTitulo(@PathVariable String descricao){
		return ResponseEntity.ok(repository.findAllBySinopseIgnoringCase(descricao));
	}
	
	
	@PostMapping
	public ResponseEntity<Produto> post (@Validated @RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED ).body(repository.save(produto));
	}
	
	@PutMapping
	public ResponseEntity<Produto> put (@Validated @RequestBody Produto produto){
		return repository.findById(produto.getId())
				.map(resposta -> ResponseEntity.ok().body(repository.save(produto)))
				.orElse(ResponseEntity.notFound().build());
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePostagem(@PathVariable long id){
		return repository.findById(id)
		.map(resposta ->{
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		})
		.orElse(ResponseEntity.notFound().build());
	}
	
	

}
