package br.com.viasoft.livros.service.impl;

import br.com.viasoft.livros.event.ClientePresave;
import br.com.viasoft.livros.model.Cliente;
import br.com.viasoft.livros.model.Produto;
import br.com.viasoft.livros.repository.ClienteRepository;
import br.com.viasoft.livros.service.ClienteService;
import framework.CrudService;
import framework.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClienteServiceImpl extends CrudServiceImpl<Cliente, Long> implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    private ApplicationEventPublisher appPublisher;


    @Override
    public JpaRepository<Cliente, Long> getRepository() {
        return clienteRepository;
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> result = getRepository().findAll();

        Collections.sort(result);
        return result;
    }

    @Override
    public List<Cliente> findByNomeCliente(String nome) {

        return clienteRepository.findAllByNomeContainsIgnoreCase(nome);
    }

    @Override
    public void preSave(Cliente cliente) {
        appPublisher.publishEvent(new ClientePresave(this, cliente));

    }

    @Override
    public void postSave(Cliente cliente) {
        System.out.println(String.format("Passei pelo post save, o id é %d",cliente.getId()));
    }

}
