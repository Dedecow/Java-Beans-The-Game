package view;

import engine.Jogo;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;

// MainUI agora será a nossa JANELA principal (JFrame)
public class MainUI extends JFrame {
    private final Jogo jogo;
    
    // Construtor principal
    public MainUI(Jogo jogo) {
        this.jogo = jogo;
        
        // Configurações do JFrame (Janela)
        this.setTitle("Java Beans"); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setResizable(false);
        
        // Define o tamanho inicial (pode ser o tamanho preferido da primeira tela)
        this.setPreferredSize(new Dimension(600, 400));
        
        // ************************************************
        // CORREÇÃO AQUI: INICIA COM A TELA INICIAL
        // ************************************************
        mostrarTela(new TelaInicial(jogo)); 
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    // Método para trocar o conteúdo do JFrame
    public void mostrarTela(JPanel novaTela) {
        getContentPane().removeAll();
        getContentPane().add(novaTela);
        revalidate();
        repaint();
    }
    
    public void atualizarStatus(String status) {
        // Método para atualizar talvez uma barra de status se tivéssemos uma.
        // Por enquanto, podemos atualizar o título se necessário.
        this.setTitle("Java Beans | " + status);
    }
}