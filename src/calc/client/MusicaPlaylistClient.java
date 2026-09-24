package calc.client;

import calc.model.Musica;
import calc.model.Playlist;
import calc.service.MusicaPlaylistServer;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPFaultException;
import java.net.URL;
import java.util.List;

class MusicaPlaylistClient {

    public static void main(String args[]) throws Exception {
        URL url = new URL("http://127.0.0.1:9877/musicaplaylist?wsdl");
        QName qname = new QName(MusicaPlaylistServer.NAMESPACE, "MusicaPlaylistService");
        Service ws = Service.create(url, qname);
        MusicaPlaylistServer servico = ws.getPort(MusicaPlaylistServer.class);

        titulo("CADASTRAR MÚSICAS");
        int m1 = servico.cadastrarMusica("Bohemian Rhapsody", "Queen", 5.55f);
        int m2 = servico.cadastrarMusica("Imagine", "John Lennon", 3.07f);
        int m3 = servico.cadastrarMusica("Aquarela", "Toquinho", 4.2f);
        System.out.println("Cadastrada (ID): " + m1);
        System.out.println("Cadastrada (ID): " + m2);
        System.out.println("Cadastrada (ID): " + m3);

        titulo("LISTAR MÚSICAS");
        mostrarMusicas(servico.listarMusicas());

        titulo("BUSCAR MÚSICA (ID " + m2 + ")");
        System.out.println(servico.buscarMusica(m2));

        titulo("ATUALIZAR MÚSICA (ID " + m3 + ")");
        System.out.println("Antes:  " + servico.buscarMusica(m3));
        System.out.println("Depois: " + servico.atualizarMusica(m3, "Garota de Ipanema", "Tom Jobim", 5.1f));

        titulo("CADASTRAR PLAYLISTS");
        Playlist p1 = servico.cadastrarPlaylist("Clássicos do Rock");
        Playlist p2 = servico.cadastrarPlaylist("Para Relaxar");
        System.out.println("Cadastrada: " + p1);
        System.out.println("Cadastrada: " + p2);

        titulo("ADICIONAR MÚSICAS NAS PLAYLISTS");
        // A música 1 fica nas duas playlists (relacionamento N:N)
        servico.adicionarMusicaNaPlaylist(p1.getId(), m1);
        servico.adicionarMusicaNaPlaylist(p1.getId(), m2);
        servico.adicionarMusicaNaPlaylist(p2.getId(), m1);
        servico.adicionarMusicaNaPlaylist(p2.getId(), m3);
        System.out.println("Músicas adicionadas.");

        titulo("LISTAR PLAYLISTS");
        mostrarPlaylists(servico.listarPlaylists());

        titulo("PLAYLISTS QUE CONTÊM A MÚSICA (ID " + m1 + ")");
        mostrarPlaylists(servico.listarPlaylistsDaMusica(m1));

        titulo("BUSCAR PLAYLIST (ID " + p1.getId() + ")");
        mostrarPlaylist(servico.buscarPlaylist(p1.getId()));

        titulo("ATUALIZAR PLAYLIST (ID " + p2.getId() + ")");
        mostrarPlaylist(servico.atualizarPlaylist(p2.getId(), "MPB e Relax"));

        titulo("REMOVER MÚSICA (ID " + m2 + ") DA PLAYLIST " + p1.getId());
        mostrarPlaylist(servico.removerMusicaDaPlaylist(p1.getId(), m2));

        titulo("EXCLUIR MÚSICA (ID " + m1 + ") - sai de todas as playlists");
        servico.excluirMusica(m1);
        mostrarMusicas(servico.listarMusicas());
        mostrarPlaylists(servico.listarPlaylists());

        titulo("EXCLUIR PLAYLIST (ID " + p2.getId() + ")");
        servico.excluirPlaylist(p2.getId());
        mostrarPlaylists(servico.listarPlaylists());

        titulo("TESTES DE VALIDAÇÃO (devem dar erro)");
        try {
            servico.cadastrarMusica("   ", "Autor", 3f);
        } catch (SOAPFaultException e) {
            erro("Música com nome em branco", e);
        }
        try {
            servico.cadastrarMusica("Música", "", 3f);
        } catch (SOAPFaultException e) {
            erro("Música com autor em branco", e);
        }
        try {
            servico.cadastrarMusica("Música", "Autor", -2f);
        } catch (SOAPFaultException e) {
            erro("Música com duração negativa", e);
        }
        try {
            servico.atualizarMusica(m2, "Imagine", "John Lennon", 90f);
        } catch (SOAPFaultException e) {
            erro("Atualizar música com duração de 90 min", e);
        }
        try {
            servico.buscarMusica(99);
        } catch (SOAPFaultException e) {
            erro("Buscar música inexistente", e);
        }
        try {
            servico.cadastrarPlaylist("");
        } catch (SOAPFaultException e) {
            erro("Playlist com nome em branco", e);
        }
        try {
            servico.excluirPlaylist(p2.getId());
        } catch (SOAPFaultException e) {
            erro("Excluir playlist já excluída", e);
        }
        try {
            servico.adicionarMusicaNaPlaylist(p1.getId(), m2);
            servico.adicionarMusicaNaPlaylist(p1.getId(), m2);
        } catch (SOAPFaultException e) {
            erro("Adicionar a mesma música duas vezes", e);
        }
    }

    private static void titulo(String texto) {
        System.out.println();
        System.out.println("===== " + texto + " =====");
    }

    private static void mostrarMusicas(List<Musica> musicas) {
        if (musicas.isEmpty()) {
            System.out.println("(nenhuma música)");
        }
        for (Musica musica : musicas) {
            System.out.println(musica);
        }
    }

    private static void mostrarPlaylist(Playlist playlist) {
        System.out.println(playlist);
        for (Musica musica : playlist.getMusicas()) {
            System.out.println("    " + musica);
        }
    }

    private static void mostrarPlaylists(List<Playlist> playlists) {
        if (playlists.isEmpty()) {
            System.out.println("(nenhuma playlist)");
        }
        for (Playlist playlist : playlists) {
            mostrarPlaylist(playlist);
        }
    }

    private static void erro(String teste, SOAPFaultException e) {
        System.out.println(teste + " -> ERRO: " + e.getFault().getFaultString());
    }
}
