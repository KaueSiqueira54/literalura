package com.alura.literalura.principal;

import com.alura.literalura.dto.AutorDTO;
import com.alura.literalura.models.*;
import com.alura.literalura.repository.AutoresRepository;
import com.alura.literalura.repository.LivrosRepository;
import com.alura.literalura.services.ConsumoApi;
import com.alura.literalura.services.ConverteDados;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://gutendex.com/books/?search=";
    private List<DadosLivros> dadosLivros = new ArrayList<>();
    private LivrosRepository repository;
    private AutoresRepository autoresRepository;
    private List<Autores> autores = new ArrayList<>();
    private List<Livros> livros = new ArrayList<>();

    public Principal(LivrosRepository repository, AutoresRepository autoresRepository) {
        this.repository = repository;
        this.autoresRepository = autoresRepository;
    }

    public void exibirMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    Escolha uma opção:
                    
                    1 - Buscar Livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros pelo idioma
                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();
            switch (opcao) {
                case 1:
                    buscarLivros();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLivroPeloIdioma();
                    break;
                case 0:
                    System.out.println("Encerrando programa..");
                    break;
            }
        }
    }


    private void listarLivros() {
        livros = repository.findAll();
        livros.stream()
                .forEach(System.out::println);
    }

    private AutorDTO converterParaDTO(Autores autor) {
        List<String> titulos = autor.getLivros().stream()
                .map(Livros::getTitulo)
                .toList();

        return new AutorDTO(
                autor.getNome(),
                autor.getAnoNascimento(),
                autor.getAnoMorte(),
                titulos
        );
    }

    private void listarAutores() {
        autoresRepository.buscarAutoresComLivros()
                .stream()
                .map(this::converterParaDTO)
                .forEach(dto -> {
                    System.out.println("""
                    Autor: %s
                    Ano de nascimento: %s
                    Ano de falecimento: %s
                    Livros: %s
                    """.formatted(
                            dto.nome(),
                            dto.anoNascimento(),
                            dto.anoMorte(),
                            dto.livros()
                    ));
                });
    }

    private void listarAutoresVivos() {
        System.out.println("Digite o ano:");
        int ano = leitura.nextInt();
        leitura.nextLine();

        List<Autores> autores = autoresRepository.autoresVivosNoAno(ano);

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor vivo nesse ano.");
            return;
        }

        autores.forEach(System.out::println);
    }

    private void listarLivroPeloIdioma() {
        System.out.println("""
                Digite o idioma:
                pt - Português
                en - Inglês
                es - Espanhol
                fr - francês
                """);
        var idioma = leitura.nextLine();
        livros = repository.findByIdioma(idioma);
        if(livros.isEmpty()) {
            System.out.println("Nenhum livro neste idioma salvo.");
        }
        else {
            livros.stream()
                    .forEach(System.out::println);
        }
    }

    private void buscarLivros() {
        DadosLivros dados = getDadosLivros();

        if (dados.autor() == null || dados.autor().isEmpty()) {
            throw new RuntimeException("Livro sem autor definido");
        }

        var autorDados = dados.autor().get(0);

        Autores autor = autoresRepository
                .findByNomeIgnoreCase(autorDados.nome())
                .orElseGet(() -> autoresRepository.save(new Autores(autorDados)));

        Livros livro = new Livros(dados, autor);
        repository.save(livro);

        System.out.println(livro);
    }


    private DadosLivros getDadosLivros() {
        System.out.println("Digite o nome do livro para a busca: \n");
        var nomeLivro = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ", "+"));
        System.out.println(json);
        DadosBusca dados = conversor.obterDados(json, DadosBusca.class);
        if(dados.results().isEmpty()) {
            throw new RuntimeException("Nenhum livro encontrado");
        }
        DadosLivros livro = dados.results().get(0);
        return livro;
    }
}
