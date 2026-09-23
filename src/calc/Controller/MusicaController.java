package calc.Controller;

import calc.Model.Musica;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class MusicaController {

    // Map simulando a tabela de músicas do banco de dados (chave = id)
    private final Map<Integer, Musica> musicas = new LinkedHashMap<>();
    // Simula o auto incremento do id no banco
    private int proximoId = 1;

    // CREATE
    public Musica cadastrar(String nome, String autor, float duracao) {
        // O construtor chama os sets, que validam os campos
        Musica musica = new Musica(proximoId, nome, autor, duracao);
        musicas.put(musica.getId(), musica);
        proximoId++;
        return musica;
    }

    // READ (uma)
    public Musica buscar(int id) {
        Musica musica = musicas.get(id);
        // Se não encontrou a música...
        if (musica == null) {
            throw new NoSuchElementException("Música com ID " + id + " não encontrada.");
        }
        return musica;
    }

    // READ (todas)
    public List<Musica> listar() {
        return new ArrayList<>(musicas.values());
    }

    // UPDATE
    public Musica atualizar(int id, String nome, String autor, float duracao) {
        Musica musica = buscar(id);
        // Valida todos os dados antes, para não alterar a música pela metade se algum campo for inválido
        Musica dadosNovos = new Musica(id, nome, autor, duracao);

        musica.setNome(dadosNovos.getNome());
        musica.setAutor(dadosNovos.getAutor());
        musica.setDuracao(dadosNovos.getDuracao());
        return musica;
    }

    // DELETE
    public void excluir(int id) {
        buscar(id); // lança exceção se não existir
        musicas.remove(id);
    }
}
