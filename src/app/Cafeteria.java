package app;

import engine.Jogo;
import javax.swing.SwingUtilities;

/**
 * Classe principal para inicializar o aplicativo "Cafeteria JavaBeans".
 * Garante que a interface gráfica (Swing) inicie na Thread correta.
 */
public class Cafeteria {

    public static void main(String[] args) {
        // Usa SwingUtilities.invokeLater para garantir que a GUI seja
        // criada e manipulada na Event Dispatch Thread (EDT).
        SwingUtilities.invokeLater(() -> {
            Jogo jogo = new Jogo();
            jogo.iniciar();
        });
    }
}