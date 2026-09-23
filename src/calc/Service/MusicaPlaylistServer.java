package calc.Service;

import calc.Model.Musica;
import calc.Model.Playlist;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

// Estilo DOCUMENT (padrão), que permite retornar objetos e listas (List<Musica>, List<Playlist>)
@WebService(name = "MusicaPlaylist", targetNamespace = MusicaPlaylistServer.NAMESPACE)
public interface MusicaPlaylistServer {

    String NAMESPACE = "http://service.calc/";

    // ---- CRUD de Música (MusicaController) ----

    @WebMethod
    @WebResult(name = "musica")
    Musica cadastrarMusica(@WebParam(name = "nome") String nome,
                           @WebParam(name = "autor") String autor,
                           @WebParam(name = "duracao") float duracao);

    @WebMethod
    @WebResult(name = "musica")
    Musica buscarMusica(@WebParam(name = "id") int id);

    @WebMethod
    @WebResult(name = "musica")
    List<Musica> listarMusicas();

    @WebMethod
    @WebResult(name = "musica")
    Musica atualizarMusica(@WebParam(name = "id") int id,
                           @WebParam(name = "nome") String nome,
                           @WebParam(name = "autor") String autor,
                           @WebParam(name = "duracao") float duracao);

    @WebMethod
    void excluirMusica(@WebParam(name = "id") int id);

    // ---- CRUD de Playlist (PlaylistController) ----

    @WebMethod
    @WebResult(name = "playlist")
    Playlist cadastrarPlaylist(@WebParam(name = "nomePlaylist") String nomePlaylist);

    @WebMethod
    @WebResult(name = "playlist")
    Playlist buscarPlaylist(@WebParam(name = "id") int id);

    @WebMethod
    @WebResult(name = "playlist")
    List<Playlist> listarPlaylists();

    @WebMethod
    @WebResult(name = "playlist")
    Playlist atualizarPlaylist(@WebParam(name = "id") int id,
                               @WebParam(name = "nomePlaylist") String nomePlaylist);

    @WebMethod
    void excluirPlaylist(@WebParam(name = "id") int id);

    // ---- Relacionamento N:N (uma música em várias playlists, uma playlist com várias músicas) ----

    @WebMethod
    @WebResult(name = "playlist")
    Playlist adicionarMusicaNaPlaylist(@WebParam(name = "idPlaylist") int idPlaylist,
                                       @WebParam(name = "idMusica") int idMusica);

    @WebMethod
    @WebResult(name = "playlist")
    Playlist removerMusicaDaPlaylist(@WebParam(name = "idPlaylist") int idPlaylist,
                                     @WebParam(name = "idMusica") int idMusica);

    @WebMethod
    @WebResult(name = "playlist")
    List<Playlist> listarPlaylistsDaMusica(@WebParam(name = "idMusica") int idMusica);
}
