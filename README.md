# 📚 Literalura

Aplicação **console em Java** desenvolvida com **Spring Boot**, que consome a API pública **Gutendex** para buscar livros e autores, persistindo os dados em um banco relacional e permitindo consultas via menu interativo.

Projeto desenvolvido como parte de um desafio de backend da formação Oracle Next Education, aplicando conceitos de **Spring Data JPA**, **Hibernate**, consumo de APIs REST e modelagem de entidades.

---

## 🧠 Visão Geral

A aplicação conecta-se à API **Gutendex** para buscar informações de livros clássicos disponíveis no Project Gutenberg.

Após a busca, os dados são convertidos de JSON para objetos Java, persistidos no banco de dados e disponibilizados para consultas posteriores.

### Exemplo de resposta da API

```json
{
  "id": 55752,
  "title": "Dom Casmurro",
  "authors": [
    {
      "name": "Machado de Assis",
      "birth_year": 1839,
      "death_year": 1908
    }
  ],
  "languages": ["pt"],
  "download_count": 1238
}

```
---

## 🚀 Funcionalidades

Menu interativo exibido no console:

1 - Buscar Livro pelo título<br/>
2 - Listar livros registrados<br/>
3 - Listar autores registrados<br/>
4 - Listar autores vivos em um determinado ano<br/>
0 - Sair<br/>

## Descrição das funcionalidades

- Buscar livro pelo título

- Consulta a API Gutendex

- Salva o livro no banco de dados

- Salva o autor, evitando duplicidade

- Relaciona livro ↔ autor

- Listar livros registrados

- Exibe todos os livros persistidos

- Listar autores registrados

- Exibe autores e seus respectivos livros

- Listar autores vivos em um determinado ano

- Filtra autores que estavam vivos no ano informado

## 🛠️ Tecnologias Utilizadas

- Java 17+

- Spring Boot

- Spring Data JPA

- Hibernate

- Maven

- PostgreSQL (ou outro banco relacional compatível)

- API externa: Gutendex

---

Autor: Kauê Siqueira
