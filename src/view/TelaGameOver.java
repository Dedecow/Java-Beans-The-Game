package view;

import engine.Jogo;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D; 

public class TelaGameOver extends JPanel {
    
    private final Jogo jogo;
    private static final int LARGURA = 600;
    private static final int ALTURA = 400;

    // Removemos as definições de cor daqui.

    public TelaGameOver(Jogo jogo) {
        this.jogo = jogo;

        this.setPreferredSize(new Dimension(LARGURA, ALTURA));
        this.setBackground(CafeColors.FUNDO_PRINCIPAL); // Usa a cor encapsulada
        
        this.setLayout(null); 
        
        // Exemplo: Botão estilizado usando CafeColors
        JButton btnReiniciar = new JButton("PRÓXIMO CLIENTE");
        btnReiniciar.setFont(new Font("Monospaced", Font.BOLD, 16));
        btnReiniciar.setFocusable(false);
        btnReiniciar.setBackground(CafeColors.MARROM_ESCURO); // Cor encapsulada
        btnReiniciar.setForeground(CafeColors.TEXTO_BRANCO); // Cor encapsulada
        btnReiniciar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); 
        
        btnReiniciar.addActionListener(e -> {
            jogo.reiniciarPartida();
        });

        int btnLargura = 200;
        int btnAltura = 40;
        btnReiniciar.setBounds((LARGURA - btnLargura) / 2, ALTURA - 80, btnLargura, btnAltura);
        this.add(btnReiniciar);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // --- Painel "GAME OVER!" ---
        int painel1Largura = 400;
        int painel1Altura = 100;
        int painel1X = (LARGURA - painel1Largura) / 2;
        int painel1Y = ALTURA / 2 - 120;
        
        g2d.setColor(CafeColors.DESTAQUE_ROSA); // Usa a cor encapsulada
        g2d.fill(new RoundRectangle2D.Double(painel1X, painel1Y, painel1Largura, painel1Altura, 20, 20));
        
        g2d.setColor(CafeColors.MARROM_ESCURO); // Usa a cor encapsulada para a borda
        g2d.setStroke(new BasicStroke(2)); 
        g2d.draw(new RoundRectangle2D.Double(painel1X, painel1Y, painel1Largura, painel1Altura, 20, 20));

        // Texto "GAME OVER!"
        String gameOverTexto = "GAME OVER!";
        g2d.setColor(CafeColors.TEXTO_PADRAO); // Usa a cor encapsulada
        g2d.setFont(new Font("Monospaced", Font.BOLD, 36)); 
        FontMetrics fm1 = g2d.getFontMetrics();
        int texto1Largura = fm1.stringWidth(gameOverTexto);
        g2d.drawString(gameOverTexto, painel1X + (painel1Largura - texto1Largura) / 2, painel1Y + painel1Altura / 2 + fm1.getAscent() / 2 - 10);

        // Subtexto "Pontuação Final: X"
        String pontuacaoTexto = "PONTUAÇÃO FINAL: " + jogo.getPontuacao();
        g2d.setFont(new Font("Monospaced", Font.PLAIN, 18));
        FontMetrics fm1_sub = g2d.getFontMetrics();
        int subtexto1Largura = fm1_sub.stringWidth(pontuacaoTexto);
        g2d.drawString(pontuacaoTexto, painel1X + (painel1Largura - subtexto1Largura) / 2, painel1Y + painel1Altura / 2 + fm1.getAscent() / 2 + 30);
    }
}