package br.com.viasoft.livros.service;

import br.com.viasoft.livros.model.Cliente;
import framework.CrudService;

import java.util.List;

public interface ClienteService extends CrudService<Cliente, Long> {
    List<Cliente> findByNomeCliente(String nome);
}
