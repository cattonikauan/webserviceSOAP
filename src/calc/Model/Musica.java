package calc.Model;

import java.util.ArrayList;
import java.util.List;

public class Musica {

    private int id;
    private String nome;
    private String autor;
    private float duracao;

    public Musica() {
    }

    public Musica(int id, String nome, String autor, float duracao) {
        setId(id);
        setNome(nome);
        setAutor(autor);
        setDuracao(duracao);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        // Caso o id seja 0 ou menor...
        if (id <= 0) {
            throw new IllegalArgumentException("O ID deve ser maior que zero.");
        }

        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        // .trim para tirar espaços em branco e verificar se está vazio, se estiver...
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da música não pode estar em branco.");
        }

        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        // .trim para tirar espaços em branco e verificar se está vazio, se estiver...
        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("O autor não pode estar em branco.");
        }

        this.autor = autor;
    }

    public float getDuracao() {
        return duracao;
    }

    public void setDuracao(float duracao) {
        // verifica se duração é menor ou igual a 0, se for...
        if (duracao <= 0) {
            throw new IllegalArgumentException("A duração deve ser maior que zero.");
        }

        this.duracao = duracao;
    }

    public void mostrarAtributos () {
        System.out.println("Atributos:");
        System.out.println(this.id);
        System.out.println(this.nome);
        System.out.println(this.autor);
        System.out.println(this.duracao);

    }
}