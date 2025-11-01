package view;

import engine.Jogo;
import data.model.Menu.MenuItem;
import data.model.Menu.Ingrediente;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

/**
 * Tela Detalhes do Cliente — responsabilidade: exibir informações
 * detalhadas sobre o cliente atual, incluindo tipo e pedido específico.
 * 
 * Segue o padrão de arquitetura limpa: apenas interage com Jogo via métodos públicos.
 */
public class TelaDetalhesCliente extends JPanel {

    private final Jogo jogo;
    
    // Componentes dinâmicos
    private JLabel lblNomeCliente;
    private JLabel lblTipoCliente;
    private JLabel lblPedidoCliente;
    private JLabel lblIngredientes;

    public TelaDetalhesCliente(Jogo jogo) {
        this.jogo = jogo;
        configurarLayoutBase();
        montarComponentes();
        atualizarInfo();
    }

    private void configurarLayoutBase() {
        setLayout(new BorderLayout(20, 20));
        setBackground(CafeColors.FUNDO_BEGE);
        setBorder(new EmptyBorder(15, 25, 15, 25));
    }

    private void montarComponentes() {
        add(criarPainelSuperior(), BorderLayout.NORTH);
        add(criarPainelCentral(), BorderLayout.CENTER);
        add(criarPainelInferior(), BorderLayout.SOUTH);
    }

    private JPanel criarPainelSuperior() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setOpaque(false);

        JLabel titulo = new JLabel("📋 Detalhes do Cliente", SwingConstants.CENTER);
        titulo.setFont(new Font("Monospaced", Font.BOLD, 24));
        titulo.setForeground(CafeColors.TEXTO_PADRAO);
        titulo.setBorder(new EmptyBorder(0, 0, 10, 0));
        painel.add(titulo, BorderLayout.CENTER);

        return painel;
    }

    private JPanel criarPainelCentral() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setOpaque(false);
        painel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(CafeColors.MARROM_CLARO, 2),
            new EmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Nome do Cliente ---
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblEtiquetaNome = new JLabel("Cliente:");
        lblEtiquetaNome.setFont(new Font("Monospaced", Font.BOLD, 16));
        lblEtiquetaNome.setForeground(CafeColors.TEXTO_PADRAO);
        painel.add(lblEtiquetaNome, gbc);

        gbc.gridx = 1;
        lblNomeCliente = new JLabel("Carregando...");
        lblNomeCliente.setFont(new Font("Monospaced", Font.PLAIN, 16));
        lblNomeCliente.setForeground(CafeColors.TEXTO_PADRAO);
        painel.add(lblNomeCliente, gbc);

        // --- Tipo do Cliente ---
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel lblEtiquetaTipo = new JLabel("Tipo:");
        lblEtiquetaTipo.setFont(new Font("Monospaced", Font.BOLD, 16));
        lblEtiquetaTipo.setForeground(CafeColors.TEXTO_PADRAO);
        painel.add(lblEtiquetaTipo, gbc);

        gbc.gridx = 1;
        lblTipoCliente = new JLabel("Carregando...");
        lblTipoCliente.setFont(new Font("Monospaced", Font.PLAIN, 16));
        lblTipoCliente.setForeground(CafeColors.TEXTO_PADRAO);
        painel.add(lblTipoCliente, gbc);

        // --- Pedido ---
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblEtiquetaPedido = new JLabel("Pedido:");
        lblEtiquetaPedido.setFont(new Font("Monospaced", Font.BOLD, 16));
        lblEtiquetaPedido.setForeground(CafeColors.TEXTO_PADRAO);
        painel.add(lblEtiquetaPedido, gbc);

        gbc.gridx = 1;
        lblPedidoCliente = new JLabel("Carregando...");
        lblPedidoCliente.setFont(new Font("Monospaced", Font.PLAIN, 16));
        lblPedidoCliente.setForeground(CafeColors.TEXTO_PADRAO);
        painel.add(lblPedidoCliente, gbc);

        // --- Ingredientes ---
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel lblEtiquetaIngredientes = new JLabel("Ingredientes:");
        lblEtiquetaIngredientes.setFont(new Font("Monospaced", Font.BOLD, 16));
        lblEtiquetaIngredientes.setForeground(CafeColors.TEXTO_PADRAO);
        painel.add(lblEtiquetaIngredientes, gbc);

        gbc.gridx = 1;
        lblIngredientes = new JLabel("Carregando...");
        lblIngredientes.setFont(new Font("Monospaced", Font.PLAIN, 14));
        lblIngredientes.setForeground(CafeColors.TEXTO_CINZA);
        painel.add(lblIngredientes, gbc);

        return painel;
    }

    private JPanel criarPainelInferior() {
        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        rodape.setOpaque(false);

        JButton btnVoltar = criarBotao("⬅ Voltar ao Jogo", CafeColors.MARROM_ESCURO, 
            e -> jogo.navegarPara(Tela.JOGO));
        
        JButton btnPreparar = criarBotao("☕ Ir Preparar", CafeColors.BOTAO_ACERTO,
            e -> jogo.navegarPara(Tela.PREPARO));

        rodape.add(btnVoltar);
        rodape.add(btnPreparar);
        
        return rodape;
    }

    /**
     * Atualiza os componentes com os dados atuais do cliente.
     * Se o Jogo não expuser informações específicas, mostra dados básicos.
     */
    public void atualizarInfo() {
        try {
            // Dados básicos sempre disponíveis
            String nomeCliente = jogo.getClienteAtual();
            String fraseCliente = jogo.getFraseClienteAtual();
            
            lblNomeCliente.setText(nomeCliente);
            
            // Inferir tipo do cliente pela frase (fallback)
            String tipo = inferirTipoCliente(fraseCliente);
            lblTipoCliente.setText(tipo);
            
            // Tentar obter informações detalhadas do pedido
            // Se o Jogo não expuser isso diretamente, usar fallbacks
            try {
                // Método hipotético - se não existir, cairá no catch
                MenuItem pedido = jogo.getPedidoClienteAtual();
                if (pedido != null) {
                    lblPedidoCliente.setText(pedido.getName());
                    
                    // Formatar lista de ingredientes
                    StringBuilder ingredientesStr = new StringBuilder();
                    for (Ingrediente ing : pedido.getIngredientes()) {
                        if (ingredientesStr.length() > 0) {
                            ingredientesStr.append(", ");
                        }
                        ingredientesStr.append(ing.getName());
                    }
                    lblIngredientes.setText("<html>" + ingredientesStr.toString() + "</html>");
                } else {
                    definirValoresFallback();
                }
            } catch (Exception e) {
                // Fallback: usar informações da frase
                definirValoresFallback();
            }
            
        } catch (Exception e) {
            System.err.println("❌ Erro ao atualizar detalhes do cliente: " + e.getMessage());
            definirValoresFallback();
        }
    }

    /**
     * Inferir o tipo do cliente baseado na frase (fallback quando não há método específico).
     */
    private String inferirTipoCliente(String frase) {
        if (frase.contains("rápido") || frase.contains("apresse") || frase.contains("demorando")) {
            return "Apressado 🏃‍♂️";
        } else if (frase.contains("tranquilo") || frase.contains("calma") || frase.contains("esperar")) {
            return "Calmo 😌";
        } else if (frase.contains("perfeito") || frase.contains("melhor") || frase.contains("exatamente")) {
            return "Exigente 👑";
        } else if (frase.contains("dúvida") || frase.contains("escolher") || frase.contains("opções")) {
            return "Indeciso 🤔";
        } else {
            return "Cliente Regular ☕";
        }
    }

    /**
     * Define valores fallback quando informações detalhadas não estão disponíveis.
     */
    private void definirValoresFallback() {
        lblPedidoCliente.setText("Informação não disponível");
        lblIngredientes.setText("Consulte o livro de receitas 📖");
    }

    /**
     * Cria botões estilizados com o padrão do projeto.
     */
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
}