package com.alura.literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivros(@JsonAlias("title") String titulo,
                          @JsonAlias("authors") List<Autor> autor,
                          @JsonAlias("languages") List<String> idioma,
                          @JsonAlias("download_count") Integer downloads){
}
