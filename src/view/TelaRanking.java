package view;

import engine.Jogo;
import data.model.Historico; // Importa o modelo de dados
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Tela Ranking — responsabilidade: exibir o Top 10 de pontuações
 * do histórico, lendo os dados do motor do jogo (que consulta o DAO).
 * CORRIGIDO: Agora exibe o nome do jogador real em vez do nome do cliente NPC.
 */
public class TelaRanking extends JPanel {

    private final Jogo jogo;

    public TelaRanking(Jogo jogo) {
        this.jogo = jogo;
        configurarLayout();
        montarComponentes();
    }

    private void configurarLayout() {
        // Estilo Padrão (Fundo e Layout)
        setBackground(CafeColors.FUNDO_BEGE);
        setLayout(new BorderLayout());
    }

    private void montarComponentes() {
        
        // 1. Cabeçalho (com destaque dourado)
        JLabel titulo = new JLabel("🏆 RANKING - TOP 10 🏆", SwingConstants.CENTER);
        titulo.setFont(new Font("Monospaced", Font.BOLD, 22));
        titulo.setForeground(CafeColors.TEXTO_DOUrado);
        titulo.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        // 2. Corpo principal (Lista de Pontuações)
        JPanel corpo = new JPanel();
        corpo.setOpaque(false); // Fundo transparente para usar o FUNDO_BEGE
        corpo.setLayout(new GridLayout(10, 1)); // 10 linhas, 1 coluna
        corpo.setBorder(new EmptyBorder(10, 40, 10, 40));

        // Busca o ranking do motor do jogo
        Historico[] ranking = jogo.getRanking();

        for (int i = 0; i < 10; i++) {
            String textoRank;
            Color corRank;

            if (i < ranking.length) {
                // CORREÇÃO: Usando getNomeJogador() em vez de getNomeCliente()
                Historico h = ranking[i];
                textoRank = String.format("%d. %-20s %d pontos", 
                    (i + 1), h.getNomeJogador(), h.getPontuacao());
            } else {
                // Preenche espaços vazios
                textoRank = (i + 1) + ". ...";
            }

            // Define as cores para o pódio
            switch (i) {
                case 0: corRank = CafeColors.OURO; break;
                case 1: corRank = CafeColors.PRATA; break;
                case 2: corRank = CafeColors.BRONZE; break;
                default: corRank = CafeColors.TEXTO_PADRAO;
            }

            JLabel lblRank = new JLabel(textoRank);
            lblRank.setFont(new Font("Monospaced", Font.BOLD, 16));
            lblRank.setForeground(corRank);
            lblRank.setHorizontalAlignment(SwingConstants.LEFT);
            corpo.add(lblRank);
        }
        
        add(corpo, BorderLayout.CENTER);

        // 3. Rodapé (com botão voltar para a Tela Inicial)
        JButton btnVoltar = criarBotaoVoltar();
        add(btnVoltar, BorderLayout.SOUTH);
    }

    /**
     * Cria um botão de voltar padronizado que navega para a Tela.INICIAL.
     */
    private JButton criarBotaoVoltar() {
        JButton b = new JButton("⬅ Voltar ao Menu");
        b.setFont(new Font("Monospaced", Font.BOLD, 14));
        b.setBackground(CafeColors.MARROM_ESCURO);
        b.setForeground(CafeColors.TEXTO_BRANCO);
        b.setFocusable(false);
        b.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Regra de Navegação: Ranking volta para o INICIAL
        b.addActionListener(e -> jogo.navegarPara(Tela.INICIAL));
        return b;
    }
}