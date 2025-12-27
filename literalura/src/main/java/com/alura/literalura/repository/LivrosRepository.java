package com.alura.literalura.repository;

import com.alura.literalura.models.Livros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivrosRepository extends JpaRepository<Livros, Long> {

    List<Livros> findByIdioma(String idioma);
}
