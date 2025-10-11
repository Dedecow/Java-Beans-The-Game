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

        // Layout centralizado
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); 
        gbc.gridx = 0; 

        // --- 1. Ícone / Logo ---
        JLabel lblIcone = new JLabel("☕"); 
        lblIcone.setFont(new Font("Monospaced", Font.PLAIN, 48));
        lblIcone.setForeground(CafeColors.TEXTO_BRANCO);
        gbc.gridy = 0; 
        this.add(lblIcone, gbc);

        // --- 2. Título Principal ---
        JLabel lblTitulo = new JLabel("JAVABEANS");
        lblTitulo.setFont(new Font("Monospaced", Font.BOLD, 32));
        lblTitulo.setForeground(CafeColors.TEXTO_BRANCO);
        gbc.gridy = 1; 
        this.add(lblTitulo, gbc);

        // --- 3. Subtítulo ---
        JLabel lblSubtitulo = new JLabel("CAFETERIA");
        lblSubtitulo.setFont(new Font("Monospaced", Font.PLAIN, 18));
        lblSubtitulo.setForeground(CafeColors.TEXTO_BRANCO);
        gbc.gridy = 2; 
        this.add(lblSubtitulo, gbc);

        // --- 4. Espaçador (para empurrar o botão para baixo) ---
        gbc.gridy = 3; 
        gbc.weighty = 1.0; 
        this.add(new JLabel(), gbc);

        // --- 5. Botão "INICIAR JOGO" ---
        JButton btnIniciar = new JButton("INICIAR JOGO");
        btnIniciar.setFont(new Font("Monospaced", Font.BOLD, 20));
        btnIniciar.setFocusable(false);
        btnIniciar.setBackground(CafeColors.MARROM_ESCURO); 
        btnIniciar.setForeground(CafeColors.TEXTO_BRANCO);
        
        btnIniciar.addActionListener(e -> {
            jogo.iniciarJogo(); 
        });

        gbc.gridy = 4; 
        gbc.weighty = 0; 
        gbc.ipadx = 50; 
        gbc.ipady = 10; 
        this.add(btnIniciar, gbc);
        
        // --- 6. Instrução ---
        JLabel lblInstrucao = new JLabel("Pressione o botão para começar");
        lblInstrucao.setFont(new Font("Monospaced", Font.PLAIN, 12));
        lblInstrucao.setForeground(CafeColors.TEXTO_BRANCO);
        gbc.gridy = 5; 
        gbc.insets = new Insets(40, 0, 0, 0); 
        this.add(lblInstrucao, gbc);
    }
}