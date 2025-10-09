package view;

import engine.Jogo; // Importa a classe Jogo
import javax.swing.*;
import java.awt.*;

/**
 * Interface Gráfica Mínima.
 */
// ESTE CÓDIGO VEIO DO ARQUIVO ANTERIORMENTE CHAMADO Jogo.java
public class MainUI extends JFrame {
    // Agora Jogo precisa ser importado
    private final Jogo jogo;

    public MainUI(Jogo jogo, String status) {
        this.jogo = jogo;
        setTitle("Cafeteria JavaBeans - Status");
        setSize(400, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Elementos da UI
        JLabel lblStatus = new JLabel("Status Atual: " + status);
        JButton btnAcertar = new JButton("Acertar Pedido");
        JButton btnErrar = new JButton("Errar Pedido");

        // Lógica de Eventos (Chama o motor do jogo)
        btnAcertar.addActionListener(e -> {
            dispose();
            jogo.processarAcao(true);
        });

        btnErrar.addActionListener(e -> {
            dispose();
            jogo.processarAcao(false);
        });

        add(lblStatus);
        add(btnAcertar);
        add(btnErrar);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
}