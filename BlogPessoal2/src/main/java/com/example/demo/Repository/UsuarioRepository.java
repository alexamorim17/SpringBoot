package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
	
	
	public List<Usuario> findAllByUsuarioContainingIgnoringCase(String usuario);
	public Optional<Usuario> findByUsuario(String usuario);


}
