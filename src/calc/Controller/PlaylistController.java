package calc.Controller;

import calc.Model.Musica;
import calc.Model.Playlist;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class PlaylistController {

    // Map simulando a tabela de playlists do banco de dados (chave = id)
    private final Map<Integer, Playlist> playlists = new LinkedHashMap<>();
    // Simula o auto incremento do id no banco
    private int proximoId = 1;
    // Usado para buscar as músicas que serão adicionadas/removidas das playlists
    private final MusicaController musicaController;

    public PlaylistController(MusicaController musicaController) {
        this.musicaController = musicaController;
    }

    // CREATE
    public Playlist cadastrar(String nomePlaylist) {
        // O construtor chama os sets, que validam os campos
        Playlist playlist = new Playlist(proximoId, nomePlaylist);
        playlists.put(playlist.getId(), playlist);
        proximoId++;
        return playlist;
    }

    // READ (uma)
    public Playlist buscar(int id) {
        Playlist playlist = playlists.get(id);
        // Se não encontrou a playlist...
        if (playlist == null) {
            throw new NoSuchElementException("Playlist com ID " + id + " não encontrada.");
        }
        return playlist;
    }

    // READ (todas)
    public List<Playlist> listar() {
        return new ArrayList<>(playlists.values());
    }

    // UPDATE
    public Playlist atualizar(int id, String nomePlaylist) {
        Playlist playlist = buscar(id);
        playlist.setNomePlaylist(nomePlaylist);
        return playlist;
    }

    // DELETE
    public void excluir(int id) {
        buscar(id); // lança exceção se não existir
        playlists.remove(id);
    }

    // ---- Relacionamento N:N entre playlist e música ----

    public Playlist adicionarMusica(int idPlaylist, int idMusica) {
        Playlist playlist = buscar(idPlaylist);
        Musica musica = musicaController.buscar(idMusica);
        playlist.adicionarMusica(musica);
        return playlist;
    }

    public Playlist removerMusica(int idPlaylist, int idMusica) {
        Playlist playlist = buscar(idPlaylist);
        Musica musica = musicaController.buscar(idMusica);
        playlist.removerMusica(musica);
        return playlist;
    }

    // Todas as playlists em que uma música aparece
    public List<Playlist> listarPorMusica(int idMusica) {
        Musica musica = musicaController.buscar(idMusica);
        List<Playlist> resultado = new ArrayList<>();
        for (Playlist playlist : playlists.values()) {
            if (playlist.getMusicas().contains(musica)) {
                resultado.add(playlist);
            }
        }
        return resultado;
    }

    // Chamado antes de excluir uma música, para ela não ficar "sobrando" nas playlists
    public void removerMusicaDeTodas(int idMusica) {
        Musica musica = musicaController.buscar(idMusica);
        for (Playlist playlist : playlists.values()) {
            playlist.getMusicas().remove(musica);
        }
    }
}
