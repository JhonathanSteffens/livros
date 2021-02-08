package br.com.viasoft.livros.controller;

import br.com.viasoft.livros.model.Cliente;
import br.com.viasoft.livros.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;


    @GetMapping("/cliente")
    public String getCliente(Model model){

        List<Cliente> lista = clienteRepository.findAll();
        model.addAttribute("lista", lista);

        return "cliente";
    }
}
