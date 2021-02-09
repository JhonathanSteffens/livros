package br.com.viasoft.livros.repository;

import br.com.viasoft.livros.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findAllByAutorIgnoreCase(String autor);
    List<Produto> findAllByAutorContainsIgnoreCase(String autor);
}
