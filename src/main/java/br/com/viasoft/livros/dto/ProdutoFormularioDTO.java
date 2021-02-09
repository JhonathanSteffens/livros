package br.com.viasoft.livros.dto;

import br.com.viasoft.livros.model.Produto;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class ProdutoFormularioDTO {
   @NotEmpty(message = "Coloca um nome aí parça")
   private String nome;

   @NotEmpty(message = "Coloca um autor aí parça")
   private String autor;
   private String imagem;

   public Produto toProduto(){
       Produto produto = new Produto();
       produto.setNome(this.nome);
       produto.setAutor(this.autor);
       produto.setImagem(this.imagem);

       return produto;

   }
}
