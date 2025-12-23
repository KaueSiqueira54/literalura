package com.alura.literalura.principal;

import com.alura.literalura.models.Autor;
import com.alura.literalura.models.DadosBusca;
import com.alura.literalura.models.DadosLivros;
import com.alura.literalura.models.Livros;
import com.alura.literalura.repository.LivrosRepository;
import com.alura.literalura.services.ConsumoApi;
import com.alura.literalura.services.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://gutendex.com/books/?search=";
    private List<DadosLivros> dadosLivros = new ArrayList<>();
    private LivrosRepository repository;
    private List<Livros> livros = new ArrayList<>();

    public Principal(LivrosRepository repository) {
        this.repository = repository;
    }

    public void exibirMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    Escolha uma opção:
                    
                    1 - Buscar Livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores
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
                case 0:
                    System.out.println("Encerrando programa..");
                    break;
            }
        }
    }

    private void listarAutores() {

    }

    private void listarLivros() {
        livros = repository.findAll();
        livros.stream()
                .forEach(System.out::println);
    }

    private void buscarLivros() {
        DadosLivros dados = getDadosLivros();
        Livros livros = new Livros(dados);
        repository.save(livros);
        System.out.println(dados);
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
