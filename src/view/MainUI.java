package view;

import engine.Jogo;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class MainUI extends JFrame {
    private final Jogo jogo;
    
    public MainUI(Jogo jogo) {
        this.jogo = jogo;
        this.jogo.setUI(this); // Injeta a UI no jogo
        
        configurarJanela();
        mostrarTela(Tela.INICIAL);
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    private void configurarJanela() {
        this.setTitle("Java Beans - Cafeteria");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(600, 400));
        this.setLayout(new BorderLayout());
    }
    
    /**
     * Método principal do orquestrador - chamado pelo Jogo
     */
    public void mostrarTela(Tela tipoTela) {
        JPanel novaTela = criarTela(tipoTela);
        
        getContentPane().removeAll();
        getContentPane().add(novaTela, BorderLayout.CENTER);
        revalidate();
        repaint();
        
        System.out.println("🔄 MainUI: Navegando para " + tipoTela);
        atualizarTitulo(tipoTela);
    }
    
    /**
     * Factory method - cria as telas baseadas no enum
     */
    private JPanel criarTela(Tela tipoTela) {
        switch (tipoTela) {
            case INICIAL:
                return new TelaInicial(jogo);
            case JOGO:
                return new TelaJogo(jogo);
            case GAME_OVER:
                return new TelaGameOver(jogo);
            default:
                return new TelaInicial(jogo);
        }
    }
    
    private void atualizarTitulo(Tela tela) {
        String status = "";
        switch (tela) {
            case INICIAL: status = "Menu Inicial"; break;
            case JOGO: status = "Em Jogo | Pontuação: " + jogo.getPontuacao(); break;
            case GAME_OVER: status = "Fim de Jogo | Pontuação: " + jogo.getPontuacao(); break;
        }
        this.setTitle("Java Beans - " + status);
    }
    
    public void atualizarStatus(String status) {
        this.setTitle("Java Beans - " + status);
    }
}