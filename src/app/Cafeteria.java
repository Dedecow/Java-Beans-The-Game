package app;

import engine.Jogo;
import view.MainUI;
import javax.swing.SwingUtilities;

public class Cafeteria {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Jogo jogo = new Jogo();       // Motor do jogo
            MainUI ui = new MainUI(jogo); // UI principal
            jogo.iniciar();               // Apenas loga o início
        });
    }
}
