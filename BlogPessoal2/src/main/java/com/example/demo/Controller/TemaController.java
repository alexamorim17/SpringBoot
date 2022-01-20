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

import com.example.demo.Entity.Tema;
import com.example.demo.Repository.TemaRepository;

@RestController
@RequestMapping("/tema")
@CrossOrigin(origins = "*", allowedHeaders ="*")
public class TemaController {
	
	@Autowired 
	private TemaRepository trepositorio;
	
	@GetMapping
	ResponseEntity <List<Tema>> GetAll(){
		
		return ResponseEntity.ok(trepositorio.findAll());
	
	}
	@GetMapping("/{id}")
	public ResponseEntity <Tema> getById(@PathVariable Long id){
		return trepositorio.findById(id)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.notFound().build());
		
	}
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity <List<Tema>> GetByDescricao(@PathVariable String descricao){
		return ResponseEntity.ok(trepositorio.findAllByDescricaoIgnoringCase(descricao));
		
	}

	@PostMapping
	public ResponseEntity<Tema> post (@Validated @RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.CREATED ).body(trepositorio.save(tema));
	}
	@PutMapping
	public ResponseEntity<Tema> put (@Validated @RequestBody Tema tema){
		return trepositorio.findById(tema.getId())
				.map(resposta -> ResponseEntity.ok().body(trepositorio.save(tema)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	
	public ResponseEntity<?> deleteTema(@PathVariable Long id){
		return trepositorio.findById(id)
				.map(resposta ->{
					trepositorio.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
		
	}
	}


