package br.com.minhaLojaDeGames.minhaLojaDeGames.Repository;
import br.com.minhaLojaDeGames.minhaLojaDeGames.Model.Categoria;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface categoriaRepository extends JpaRepository<Categoria, Long> {
	
	
		public List<Categoria> findAllByDescricaoIgnoringCase(String descricao);

	}

