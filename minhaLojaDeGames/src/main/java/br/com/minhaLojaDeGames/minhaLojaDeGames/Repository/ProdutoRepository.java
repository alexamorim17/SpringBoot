package br.com.minhaLojaDeGames.minhaLojaDeGames.Repository;
import br.com.minhaLojaDeGames.minhaLojaDeGames.Model.Produto;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	
		public List<Produto> findAllBySinopseIgnoringCase(String sinopse);

	}