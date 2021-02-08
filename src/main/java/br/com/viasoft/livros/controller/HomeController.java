package br.com.viasoft.livros.controller;

import br.com.viasoft.livros.model.Produto;
import br.com.viasoft.livros.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProdutoRepository produtoRepository;


    @GetMapping("/")
    public String getHome(Model model){

        List<Produto> lista = produtoRepository.findAll();
        model.addAttribute("lista", lista);

        return "home";
    }
}
