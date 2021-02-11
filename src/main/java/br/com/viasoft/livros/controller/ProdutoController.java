package br.com.viasoft.livros.controller;

import br.com.viasoft.livros.dto.ProdutoFormularioDTO;
import br.com.viasoft.livros.model.Produto;
import br.com.viasoft.livros.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProdutoController {


    @Autowired
    ProdutoService produtoService;

    @GetMapping("/produto")
    public String listaProduto(Model model) {

        List<Produto> lista = produtoService.findAll();
        model.addAttribute("lista", lista);

        return "produto/listaprodutos";
    }

    @GetMapping("/produto/autor/{autor}")
    public String listaProdutoByAutor(@PathVariable("autor") String autor, Model model) {
        List<Produto> lista = produtoService.findByAutor(autor);
        model.addAttribute("lista", lista);
        return "produto/listaprodutos";
    }

    @GetMapping("/produto/edit/{id}")
    public String editaProduto(@PathVariable("id") Long id, ProdutoFormularioDTO produtoFormularioDTO, Model model){
        Produto p1 = produtoService.findById(id).orElse(null);
        produtoFormularioDTO = new ProdutoFormularioDTO(p1);
        model.addAttribute("produto", p1);
        model.addAttribute("dto", produtoFormularioDTO);
        return "etebilu";
    }



    @GetMapping("/produto/{id}")
    public String ListaProdutoById(@PathVariable("id") Long id, ProdutoFormularioDTO produtoFormularioDTO, Model model){
        Produto p1 = produtoService.findById(id).orElse(null);
        if (p1 == null){
            return "redirect:/produto/";
        }
        model.addAttribute("produto",p1);

        return "produto/produtodetail";
    }

    @GetMapping("/produto/novo")
    public String getProduto(ProdutoFormularioDTO produtoFormularioDTO) {
        return "produto/formularioproduto";
    }

    @PostMapping("/produto/salvar")
    public String formProduto(@Valid ProdutoFormularioDTO produtoDTO, BindingResult result) {

        if (result.hasErrors()){
            return "redirect:/produto/";
        }
        Produto produto = produtoDTO.toProduto();
        produtoService.save(produto);
        return "redirect:/produto/" + produto.getId();
    }

    @PostMapping("/produto/salvar/{id}")
    public String saveProdutoExistente(@PathVariable("id") Long id, @Valid ProdutoFormularioDTO produtoDTO, BindingResult result) {

        if (result.hasErrors()) {
            return "etebilu";
        }
        Produto produto = produtoDTO.toProduto();
        produto.setId(id);
        produtoService.save(produto);
        return "redirect:/produto/" + produto.getId();
    }

    @GetMapping("produto/delete/{id}")
    public String removeProduto(@PathVariable("id") Long id, Principal principal) {
        var roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<String> cargos = roles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        if (cargos.get(0).equals("ROLE_ADM")){
            produtoService.delete(id);
            return "redirect:/produto/";
        }
        return "redirect:/produto/edit/" + id;
    }
}
