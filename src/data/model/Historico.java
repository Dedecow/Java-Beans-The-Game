package data.model;

/**
 * Representa um registro do histórico de pedidos (o que seria salvo no BD).
 * Esta é a classe de modelo (o 'Usuario.java' do seu exemplo).
 */
public class Historico {
    private String nomeCliente;
    private int pontuacao;

    public Historico(String nomeCliente, int pontuacao) {
        this.nomeCliente = nomeCliente;
        this.pontuacao = pontuacao;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public int getPontuacao() {
        return pontuacao;
    }
}