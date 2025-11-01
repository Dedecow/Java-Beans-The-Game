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
        
        // Adiciona a interface 'Atualizavel' para dar
        // a chance da tela carregar dados do jogo
        if (novaTela instanceof Atualizavel) {
            try {
                ((Atualizavel) novaTela).atualizarInfo();
            } catch (Exception e) {
                System.err.println("⚠️ Falha ao atualizar tela " + tipoTela.name() + ": " + e.getMessage());
            }
        }
        
        getContentPane().removeAll();
        getContentPane().add(novaTela, BorderLayout.CENTER);
        revalidate();
        repaint();
        atualizarTitulo(tipoTela);
    }

    private JPanel criarTela(Tela tipoTela) {
        // O 'switch expression' é mais limpo
        return switch (tipoTela) { 
            case INICIAL -> new TelaInicial(jogo);
            case JOGO -> new TelaJogo(jogo);
            case GAME_OVER -> new TelaGameOver(jogo); 
            case RECEITA -> new TelaReceita(jogo);
            case PREPARO -> new TelaPreparo(jogo);
            case RANKING -> new TelaRanking(jogo); 
            case DETALHES_CLIENTE -> new TelaDetalhesCliente(jogo); 
            case CONFIGURACOES -> new TelaConfiguracoes(jogo);
            
            // --- NOVAS LINHAS ADICIONADAS ---
            case CLIENTE_CHEGANDO -> new TelaClienteChegando(jogo);
            case SOBRE -> new TelaSobre(jogo);
            
            // Default fallback (removemos os outros enums não implementados)
            default -> new TelaInicial(jogo);
        };
    }

    private void atualizarTitulo(Tela tela) {
        setTitle("Java Beans - " + tela.name() + " | Pontuação: " + jogo.getPontuacao());
    }

    public void atualizarStatus(String status) {
        setTitle("Java Beans - " + status);
    }
    
    /**
     * NOVO: Interface para garantir que as telas possam
     * ser atualizadas ANTES de serem exibidas.
     */
    public interface Atualizavel {
        void atualizarInfo();
    }
}