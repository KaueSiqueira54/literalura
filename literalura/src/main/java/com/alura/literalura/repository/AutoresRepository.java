package com.alura.literalura.repository;

import com.alura.literalura.models.Autor;
import com.alura.literalura.models.Autores;
import com.alura.literalura.models.Livros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutoresRepository extends JpaRepository<Autores, Long> {
    Optional<Autores> findByNomeIgnoreCase(String nome);

    @Query("""
       SELECT a FROM Autores a
       JOIN FETCH a.livros
       """)
    List<Autores> buscarAutoresComLivros();

    @Query("""
    SELECT a FROM Autores a
    WHERE a.anoNascimento <= :ano
      AND (a.anoMorte IS NULL OR a.anoMorte > :ano)
    """)
    List<Autores> autoresVivosNoAno(@Param("ano") Integer ano);

}
