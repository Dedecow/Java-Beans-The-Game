package app;

import engine.Jogo;
import view.MainUI;
import javax.swing.SwingUtilities;

public class Cafeteria {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Cria o jogo (model)
            Jogo jogo = new Jogo();
            
            // Cria a UI (orquestrador) que injeta a si mesma no jogo
            MainUI ui = new MainUI(jogo);
            
            // Inicia o jogo
            jogo.iniciar();
        });
    }
}