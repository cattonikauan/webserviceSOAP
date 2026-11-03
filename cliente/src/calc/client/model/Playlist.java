package calc.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

// Versão do cliente: só guarda os dados que chegam do Web Service.
// As validações ficam no servidor, que devolve SOAP Fault se algo estiver errado.
@XmlAccessorType(XmlAccessType.FIELD)
public class Playlist {

    private int id;
    private String nomePlaylist;
    // Mesmo nome de elemento usado no servidor
    @XmlElement(name = "musica")
    private List<Musica> musicas = new ArrayList<>();

    public Playlist() {
    }

    public int getId() {
        return id;
    }

    public String getNomePlaylist() {
        return nomePlaylist;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    @Override
    public String toString() {
        return "#" + id + " - " + nomePlaylist + " (" + musicas.size() + " músicas)";
    }
}
