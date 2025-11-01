package view;

import engine.Jogo;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Tela Sobre — exibe informações sobre o projeto, versão e créditos.
 * MODIFICADO: Agora exibe a imagem de agradecimento e implementa Atualizavel.
 */
public class TelaSobre extends JPanel implements MainUI.Atualizavel { // Implementa a interface

    private final Jogo jogo;
    private static final int LARGURA_MAX_IMAGEM = 480;

    public TelaSobre(Jogo jogo) {
        this.jogo = jogo;
        configurarLayout();
        montarComponentes();
    }

    /**
     * NOVO: Método utilitário para carregar e escalar imagens com segurança.
     *
     * @param caminho O caminho relativo para a imagem (ex: "assets/fotos/...")
     * @param larguraAlvo A largura desejada para a imagem escalada.
     * @return um ImageIcon escalado ou null se falhar.
     */
    private ImageIcon carregarIcone(String caminho, int larguraAlvo) {
        try {
            ImageIcon originalIcon = new ImageIcon(caminho);
            Image originalImage = originalIcon.getImage();

            int originalLargura = originalImage.getWidth(null);
            
            // Se for menor que o alvo, ou se o alvo for -1, usa original
            if (larguraAlvo == -1 || originalLargura <= larguraAlvo) {
                return originalIcon;
            }

            // Calcula nova altura mantendo a proporção
            int originalAltura = originalImage.getHeight(null);
            int novaAltura = (originalAltura * larguraAlvo) / originalLargura;

            Image imagemEscalada = originalImage.getScaledInstance(larguraAlvo, novaAltura, Image.SCALE_SMOOTH);
            return new ImageIcon(imagemEscalada);

        } catch (Exception e) {
            System.err.println("⚠️ Erro ao carregar imagem: " + caminho + " | " + e.getMessage());
            return null; // Retorna nulo para tratamento
        }
    }

    private void configurarLayout() {
        setLayout(new BorderLayout(20, 20));
        setBackground(CafeColors.FUNDO_BEGE);
        // Aumenta a borda horizontal para a imagem caber melhor
        setBorder(new EmptyBorder(20, 40, 20, 40));
    }

    private void montarComponentes() {
        // Cabeçalho (MODIFICADO: emoji removido do título)
        JLabel titulo = new JLabel("Sobre o Java Beans", SwingConstants.CENTER);
        titulo.setFont(new Font("Monospaced", Font.BOLD, 24));
        titulo.setForeground(CafeColors.TEXTO_PADRAO);
        add(titulo, BorderLayout.NORTH);

        // Corpo - Informações do projeto
        JPanel painelInfo = criarPainelInformacoes();
        // Adiciona barra de rolagem caso a imagem e o texto sejam muito grandes
        JScrollPane scrollPane = new JScrollPane(painelInfo);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(CafeColors.FUNDO_BEGE);
        scrollPane.setOpaque(false);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(scrollPane, BorderLayout.CENTER);

        // Rodapé
        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rodape.setOpaque(false);
        
        // Botão de retorno ao Menu Inicial
        JButton btnVoltar = criarBotao("⬅ Voltar ao Menu", CafeColors.MARROM_ESCURO,
            e -> jogo.navegarPara(Tela.INICIAL));
        
        rodape.add(btnVoltar);
        add(rodape, BorderLayout.SOUTH);
    }

    private JPanel criarPainelInformacoes() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setOpaque(false);
        painel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(CafeColors.MARROM_CLARO, 2),
            new EmptyBorder(20, 20, 20, 20)
        ));

        // --- NOVO: Adiciona a imagem de agradecimento ---
        ImageIcon imgAgradecimento = carregarIcone("assets/fotos/agradecimento.png", LARGURA_MAX_IMAGEM);
        if (imgAgradecimento != null) {
            JLabel lblImagem = new JLabel(imgAgradecimento);
            lblImagem.setAlignmentX(Component.CENTER_ALIGNMENT);
            painel.add(lblImagem);
            painel.add(Box.createRigidArea(new Dimension(0, 20))); // Espaçamento
        }
        // ---------------------------------------------

        String[] informacoes = {
            "🏪 Java Beans - The Game",
            "",
            "📚 Propósito:",
            "• Simulador  de jogo Java, tema: cafeteria",
            "",
            "⚙️ Tecnologias:",
            "• Java 17+ • Swing • SQLite • JDBC",
            "• Padrão DAO • MVC • Programação Orientada a Objetos",
            "",
            "🎯 Funcionalidades:",
            "• Sistema de clientes com personalidades",
            "• Preparo de bebidas com ingredientes",
            "• Sistema de pontuação e ranking",
            "• Persistência em banco de dados",
            "",
            "👨‍💻 Disciplina Programação de Soluções Computacionais",
            "📅 Versão: 2.0.0",
            "🎮 Avaliação- Trabalho A3 - Ânima"
        };

        for (String info : informacoes) {
            JLabel lbl = new JLabel(info);
            lbl.setFont(new Font("Monospaced", Font.PLAIN, 14));
            lbl.setForeground(info.startsWith("🏪") || info.startsWith("👨‍💻") ? 
                CafeColors.TEXTO_PADRAO : CafeColors.TEXTO_CINZA);
            lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
            painel.add(lbl);
            
            if (info.isEmpty()) {
                painel.add(Box.createRigidArea(new Dimension(0, 5)));
            }
        }

        return painel;
    }

    private JButton criarBotao(String texto, Color cor, java.awt.event.ActionListener acao) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Monospaced", Font.BOLD, 14));
        botao.setBackground(cor);
        botao.setForeground(CafeColors.TEXTO_BRANCO);
        botao.setFocusable(false);
        botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        botao.addActionListener(acao);
        return botao;
    }

    @Override
    public void atualizarInfo() {
        // Informações estáticas, não precisam ser atualizadas dinamicamente.
    }
}