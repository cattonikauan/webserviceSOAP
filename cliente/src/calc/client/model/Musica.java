package calc.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

// Versão do cliente: só guarda os dados que chegam do Web Service.
// As validações ficam no servidor, que devolve SOAP Fault se algo estiver errado.
@XmlAccessorType(XmlAccessType.FIELD)
public class Musica {

    private int id;
    private String nome;
    private String autor;
    private float duracao; // em minutos

    public Musica() {
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getAutor() {
        return autor;
    }

    public float getDuracao() {
        return duracao;
    }

    @Override
    public String toString() {
        return "#" + id + " - " + nome + " (" + autor + ", " + duracao + " min)";
    }
}
