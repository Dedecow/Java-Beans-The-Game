package view;

import engine.Jogo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaJogo extends JPanel {
    private final Jogo jogo;
    private static final int LARGURA = 600;
    private static final int ALTURA = 400;
    
    private JLabel lblCliente;
    private JLabel lblPontuacao;

    public TelaJogo(Jogo jogo) {
        this.jogo = jogo;
        configurarUI();
    }
    
    private void configurarUI() {
        this.setPreferredSize(new Dimension(LARGURA, ALTURA));
        this.setBackground(CafeColors.FUNDO_BEGE);
        this.setLayout(new BorderLayout());
        
        // Painel de informações
        JPanel painelInfo = criarPainelInfo();
        
        // Painel de ações
        JPanel painelAcoes = criarPainelAcoes();
        
        // Botão de finalizar
        JButton btnFinalizar = criarBotao("FINALIZAR JOGO", CafeColors.MARROM_ESCURO, 
            e -> jogo.finalizarJogo());
        
        this.add(painelInfo, BorderLayout.NORTH);
        this.add(painelAcoes, BorderLayout.CENTER);
        this.add(btnFinalizar, BorderLayout.SOUTH);
    }
    
    private JPanel criarPainelInfo() {
        JPanel painel = new JPanel(new GridLayout(2, 1));
        painel.setBackground(CafeColors.MARROM_ESCURO);
        painel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        lblCliente = new JLabel("Cliente: " + jogo.getClienteAtual(), SwingConstants.CENTER);
        lblCliente.setFont(new Font("Monospaced", Font.BOLD, 18));
        lblCliente.setForeground(CafeColors.TEXTO_BRANCO);
        
        lblPontuacao = new JLabel("Pontuação: " + jogo.getPontuacao(), SwingConstants.CENTER);
        lblPontuacao.setFont(new Font("Monospaced", Font.PLAIN, 16));
        lblPontuacao.setForeground(CafeColors.TEXTO_BRANCO);
        
        painel.add(lblCliente);
        painel.add(lblPontuacao);
        
        return painel;
    }
    
    private JPanel criarPainelAcoes() {
        JPanel painel = new JPanel(new GridLayout(1, 2, 20, 0));
        painel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        painel.setBackground(CafeColors.FUNDO_BEGE);
        
        JButton btnAcertar = criarBotao("✅ ACERTAR", CafeColors.BOTAO_ACERTO, 
            e -> jogo.processarPedido(true));
        
        JButton btnErrar = criarBotao("❌ ERRAR", CafeColors.BOTAO_ERRO, 
            e -> jogo.processarPedido(false));
        
        painel.add(btnAcertar);
        painel.add(btnErrar);
        
        return painel;
    }
    
    private JButton criarBotao(String texto, Color cor, java.awt.event.ActionListener acao) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Monospaced", Font.BOLD, 14));
        botao.setBackground(cor);
        botao.setForeground(CafeColors.TEXTO_BRANCO);
        botao.setFocusable(false);
        botao.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        botao.addActionListener(acao);
        return botao;
    }
}