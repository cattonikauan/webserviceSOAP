package calc.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

// FIELD: o JAXB converte o objeto para XML (e de volta) usando os atributos,
// assim a validação dos sets fica só para quem cria/altera a música no código
@XmlAccessorType(XmlAccessType.FIELD)
public class Musica {

    private int id;
    private String nome;
    private String autor;
    private float duracao; // em minutos

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
            throw new IllegalArgumentException("O ID da música deve ser maior que zero.");
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
        // Limite de tamanho para o nome
        if (nome.trim().length() > 100) {
            throw new IllegalArgumentException("O nome da música deve ter no máximo 100 caracteres.");
        }

        this.nome = nome.trim();
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        // .trim para tirar espaços em branco e verificar se está vazio, se estiver...
        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("O autor não pode estar em branco.");
        }
        // Limite de tamanho para o autor
        if (autor.trim().length() > 100) {
            throw new IllegalArgumentException("O autor deve ter no máximo 100 caracteres.");
        }

        this.autor = autor.trim();
    }

    public float getDuracao() {
        return duracao;
    }

    public void setDuracao(float duracao) {
        // NaN e infinito não são durações válidas
        if (Float.isNaN(duracao) || Float.isInfinite(duracao)) {
            throw new IllegalArgumentException("A duração informada é inválida.");
        }
        // verifica se duração é menor ou igual a 0, se for...
        if (duracao <= 0) {
            throw new IllegalArgumentException("A duração deve ser maior que zero.");
        }
        // Limite de 60 minutos para uma música
        if (duracao > 60) {
            throw new IllegalArgumentException("A duração deve ser de no máximo 60 minutos.");
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

    @Override
    public String toString() {
        return "#" + id + " - " + nome + " (" + autor + ", " + duracao + " min)";
    }
}
