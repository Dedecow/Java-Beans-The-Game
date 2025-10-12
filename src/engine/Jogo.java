package engine;

import data.model.Historico;
import data.persistence.IPersistencia;
import data.persistence.HistoricoDAO;
import view.MainUI;
import view.Tela;

/**
 * Classe pura de lógica de negócio - não conhece as telas, apenas o orquestrador
 */
public class Jogo {
    private int pontuacaoAtual;
    private String clienteAtual;
    private final IPersistencia persistencia;
    private MainUI orquestrador;
    private boolean jogoAtivo;

    public Jogo() {
        this.persistencia = new HistoricoDAO();
        this.pontuacaoAtual = 0;
        this.jogoAtivo = false;
    }
    
    // ------------------------------------------------------------------
    // INJEÇÃO DE DEPENDÊNCIA
    // ------------------------------------------------------------------
    
    public void setUI(MainUI orquestrador) {
        this.orquestrador = orquestrador;
    }
    
    // ------------------------------------------------------------------
    // MÉTODOS DE FLUXO CHAMADOS PELAS TELAS (VIEW)
    // ------------------------------------------------------------------

    public void iniciar() {
        // Chamado pela Cafeteria - inicia o ciclo
        System.out.println("🎮 Jogo: Motor iniciado");
    }

    public void iniciarJogo() {
        this.pontuacaoAtual = 0;
        this.jogoAtivo = true;
        this.clienteAtual = "Cliente #1";
        
        // Chama o orquestrador para mudar de tela
        orquestrador.mostrarTela(Tela.JOGO);
        
        System.out.println("🎮 Jogo: Partida iniciada");
    }

    public void finalizarJogo() {
        this.jogoAtivo = false;
        
        // Salva histórico antes de mudar de tela
        salvarHistorico();
        
        // Chama orquestrador
        orquestrador.mostrarTela(Tela.GAME_OVER);
        
        System.out.println("🎮 Jogo: Partida finalizada - Pontuação: " + pontuacaoAtual);
    }

    public void reiniciarPartida() {
        this.pontuacaoAtual = 0;
        this.jogoAtivo = false;
        
        // Chama orquestrador
        orquestrador.mostrarTela(Tela.INICIAL);
        
        System.out.println("🎮 Jogo: Reiniciando para menu inicial");
    }
    
    // ------------------------------------------------------------------
    // MÉTODOS DE LÓGICA DE NEGÓCIO
    // ------------------------------------------------------------------
    
    public void processarPedido(boolean acertou) {
        if (!jogoAtivo) return;
        
        if (acertou) {
            pontuacaoAtual += 10;
            System.out.println("🎮 Jogo: Pedido correto! +10 pontos");
        } else {
            pontuacaoAtual = Math.max(0, pontuacaoAtual - 5);
            System.out.println("🎮 Jogo: Pedido errado! -5 pontos");
        }
        
        // Atualiza UI via orquestrador
        orquestrador.atualizarStatus("Em Jogo | Pontuação: " + pontuacaoAtual);
    }
    
    private void salvarHistorico() {
        try {
            Historico historico = new Historico(clienteAtual, pontuacaoAtual);
            persistencia.salvar(historico);
            System.out.println("💾 Jogo: Histórico salvo");
        } catch (Exception e) {
            System.err.println("❌ Jogo: Erro ao salvar histórico: " + e.getMessage());
        }
    }
    
    // ------------------------------------------------------------------
    // GETTERS (ESTADO PURO)
    // ------------------------------------------------------------------
        
    public int getPontuacao() {
        return pontuacaoAtual;
    }
    
    public String getClienteAtual() {
        return clienteAtual;
    }
    
    public boolean isJogoAtivo() {
        return jogoAtivo;
    }
}