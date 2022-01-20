package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Tema;

public interface TemaRepository extends JpaRepository<Tema,Long>{
	
	public List<Tema> findAllByDescricaoIgnoringCase(String descricao);
	
	

}
