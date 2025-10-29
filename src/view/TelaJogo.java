package view;

import engine.Jogo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaJogo extends JPanel {
    private final Jogo jogo;
    private static final int LARGURA = 600;
    private static final int ALTURA = 400;
    
    private JLabel lblClienteNome;
    private JLabel lblClienteFrase; 
    private JLabel lblPontuacao;

    public TelaJogo(Jogo jogo) { 
        this.jogo = jogo;
        configurarUI();
        atualizarInfo(); 
    }
    
    private void configurarUI() {
        this.setPreferredSize(new Dimension(LARGURA, ALTURA)); 
        this.setBackground(CafeColors.FUNDO_BEGE);
        this.setLayout(new BorderLayout());
        
        JPanel painelInfo = criarPainelInfo(); 
        JPanel painelAcoes = criarPainelAcoes(); // MODIFICADO
        
        JButton btnFinalizar = criarBotao("FINALIZAR JOGO", CafeColors.MARROM_ESCURO, 
            e -> jogo.finalizarJogo());
        
        this.add(painelInfo, BorderLayout.NORTH);
        this.add(painelAcoes, BorderLayout.CENTER);
        this.add(btnFinalizar, BorderLayout.SOUTH);
    }
    
    /**
     * Cria o painel de informações com nome, frase e pontuação.
     */
    private JPanel criarPainelInfo() {
        JPanel painel = new JPanel(new GridLayout(3, 1)); 
        painel.setBackground(CafeColors.MARROM_ESCURO);
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        lblClienteNome = new JLabel("Cliente: ", SwingConstants.CENTER);
        lblClienteNome.setFont(new Font("Monospaced", Font.BOLD, 18));
        lblClienteNome.setForeground(CafeColors.TEXTO_BRANCO);
        
        lblClienteFrase = new JLabel("...", SwingConstants.CENTER);
        lblClienteFrase.setFont(new Font("Monospaced", Font.ITALIC, 16));
        lblClienteFrase.setForeground(CafeColors.PAINEL_DESTAQUE);
        
        lblPontuacao = new JLabel("Pontuação: ", SwingConstants.CENTER);
        lblPontuacao.setFont(new Font("Monospaced", Font.PLAIN, 16));
        lblPontuacao.setForeground(CafeColors.TEXTO_BRANCO);
        
        painel.add(lblClienteNome);
        painel.add(lblClienteFrase);
        painel.add(lblPontuacao);
        
        return painel;
    }

    /**
     * Puxa os dados do Jogo e atualiza os JLabels.
     */
    private void atualizarInfo() {
        lblClienteNome.setText("Cliente: " + jogo.getClienteAtual());
        lblClienteFrase.setText(jogo.getFraseClienteAtual());
        lblPontuacao.setText("Pontuação: " + jogo.getPontuacao());
    }
    
    /**
     * MODIFICADO: Agora tem dois botões (Preparar e Receitas).
     */
    private JPanel criarPainelAcoes() {
        // Layout 1 linha, 2 colunas, com espaço de 20
        JPanel painel = new JPanel(new GridLayout(1, 2, 20, 0)); 
        painel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); 
        painel.setBackground(CafeColors.FUNDO_BEGE);
        
        // --- NOVO BOTÃO DE RECEITAS ---
        JButton btnVerReceitas = criarBotao(
            "📖 Ver Receitas", 
            CafeColors.BOTAO_INFO,
            // Ação: Navega para a tela de Receita
            e -> jogo.navegarPara(Tela.RECEITA)
        );
        btnVerReceitas.setFont(new Font("Monospaced", Font.BOLD, 16));
        
        // Botão para ir ao preparo
        JButton btnIrPreparo = criarBotao(
            "☕ Ir Preparar", 
            CafeColors.BOTAO_ACERTO,
            // Ação: Navega para a tela de Preparo
            e -> jogo.navegarPara(Tela.PREPARO)
        );
        btnIrPreparo.setFont(new Font("Monospaced", Font.BOLD, 16));
                
        painel.add(btnVerReceitas); // Adiciona o novo botão
        painel.add(btnIrPreparo); 
        
        return painel;
    }
    
    /**
     * Método utilitário para criar botões estilizados.
     */
    private JButton criarBotao(String texto, Color cor, java.awt.event.ActionListener acao) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Monospaced", Font.BOLD, 14)); 
        botao.setBackground(cor);
        botao.setForeground(CafeColors.TEXTO_BRANCO);
        botao.setFocusable(false);
        botao.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        botao.addActionListener(acao);
        return botao;
    }
}