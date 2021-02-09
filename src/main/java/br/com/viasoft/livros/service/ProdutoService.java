package br.com.viasoft.livros.service;

import br.com.viasoft.livros.model.Produto;
import framework.CrudService;

import java.util.List;

public interface ProdutoService extends CrudService<Produto, Long> {
    List<Produto> findByAutor(String autor);
}
