package com.example.demo.Controller;

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

import com.example.demo.Entity.Postagem;
import com.example.demo.Repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")

public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	
	
	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable long id){
		return repository.findById(id).map(resp-> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}	
	@GetMapping	("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	
	@PostMapping
	public ResponseEntity<Postagem> post (@Validated @RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED ).body(repository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<Postagem> put (@Validated @RequestBody Postagem postagem){
		return repository.findById(postagem.getId())
				.map(resposta -> ResponseEntity.ok().body(repository.save(postagem)))
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
