package view;

import engine.Jogo;
import java.awt.*;
import javax.swing.*;

public class MainUI extends JFrame {
    private final Jogo jogo;
    
    public MainUI(Jogo jogo) {
        this.jogo = jogo;
        this.jogo.setUI(this);
        
        configurarJanela();
        mostrarTela(Tela.INICIAL);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void configurarJanela() {
        setTitle("Java Beans - Cafeteria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        setLayout(new BorderLayout());
    }

    public void mostrarTela(Tela tipoTela) {
        JPanel novaTela = criarTela(tipoTela);
        getContentPane().removeAll();
        getContentPane().add(novaTela, BorderLayout.CENTER);
        revalidate();
        repaint();
        atualizarTitulo(tipoTela);
    }

    private JPanel criarTela(Tela tipoTela) {
        return switch (tipoTela) { 
            case INICIAL -> new TelaInicial(jogo);
            case JOGO -> new TelaJogo(jogo);
            case GAME_OVER -> new TelaGameOver(jogo); 
            case RECEITA -> new TelaReceita(jogo);
            case PREPARO -> new TelaPreparo(jogo);
            case RANKING -> new TelaRanking(jogo); 
            case DETALHES_CLIENTE -> new TelaDetalhesCliente(jogo); 
            
            // --- NOVA LINHA ADICIONADA ---
            case CONFIGURACOES -> new TelaConfiguracoes(jogo); // Agora o MainUI sabe criar a tela
            
            // Default fallback
            default -> new TelaInicial(jogo);
        };
    }

    private void atualizarTitulo(Tela tela) {
        setTitle("Java Beans - " + tela.name() + " | Pontuação: " + jogo.getPontuacao());
    }

    public void atualizarStatus(String status) {
        setTitle("Java Beans - " + status);
    }
}