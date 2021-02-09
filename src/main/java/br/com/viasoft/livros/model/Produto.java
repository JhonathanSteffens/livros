package br.com.viasoft.livros.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Produto implements Comparable<Produto> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String autor;
    private String imagem;

    @Override
    public int compareTo(Produto o) {
        return nome.compareTo(o.nome);
    }
}


