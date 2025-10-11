package engine;

import data.model.Historico;
import data.persistence.IPersistencia; 
import data.persistence.HistoricoDAO; 
import view.MainUI;
import view.TelaGameOver;
import view.TelaInicial; // Importação necessária para manipular a TelaInicial

/**
 * Classe principal do motor do jogo (Engine).
 * Gerencia a lógica do jogo e persiste os dados.
 * 
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
        this.ui = new MainUI(this);
        // O MainUI em seu construtor deve mostrar a TelaInicial
    }
    
    // ------------------------------------------------------------------
    // MÉTODOS DE FLUXO DE JOGO CHAMADOS PELA VIEW
    // ------------------------------------------------------------------

    public void iniciarJogo() {
        this.pontuacaoAtual = 0; 
        
        // Passagem de tela
        ui.mostrarTela(new TelaGameOver(this)); 
        
        System.out.println("Partida Iniciada! (Simulando Tela Jogo com TelaGameOver)");
        ui.atualizarStatus("Nova Partida! Pontuação: 0");
    }

    public void reiniciarPartida() {
        this.pontuacaoAtual = 0; 
        
        ui.mostrarTela(new TelaInicial(this)); 
        
        System.out.println("Fim de Jogo. Retornando à Tela Inicial.");
    }
    
    // ------------------------------------------------------------------
    // MÉTODOS DE LÓGICA E STATUS
    // ------------------------------------------------------------------
        
    public int getPontuacao() {
        return pontuacaoAtual;
    }
}