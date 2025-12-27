package com.alura.literalura.models;

import com.alura.literalura.dto.AutorDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private Integer anoNascimento;
    private Integer anoMorte;
    @OneToMany(mappedBy = "autor")
    private List<Livros> livros = new ArrayList<>();

    public Autores() {};

    public Autores(Autor autor) {
        this.nome = autor.nome();
        this.anoNascimento = autor.dataNascimento();
        this.anoMorte = autor.anoMorte();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // getters e setters corretos
    public List<Livros> getLivros() {
        return livros;
    }

    public void setLivros(List<Livros> livros) {
        this.livros = livros;
    }

    public String getNome() {
        return nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoMorte() {
        return anoMorte;
    }

    public void setAnoMorte(Integer anoMorte) {
        this.anoMorte = anoMorte;
    }

    @Override
    public String toString() {
        return
                """
                Autor: %s
                Ano de nascimento: %s
                Ano de falecimento: %s
                        """.formatted(nome, anoNascimento, anoMorte);
    }
}
