package view;

import engine.Jogo;
import javax.swing.*;
import java.awt.*;

/**
 * TelaJogo — Exibe o cliente e pedido atual,
 * além das ações principais (Preparar, Receitas, Finalizar).
 *
 * Responsabilidade única: interface de interação principal do jogador.
 */
public class TelaJogo extends JPanel {

    private final Jogo jogo;

    // Constantes de layout
    private static final int LARGURA = 600;
    private static final int ALTURA = 400;

    // Componentes que precisam ser atualizados dinamicamente
    private JLabel lblClienteNome;
    private JLabel lblClienteFrase;
    private JLabel lblPontuacao;

    // ============================================================
    // CONSTRUTOR
    // ============================================================
    public TelaJogo(Jogo jogo) {
        this.jogo = jogo;
        configurarLayoutBase();
        montarComponentes();
        atualizarInfo();
    }

    // ============================================================
    // CONFIGURAÇÃO DE LAYOUT E COMPONENTES
    // ============================================================

    /** Define o layout e aparência geral da tela. */
    private void configurarLayoutBase() {
        setPreferredSize(new Dimension(LARGURA, ALTURA));
        setBackground(CafeColors.FUNDO_BEGE); 
        setLayout(new BorderLayout(20, 20)); // Adicionado espaçamento
    }

    /** Monta os painéis da tela: Info (Norte), Ações (Centro) e Rodapé (Sul). */
    private void montarComponentes() {
        add(criarPainelInfo(), BorderLayout.NORTH);
        add(criarPainelAcoes(), BorderLayout.CENTER);
        add(criarPainelRodape(), BorderLayout.SOUTH); // Método de rodapé refatorado
    }

    // ============================================================
    // PAINÉIS
    // ============================================================

    /** Cria o painel superior com informações do cliente e pontuação. */
    private JPanel criarPainelInfo() {
        JPanel painel = new JPanel(new GridLayout(3, 1, 5, 5));
        painel.setBackground(CafeColors.MARROM_ESCURO);
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblClienteNome = criarLabel("Cliente: ", Font.BOLD, 18, CafeColors.TEXTO_BRANCO);
        lblClienteFrase = criarLabel("...", Font.ITALIC, 16, CafeColors.PAINEL_DESTAQUE);
        lblPontuacao = criarLabel("Pontuação: ", Font.PLAIN, 16, CafeColors.TEXTO_BRANCO);

        painel.add(lblClienteNome);
        painel.add(lblClienteFrase);
        painel.add(lblPontuacao);
        return painel;
    }

    /** Cria o painel central com as ações principais do jogo. */
    private JPanel criarPainelAcoes() {
        JPanel painel = new JPanel(new GridLayout(1, 2, 30, 0));
        painel.setOpaque(false); // Fundo transparente
        painel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80)); // Padding

        JButton btnReceitas = criarBotao(
            "📖 Ver Receitas",
            CafeColors.BOTAO_INFO,
            e -> jogo.navegarPara(Tela.RECEITA)
        );

        JButton btnPreparo = criarBotao(
            "☕ Ir Preparar",
            CafeColors.BOTAO_ACERTO,
            e -> jogo.navegarPara(Tela.PREPARO)
        );

        painel.add(btnReceitas);
        painel.add(btnPreparo);
        return painel;
    }

    /** Cria o painel inferior com botões de navegação/fim. */
    private JPanel criarPainelRodape() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5)); // Espaçamento
        painel.setOpaque(false); // Fundo transparente

        JButton btnDetalhes = criarBotao(
            "📋 Esqueci Pedido",
            CafeColors.MARROM_MEIO, // Cor neutra [cite: 8]
            e -> jogo.navegarPara(Tela.DETALHES_CLIENTE)
        );

        // Botão "Finalizar Jogo" (Existente)
        JButton btnFinalizar = criarBotao(
            "⏹ Finalizar Jogo",
            CafeColors.BOTAO_ERRO,
            e -> jogo.finalizarJogo()
        );
        
        if (!jogo.isJogadorTreinado()) { 
            painel.add(btnDetalhes); 
        }
        
        //painel.add(btnDetalhes); 
        painel.add(btnFinalizar);
        return painel;
    }

    // ============================================================
    // MÉTODOS DE SUPORTE
    // ============================================================

    /** Atualiza dinamicamente as informações do cliente e pontuação. */
    public void atualizarInfo() {
        lblClienteNome.setText("Cliente: " + jogo.getClienteAtual());
        lblClienteFrase.setText(jogo.getFraseClienteAtual());
        lblPontuacao.setText("Pontuação: " + jogo.getPontuacao());
    }

    /** Cria um JLabel estilizado. */
    private JLabel criarLabel(String texto, int estilo, int tamanho, Color cor) {
        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setFont(new Font("Monospaced", estilo, tamanho));
        label.setForeground(cor);
        return label;
    }

    /** Cria um botão com estilo padrão e ação associada. */
    private JButton criarBotao(String texto, Color corFundo, java.awt.event.ActionListener acao) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Monospaced", Font.BOLD, 16));
        botao.setBackground(corFundo);
        botao.setForeground(CafeColors.TEXTO_BRANCO);
        botao.setFocusable(false);
        botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
        botao.addActionListener(acao);
        return botao;
    }
}