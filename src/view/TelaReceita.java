package view;

import engine.Jogo;
import data.model.Menu.MenuItem;
import data.model.Menu.Ingrediente;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

/**
 * Tela Receita — responsabilidade: exibir a lista de
 * bebidas e seus ingredientes, lendo do Cardapio via Jogo.
 */
public class TelaReceita extends JPanel {

    private final Jogo jogo;

    public TelaReceita(Jogo jogo) {
        this.jogo = jogo;
        configurarLayout();
        montarComponentes();
    }

    private void configurarLayout() {
        setBackground(CafeColors.FUNDO_BEGE);
        setLayout(new BorderLayout());
    }

    private void montarComponentes() {
        // 1. Cabeçalho
        JLabel titulo = new JLabel("📖 Livro de Receitas", SwingConstants.CENTER);
        titulo.setFont(new Font("Monospaced", Font.BOLD, 22));
        titulo.setForeground(CafeColors.TEXTO_PADRAO);
        titulo.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        // 2. Corpo principal (Lista de receitas)
        
        // Busca os dados do motor do jogo (NUNCA direto do Cardapio.java)
        List<MenuItem> menu = jogo.getCardapio();
        
        // Usar GridLayout para organizar as receitas
        JPanel corpo = new JPanel(new GridLayout(menu.size(), 1, 5, 5)); // (Nº de linhas = tamanho do menu)
        corpo.setOpaque(false); // Fundo transparente
        corpo.setBorder(new EmptyBorder(15, 30, 15, 30)); // Margem

        // Itera pelo menu e cria um JLabel para cada receita
        for (MenuItem item : menu) {
            
            // Formata a lista de ingredientes (ex: "Café, Água, Leite")
            StringBuilder ingredientesStr = new StringBuilder();
            for (Ingrediente ing : item.getIngredientes()) {
                ingredientesStr.append(ing.getName()).append(", ");
            }
            // Remove a última vírgula e espaço
            String receitaFormatada = ingredientesStr.substring(0, ingredientesStr.length() - 2);
            
            // Cria o Label final
            JLabel lblReceita = new JLabel("• " + item.getName() + ": " + receitaFormatada);
            lblReceita.setFont(new Font("Monospaced", Font.PLAIN, 16));
            lblReceita.setForeground(CafeColors.TEXTO_PADRAO);
            corpo.add(lblReceita);
        }
        
        // Adiciona o painel de corpo dentro de um JScrollPane para rolar
        JScrollPane scrollPane = new JScrollPane(corpo);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null); // Remove bordas do ScrollPane
        
        add(scrollPane, BorderLayout.CENTER);

        // 3. Rodapé (com botão voltar)
        JButton btnVoltar = criarBotaoVoltar();
        add(btnVoltar, BorderLayout.SOUTH);
    }

    /**
     * Cria um botão de voltar padronizado que navega para a Tela.JOGO.
     */
    private JButton criarBotaoVoltar() {
        JButton b = new JButton("⬅ Voltar");
        b.setFont(new Font("Monospaced", Font.BOLD, 14));
        b.setBackground(CafeColors.MARROM_ESCURO);
        b.setForeground(CafeColors.TEXTO_BRANCO);
        b.setFocusable(false);
        b.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Delega a navegação ao Jogo
        b.addActionListener(e -> jogo.navegarPara(Tela.JOGO));
        return b;
    }
}