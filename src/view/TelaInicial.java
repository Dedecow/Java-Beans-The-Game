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
        // (Código do Ícone, Título e Subtítulo permanece o mesmo)
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
        gbc.weighty = 0.5; // Empurra os botões para baixo
        gbc.anchor = GridBagConstraints.PAGE_END; // Alinha ao fim da célula
        gbc.ipadx = 50; 
        gbc.ipady = 10; 
        this.add(btnIniciar, gbc);
        
        // --- 3. NOVO BOTÃO "RANKING" ---
        JButton btnRanking = new JButton("🏆 Ranking");
        btnRanking.setFont(new Font("Monospaced", Font.BOLD, 16));
        btnRanking.setFocusable(false);
        btnRanking.setBackground(CafeColors.BOTAO_AVISO); // Cor de aviso (dourado)
        btnRanking.setForeground(CafeColors.TEXTO_PRETO);
        
        // Ação: Delega a navegação ao Jogo
        btnRanking.addActionListener(e -> jogo.navegarPara(Tela.RANKING));

        gbc.gridy = 4; // Posição abaixo do Iniciar
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Reseta o alinhamento
        gbc.ipadx = 30; 
        gbc.ipady = 5; 
        this.add(btnRanking, gbc);
    }
}