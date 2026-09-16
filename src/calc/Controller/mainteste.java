package calc.Controller;

import calc.Model.Musica;
import calc.Model.Playlist;

public class mainteste {
    public static void main(String[] args) {

        Playlist playlist1 = new Playlist();
        playlist1.setNomePlaylist("playlist1");
        Playlist playlist2 = new Playlist();
        playlist2.setNomePlaylist("playlist2");
        Musica musica1 = new Musica();
        musica1.setNome("musica1");
        Musica musica2 = new Musica();
        musica2.setNome("musica2");

        playlist1.adicionarMusica(musica1);
        playlist1.adicionarMusica(musica2);
    }
}
