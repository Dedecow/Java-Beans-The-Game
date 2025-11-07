// data/model/Historico.java
package data.model;

/**
 * Representa um registro do histórico de pedidos (o que seria salvo no BD).
 * CORRIGIDO: Agora armazena o nome do JOGADOR, não do NPC cliente.
 */
public class Historico {
    private String nomeJogador;  
    private int pontuacao;
    private String dataPartida; 

    public Historico(String nomeJogador, int pontuacao) {
        this.nomeJogador = nomeJogador;
        this.pontuacao = pontuacao;
        this.dataPartida = java.time.LocalDate.now().toString();
    }

    // Getters
    public String getNomeJogador() {
        return nomeJogador;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public String getDataPartida() {
        return dataPartida;
    }
}