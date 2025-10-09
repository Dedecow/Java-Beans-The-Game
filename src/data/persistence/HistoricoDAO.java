package data.persistence;

import data.model.Historico;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Implementação DAO que salva o histórico em um arquivo de texto simples (Persistência Mínima).
 * No projeto final, esta classe seria substituída pela lógica de conexão ao MySQL.
 */
public class HistoricoDAO implements IPersistencia {
    private static final String ARQUIVO = "historico_jogo.txt"; 

    @Override
    public void salvar(Historico registro) {
        String linha = String.format("Cliente: %s | Pontos: %d\n",
            registro.getNomeCliente(),
            registro.getPontuacao()
        );

        try (FileWriter fw = new FileWriter(ARQUIVO, true)) {
            fw.write(linha);
            System.out.println("✅ Salvo localmente: " + linha.trim());
        } catch (IOException e) {
            System.err.println("❌ Erro ao salvar registro: " + e.getMessage());
        }
    }
}