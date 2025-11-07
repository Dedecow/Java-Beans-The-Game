package view;

import engine.Jogo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaInicial extends JPanel {

    private final Jogo jogo;
    private JTextField campoNomeJogador;

    public TelaInicial(Jogo jogo) {
        this.jogo = jogo;
        configurarLayout();
        montarComponentes();
    }

    private void configurarLayout() {
        setPreferredSize(new Dimension(600, 400));
        setBackground(CafeColors.FUNDO_CAFE_ESCURO);
        setLayout(new BorderLayout());
    }

    private void montarComponentes() {
        // Painel principal com BoxLayout para organizar verticalmente
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBackground(CafeColors.FUNDO_CAFE_ESCURO);
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- 1. Cabeçalho (Título) ---
        JPanel painelTitulo = new JPanel();
        painelTitulo.setLayout(new BoxLayout(painelTitulo, BoxLayout.Y_AXIS));
        painelTitulo.setBackground(CafeColors.FUNDO_CAFE_ESCURO);
        painelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblIcone = new JLabel("☕");
        lblIcone.setFont(new Font("Monospaced", Font.PLAIN, 64));
        lblIcone.setForeground(CafeColors.TEXTO_BRANCO);
        lblIcone.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTitulo = new JLabel("JAVABEANS");
        lblTitulo.setFont(new Font("Monospaced", Font.BOLD, 36));
        lblTitulo.setForeground(CafeColors.TEXTO_BRANCO);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitulo = new JLabel("CAFETERIA");
        lblSubtitulo.setFont(new Font("Monospaced", Font.PLAIN, 20));
        lblSubtitulo.setForeground(CafeColors.TEXTO_BRANCO);
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelTitulo.add(lblIcone);
        painelTitulo.add(Box.createRigidArea(new Dimension(0, 5)));
        painelTitulo.add(lblTitulo);
        painelTitulo.add(Box.createRigidArea(new Dimension(0, 2)));
        painelTitulo.add(lblSubtitulo);

        // --- 2. Painel do Nome do Jogador ---
        JPanel painelNome = new JPanel();
        painelNome.setLayout(new BoxLayout(painelNome, BoxLayout.Y_AXIS));
        painelNome.setBackground(CafeColors.FUNDO_CAFE_ESCURO);
        painelNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelNome.setMaximumSize(new Dimension(300, 80));

        JLabel lblInstrucao = new JLabel("Digite seu nome:");
        lblInstrucao.setFont(new Font("Monospaced", Font.PLAIN, 16));
        lblInstrucao.setForeground(CafeColors.TEXTO_BRANCO);
        lblInstrucao.setAlignmentX(Component.CENTER_ALIGNMENT);

        campoNomeJogador = new JTextField();
        campoNomeJogador.setFont(new Font("Monospaced", Font.PLAIN, 18));
        campoNomeJogador.setHorizontalAlignment(JTextField.CENTER);
        campoNomeJogador.setText("Barista");
        campoNomeJogador.setMaximumSize(new Dimension(250, 35));
        campoNomeJogador.setBackground(Color.WHITE);
        campoNomeJogador.setForeground(CafeColors.TEXTO_PADRAO);

        painelNome.add(lblInstrucao);
        painelNome.add(Box.createRigidArea(new Dimension(0, 8)));
        painelNome.add(campoNomeJogador);

        // --- 3. Botão INICIAR JOGO ---
        JPanel painelBotaoIniciar = new JPanel();
        painelBotaoIniciar.setBackground(CafeColors.FUNDO_CAFE_ESCURO);
        painelBotaoIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnIniciar = new JButton("INICIAR JOGO");
        btnIniciar.setFont(new Font("Monospaced", Font.BOLD, 20));
        btnIniciar.setFocusable(false);
        btnIniciar.setBackground(CafeColors.MARROM_ESCURO);
        btnIniciar.setForeground(CafeColors.TEXTO_BRANCO);
        btnIniciar.setBorder(BorderFactory.createEmptyBorder(12, 40, 12, 40));
        btnIniciar.addActionListener(this::iniciarJogo);

        painelBotaoIniciar.add(btnIniciar);

        // --- 4. Painel de Botões Inferiores ---
        JPanel painelBotoesExtras = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelBotoesExtras.setBackground(CafeColors.FUNDO_CAFE_ESCURO);
        painelBotoesExtras.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelBotoesExtras.setMaximumSize(new Dimension(400, 60));

        JButton btnRanking = new JButton("🏆 Ranking");
        btnRanking.setFont(new Font("Monospaced", Font.BOLD, 14));
        btnRanking.setFocusable(false);
        btnRanking.setBackground(CafeColors.BOTAO_AVISO);
        btnRanking.setForeground(CafeColors.TEXTO_PRETO);
        btnRanking.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        btnRanking.addActionListener(e -> jogo.navegarPara(Tela.RANKING));

        JButton btnConfig = new JButton("⚙️ Configurações");
        btnConfig.setFont(new Font("Monospaced", Font.BOLD, 14));
        btnConfig.setFocusable(false);
        btnConfig.setBackground(CafeColors.BOTAO_INFO);
        btnConfig.setForeground(CafeColors.TEXTO_BRANCO);
        btnConfig.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        btnConfig.addActionListener(e -> jogo.navegarPara(Tela.CONFIGURACOES));

        painelBotoesExtras.add(btnRanking);
        painelBotoesExtras.add(btnConfig);

        // --- MONTAGEM FINAL ---
        painelPrincipal.add(Box.createVerticalGlue()); // Espaço no topo
        painelPrincipal.add(painelTitulo);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 25)));
        painelPrincipal.add(painelNome);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 25)));
        painelPrincipal.add(painelBotaoIniciar);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 20))); // Espaço reduzido
        painelPrincipal.add(painelBotoesExtras);
        painelPrincipal.add(Box.createVerticalGlue()); // Espaço na base

        add(painelPrincipal, BorderLayout.CENTER);
    }

    private void iniciarJogo(ActionEvent e) {
        String nomeJogador = campoNomeJogador.getText().trim();
        
        if (nomeJogador.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, digite seu nome!", 
                "Nome Obrigatório", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        jogo.iniciarJogo(nomeJogador);
    }
}