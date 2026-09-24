package calc.service;

import calc.controller.MusicaController;
import calc.controller.PlaylistController;
import calc.model.Musica;
import calc.model.Playlist;

import javax.jws.WebService;
import java.util.List;

// Cada método só repassa a chamada para o controller correspondente.
// As exceções lançadas pelos models/controllers viram SOAP Fault para o cliente.
@WebService(endpointInterface = "calc.Service.MusicaPlaylistServer",
        targetNamespace = MusicaPlaylistServer.NAMESPACE,
        serviceName = "MusicaPlaylistService",
        portName = "MusicaPlaylistPort")
public class MusicaPlaylistServerImpl implements MusicaPlaylistServer {

    private final MusicaController musicaController = new MusicaController();
    private final PlaylistController playlistController = new PlaylistController(musicaController);

    // ---- Música ----

    public int cadastrarMusica(String nome, String autor, float duracao) {
        Musica musica = musicaController.cadastrar(nome, autor, duracao);
        return musica.getId();
    }

    public Musica buscarMusica(int id) {
        return musicaController.buscar(id);
    }

    public List<Musica> listarMusicas() {
        return musicaController.listar();
    }

    public Musica atualizarMusica(int id, String nome, String autor, float duracao) {
        return musicaController.atualizar(id, nome, autor, duracao);
    }

    public void excluirMusica(int id) {
        // Tira a música das playlists antes de excluir
        playlistController.removerMusicaDeTodas(id);
        musicaController.excluir(id);
    }

    // ---- Playlist ----

    public Playlist cadastrarPlaylist(String nomePlaylist) {
        return playlistController.cadastrar(nomePlaylist);
    }

    public Playlist buscarPlaylist(int id) {
        return playlistController.buscar(id);
    }

    public List<Playlist> listarPlaylists() {
        return playlistController.listar();
    }

    public Playlist atualizarPlaylist(int id, String nomePlaylist) {
        return playlistController.atualizar(id, nomePlaylist);
    }

    public void excluirPlaylist(int id) {
        playlistController.excluir(id);
    }

    // ---- Relacionamento ----

    public Playlist adicionarMusicaNaPlaylist(int idPlaylist, int idMusica) {
        return playlistController.adicionarMusica(idPlaylist, idMusica);
    }

    public Playlist removerMusicaDaPlaylist(int idPlaylist, int idMusica) {
        return playlistController.removerMusica(idPlaylist, idMusica);
    }

    public List<Playlist> listarPlaylistsDaMusica(int idMusica) {
        return playlistController.listarPorMusica(idMusica);
    }
}
