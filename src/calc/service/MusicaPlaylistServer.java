package calc.service;

import calc.model.Musica;
import calc.model.Playlist;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

// separar projeto, mudar musicaplaylistserver (interface),

@WebService(name = "MusicaPlaylist", targetNamespace = MusicaPlaylistServer.NAMESPACE)
public interface MusicaPlaylistServer {

    String NAMESPACE = "http://service.calc/";

    // ---- CRUD de Música (MusicaController) ----

    // alterado para int ao invés de Musica, pois o tipo tem que ser um tipo primitivo para o cliente entender o que tem que retornar
    @WebMethod
    int cadastrarMusica(String nome, String autor, float duracao);

    @WebMethod
    Musica buscarMusica(int id);

    @WebMethod
    List<Musica> listarMusicas();

    @WebMethod
    Musica atualizarMusica(int id,
                           String nome,
                           String autor,
                           float duracao);

    @WebMethod
    void excluirMusica(int id);

    // ---- CRUD de Playlist (PlaylistController) ----

    @WebMethod
    Playlist cadastrarPlaylist(String nomePlaylist);

    @WebMethod
    Playlist buscarPlaylist(int id);

    @WebMethod
    List<Playlist> listarPlaylists();

    @WebMethod
    Playlist atualizarPlaylist(int id,
                               String nomePlaylist);

    @WebMethod
    void excluirPlaylist(int id);

    // ---- Relacionamento N:N (uma música em várias playlists, uma playlist com várias músicas) ----

    @WebMethod
    Playlist adicionarMusicaNaPlaylist(int idPlaylist,
                                       int idMusica);

    @WebMethod
    Playlist removerMusicaDaPlaylist(int idPlaylist,
                                     int idMusica);

    @WebMethod
    List<Playlist> listarPlaylistsDaMusica(int idMusica);
}
