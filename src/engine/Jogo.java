package engine;

import data.model.Historico;
import data.persistence.HistoricoDAO;
import data.persistence.IPersistencia;
import view.MainUI; // Agora importa a MainUI corrigida do pacote 'view'

/**
 * Classe principal do motor do jogo (Engine).
 * Gerencia a lógica de pontuação e o acesso à persistência.
 */
public class Jogo {
    private int pontuacaoAtual = 0;
    private final IPersistencia persistencia;

    /**
     * O construtor define qual implementação de persistência será usada (Injeção de Dependência).
     */
    public Jogo() {
        // Usa a implementação mínima (arquivo de texto)
        this.persistencia = new HistoricoDAO();
    }

    /**
     * Inicializa a primeira tela do jogo.
     */
    public void iniciar() {
        exibirStatus();
    }

    /**
     * Processa a ação do usuário (Acertar ou Errar).
     * @param acertou Verdadeiro se o pedido foi acertado.
     */
    public void processarAcao(boolean acertou) {
        if (acertou) {
            pontuacaoAtual += 10;
            System.out.println("🎉 Pedido Acertado! Pontos: +10");
        } else {
            // Salva o histórico e reseta a pontuação ao errar.
            salvarHistorico("Cliente Teste", pontuacaoAtual);
            pontuacaoAtual = 0;
            System.out.println("😭 Pedido Errado! Pontuação Resetada.");
        }
        
        exibirStatus();
    }
    
    /**
     * Salva o registro no sistema de persistência.
     */
    private void salvarHistorico(String nomeCliente, int pontos) {
        Historico registro = new Historico(nomeCliente, pontos);
        persistencia.salvar(registro);
    }
    
    /**
     * Cria e exibe a interface gráfica.
     */
    private void exibirStatus() {
        // Usa a MainUI do pacote 'view'.
        new MainUI(this, "Pontuação: " + pontuacaoAtual);
    }
}