package com.alura.literalura.models;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autores autor;
    private String idioma;
    private Integer downloads;

    //Constructors

    public Livros() {}

    public Livros(DadosLivros dadosLivros, Autores autor) {
        this.titulo = dadosLivros.titulo();
        this.autor = autor;
        this.idioma = dadosLivros.idioma() != null
                ? String.join(", ", dadosLivros.idioma())
                : null;
        this.downloads = dadosLivros.downloads();
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autores getAutor() {
        return autor;
    }

    public void setAutor(Autores autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        return """
                --- LIVRO ---
                Titulo: %s
                Autor: %s
                Idioma: %s
                Número de downloads: %s
                """.formatted(titulo, autor.getNome(), idioma, downloads);
    }
}
