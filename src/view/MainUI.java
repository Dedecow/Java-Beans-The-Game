package view;

import engine.Jogo;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainUI extends JFrame {
    private final Jogo jogo;
    
    public MainUI(Jogo jogo) {
        this.jogo = jogo;
        // RESTAURADO: Injeta o MainUI no Jogo, dando a ele o controle de navegação
        this.jogo.setUI(this); 
        
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
     * Método principal do orquestrador - chamado pelo Jogo.
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
     * Factory method - cria as telas baseadas no enum.
     * Todas as Views Puras recebem apenas o Jogo.
     */
    private JPanel criarTela(Tela tipoTela) {
        switch (tipoTela) {
            case INICIAL:
                return new TelaInicial(jogo);
            case JOGO:
                return new TelaJogo(jogo); 
            case GAME_OVER:
                return new TelaGameOver(jogo);
                
            // TELAS NOVAS: Seguem o padrão simples, injetando apenas o Jogo
            case HISTORICO:
                return new TelaHistorico(jogo);
            case DETALHES_CLIENTE:
                return new TelaDetalhesCliente(jogo);
            case PREPARO:
                return new TelaPreparo(jogo);
            case RECEITA:
                return new TelaReceita(jogo);
            case MENU_ADMIN:
                return new TelaMenuAdmin(jogo);
            case CONFIGURACOES:
                return new TelaConfiguracoes(jogo);
            case ADICIONARRECEITA:
                return new TelaAdicionarReceita(jogo);
            case SOBRE:
                return new TelaSobre(jogo);
            default:
                throw new IllegalArgumentException("Tela desconhecida: " + tipoTela);
        }
    }
    
    private void atualizarTitulo(Tela tela) {
        String status = "";
        switch (tela) {
            case INICIAL: status = "Menu Inicial"; break;
            case JOGO: status = "Em Jogo | Pontuação: " + jogo.getPontuacao(); break;
            case GAME_OVER: status = "Fim de Jogo | Pontuação: " + jogo.getPontuacao(); break;
            case HISTORICO: status = "Histórico de Jogadores"; break;
            case MENU_ADMIN: status = "Menu Administrativo"; break;
            default: status = "Visualizando " + tela.name(); break;
        }
        this.setTitle("Java Beans - " + status);
    }
    
    public void atualizarStatus(String status) {
        // Chamado pelo Jogo
        this.setTitle("Java Beans - " + status);
    }
}