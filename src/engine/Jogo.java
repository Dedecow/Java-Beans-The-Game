package engine;

import data.model.Historico;
import data.persistence.IPersistencia; 
import data.persistence.HistoricoDAO; 
import view.MainUI;
import view.TelaGameOver;
import view.TelaInicial; // Importação necessária para manipular a TelaInicial

/**
 * Classe principal do motor do jogo (Engine).
 * Gerencia a lógica de pontuação e o acesso à persistência.
 */
public class Jogo {
    private int pontuacaoAtual = 0;
    private final IPersistencia persistencia;
    private MainUI ui; 

    public Jogo() {
        this.persistencia = new HistoricoDAO();
    }

    /**
     * Inicia o motor do jogo, criando a janela principal (UI).
     */
    public void iniciar() {
        // Inicializa a UI (apenas uma vez)
        this.ui = new MainUI(this);
        // O MainUI em seu construtor deve mostrar a TelaInicial
    }
    
    // ------------------------------------------------------------------
    // MÉTODOS DE FLUXO DE JOGO CHAMADOS PELA VIEW
    // ------------------------------------------------------------------

    /**
     * Método chamado pela Tela Inicial (Botão "INICIAR JOGO") para começar a partida.
     * Na nossa arquitetura atual, ele transiciona para a Tela GameOver (simulando a Tela Jogo).
     */
    public void iniciarJogo() {
        this.pontuacaoAtual = 0; 
        
        // TROCA 1: TelaInicial (Clique) -> Tela GameOver (Simulando Tela Jogo)
        ui.mostrarTela(new TelaGameOver(this)); 
        
        System.out.println("Partida Iniciada! (Simulando Tela Jogo com TelaGameOver)");
        ui.atualizarStatus("Nova Partida! Pontuação: 0");
    }

    /**
     * Reseta a pontuação e retorna para a Tela Inicial (Botão "PRÓXIMO CLIENTE" na TelaGameOver).
     */
    public void reiniciarPartida() {
        this.pontuacaoAtual = 0; 
        
        // TROCA 2: Tela GameOver (Fim de Jogo) -> Tela Inicial (Voltar ao Menu)
        ui.mostrarTela(new TelaInicial(this)); 
        
        System.out.println("Fim de Jogo. Retornando à Tela Inicial.");
    }
    
    // ------------------------------------------------------------------
    // MÉTODOS DE LÓGICA E STATUS
    // ------------------------------------------------------------------
    
    // Omitidos para manter o foco na lógica de telas.
    
    public int getPontuacao() {
        return pontuacaoAtual;
    }
}