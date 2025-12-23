package com.alura.literalura.repository;

import com.alura.literalura.models.Livros;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivrosRepository extends JpaRepository<Livros, Long> {

}
