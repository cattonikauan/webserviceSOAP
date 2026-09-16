package calc.Model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private int id;
    private String nomePlaylist;
    // Lista de musicas que a playlist contém
    private List<Musica> musicas = new ArrayList<>();

    public Playlist() {
    }

    public Playlist(int id, String nomePlaylist) {
        setId(id);
        setNomePlaylist(nomePlaylist);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        // Verifica se o id é menor ou igual a 0, se for...
        if (id <= 0) {
            throw new IllegalArgumentException("O ID deve ser maior que zero.");
        }

        this.id = id;
    }

    public String getNomePlaylist() {
        return nomePlaylist;
    }

    public void setNomePlaylist(String nomePlaylist) {
        // .trim para tirar espaços em branco e verificar se está vazio, se estiver...
        if (nomePlaylist == null || nomePlaylist.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O nome da playlist não pode estar em branco."
            );
        }

        this.nomePlaylist = nomePlaylist;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void adicionarMusica(Musica musica) {
        // Verifica se a música passada como argumento é nula, se for...
        if (musica == null) {
            throw new IllegalArgumentException("A música não pode ser nula.");
        }

        if (!musicas.contains(musica)) {
            musicas.add(musica);
        }
    }

    public void removerMusica(Musica musica) {
        // Verifica se o argumento passado é nulo, se for...
        if (musica == null) {
            throw new IllegalArgumentException("A música não pode ser nula.");
        }

        musicas.remove(musica);
    }

    // Mostra todas as musicas que a playlist contém
    public void mostrarMusicas () {
        for (Musica musica : musicas){
            System.out.println(musica.getNome());
        }
    }

    public void mostrarAtributos () {
        System.out.println("Atributos:");
        System.out.println(this.id);
        System.out.println(this.nomePlaylist);
        System.out.println("Lista de músicas:");
        mostrarMusicas();
    }
}