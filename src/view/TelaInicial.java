package view;

import engine.Jogo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicial extends JPanel {

    private final Jogo jogo;
    private static final int LARGURA = 600;
    private static final int ALTURA = 400;
    
    public TelaInicial(Jogo jogo) {
        this.jogo = jogo;

        this.setPreferredSize(new Dimension(LARGURA, ALTURA));
        this.setBackground(CafeColors.FUNDO_CAFE_ESCURO);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;

        // --- 1. Ícone / Logo ---
        JLabel lblIcone = new JLabel("☕");
        lblIcone.setFont(new Font("Monospaced", Font.PLAIN, 48));
        lblIcone.setForeground(CafeColors.TEXTO_BRANCO);
        gbc.gridy = 0; 
        this.add(lblIcone, gbc);

        JLabel lblTitulo = new JLabel("JAVABEANS");
        lblTitulo.setFont(new Font("Monospaced", Font.BOLD, 32));
        lblTitulo.setForeground(CafeColors.TEXTO_BRANCO);
        gbc.gridy = 1; 
        this.add(lblTitulo, gbc);

        JLabel lblSubtitulo = new JLabel("CAFETERIA");
        lblSubtitulo.setFont(new Font("Monospaced", Font.PLAIN, 18));
        lblSubtitulo.setForeground(CafeColors.TEXTO_BRANCO);
        gbc.gridy = 2; 
        this.add(lblSubtitulo, gbc);

        // --- 2. Botão "INICIAR JOGO" ---
        JButton btnIniciar = new JButton("INICIAR JOGO");
        btnIniciar.setFont(new Font("Monospaced", Font.BOLD, 20));
        btnIniciar.setFocusable(false);
        btnIniciar.setBackground(CafeColors.MARROM_ESCURO);
        btnIniciar.setForeground(CafeColors.TEXTO_BRANCO);
        btnIniciar.addActionListener(e -> jogo.iniciarJogo());

        gbc.gridy = 3; 
        gbc.weighty = 0.5;
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.ipadx = 50;
        gbc.ipady = 10;
        this.add(btnIniciar, gbc);
        
        // --- 3. Painel de Botões Inferiores (Ranking e Configurações) ---
        JPanel painelBotoesExtras = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        painelBotoesExtras.setOpaque(false); // Transparente

        // Botão "Ranking" (Existente)
        JButton btnRanking = new JButton("🏆 Ranking");
        btnRanking.setFont(new Font("Monospaced", Font.BOLD, 16));
        btnRanking.setFocusable(false);
        btnRanking.setBackground(CafeColors.BOTAO_AVISO);
        btnRanking.setForeground(CafeColors.TEXTO_PRETO);
        btnRanking.addActionListener(e -> jogo.navegarPara(Tela.RANKING));
        painelBotoesExtras.add(btnRanking);
        
        // --- NOVO BOTÃO "CONFIGURAÇÕES" ---
        JButton btnConfig = new JButton("⚙️ Configurações");
        btnConfig.setFont(new Font("Monospaced", Font.BOLD, 16));
        btnConfig.setFocusable(false);
        btnConfig.setBackground(CafeColors.BOTAO_INFO); // Cor de informação [cite: 12]
        btnConfig.setForeground(CafeColors.TEXTO_BRANCO);
        btnConfig.addActionListener(e -> jogo.navegarPara(Tela.CONFIGURACOES)); // Navega para a nova tela [cite: 199, 245, 446, 54]
        painelBotoesExtras.add(btnConfig);

        // Adiciona o painel de botões extras abaixo do botão Iniciar
        gbc.gridy = 4;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.ipadx = 0;  // Reseta padding do GBC
        gbc.ipady = 0;  // Reseta padding do GBC
        this.add(painelBotoesExtras, gbc);
    }
}