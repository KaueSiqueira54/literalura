package com.alura.literalura.dto;

import java.util.List;

public record AutorDTO(String nome,
                       Integer anoNascimento,
                       Integer anoMorte,
                       List<String> livros) {
}
