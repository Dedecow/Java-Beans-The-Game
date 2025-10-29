package app;

import engine.Jogo;
import view.MainUI;
import javax.swing.SwingUtilities;

public class Cafeteria {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            
            // 1. Cria o jogo (motor)
            // Simples, sem injeção de dependência.
            Jogo jogo = new Jogo();
            
            // 2. Cria a UI (orquestrador) que injeta a si mesma no jogo
            MainUI ui = new MainUI(jogo);
            
            // 3. Inicia o jogo
            jogo.iniciar();
        });
    }
}