package calc.client.service;

import calc.client.model.Musica;
import calc.client.model.Playlist;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

// Cópia da interface do servidor, usada pelo cliente para chamar o Web Service.
// O name e o targetNamespace precisam ser os mesmos do servidor para o cliente achar o serviço no WSDL.
@WebService(name = "MusicaPlaylist", targetNamespace = MusicaPlaylistServer.NAMESPACE)
public interface MusicaPlaylistServer {

    String NAMESPACE = "http://service.calc/";

    // ---- CRUD de Música ----

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

    // ---- CRUD de Playlist ----

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

    // ---- Relacionamento N:N ----

    @WebMethod
    Playlist adicionarMusicaNaPlaylist(int idPlaylist,
                                       int idMusica);

    @WebMethod
    Playlist removerMusicaDaPlaylist(int idPlaylist,
                                     int idMusica);

    @WebMethod
    List<Playlist> listarPlaylistsDaMusica(int idMusica);
}
