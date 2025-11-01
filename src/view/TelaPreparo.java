package view;

import engine.Jogo;
import data.model.Menu.Ingrediente;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Tela de Preparo — responsabilidade: permitir ao jogador selecionar
 * os ingredientes e submeter o pedido (bandeja) ao motor do jogo.
 */
public class TelaPreparo extends JPanel {

    private final Jogo jogo;
    // Lista para guardar os checkboxes e poder ler os valores depois
    private final List<JCheckBox> chkIngredientes = new ArrayList<>();

    public TelaPreparo(Jogo jogo) {
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
        JLabel titulo = new JLabel("☕ Área de Preparo", SwingConstants.CENTER);
        titulo.setFont(new Font("Monospaced", Font.BOLD, 22));
        titulo.setForeground(CafeColors.TEXTO_PADRAO);
        titulo.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        // 2. Corpo (Checkboxes de ingredientes)
        
        // Busca os dados do motor do jogo
        List<Ingrediente> todosIngredientes = jogo.getTodosIngredientes();
        
        // Usar GridLayout para organizar os checkboxes
        int numIngredientes = todosIngredientes.size();
        int colunas = 3; // Define 3 colunas para os ingredientes
        int linhas = (int) Math.ceil((double) numIngredientes / colunas);
        
        JPanel corpo = new JPanel(new GridLayout(linhas, colunas, 10, 10));
        corpo.setOpaque(false);
        corpo.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        chkIngredientes.clear(); // Limpa a lista caso a tela seja recriada

        for (Ingrediente ing : todosIngredientes) {
            JCheckBox chk = new JCheckBox(ing.getName());
            chk.setFont(new Font("Monospaced", Font.PLAIN, 16));
            chk.setOpaque(false);
            chk.setForeground(CafeColors.TEXTO_PADRAO);
            
            // Armazena o *objeto* Ingrediente dentro do checkbox
            chk.putClientProperty("ingrediente_obj", ing); 
            
            chkIngredientes.add(chk);
            corpo.add(chk);
        }
        add(corpo, BorderLayout.CENTER);

        // 3. Rodapé (Botões de Ação)
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        painelBotoes.setOpaque(false); // Fundo transparente
        
        JButton btnVoltar = criarBotaoVoltar();
        JButton btnEntregar = criarBotaoEntregar();
        
        painelBotoes.add(btnVoltar);
        painelBotoes.add(btnEntregar);
        add(painelBotoes, BorderLayout.SOUTH);
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
        b.addActionListener(e -> jogo.navegarPara(Tela.JOGO));
        return b;
    }

    /**
     * Cria o botão principal de ação "Entregar Pedido".
     */
    private JButton criarBotaoEntregar() {
        JButton b = new JButton("Entregar Pedido ➡");
        b.setFont(new Font("Monospaced", Font.BOLD, 14));
        b.setBackground(CafeColors.BOTAO_ACERTO);
        b.setForeground(CafeColors.TEXTO_BRANCO);
        b.setFocusable(false);
        b.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Ação: Coletar selecionados e entregar ao Jogo
        b.addActionListener(e -> entregarPedido());
        return b;
    }

    /**
     * Coleta os ingredientes selecionados nos JCheckBoxes e
     * os envia para o motor do jogo para processamento.
     */
    private void entregarPedido() {
        List<Ingrediente> bandeja = new ArrayList<>();
        
        // Itera pela lista de checkboxes
        for (JCheckBox chk : chkIngredientes) {
            if (chk.isSelected()) {
                // Recupera o objeto Ingrediente armazenado
                bandeja.add((Ingrediente) chk.getClientProperty("ingrediente_obj"));
            }
        }
        
        // Converte a Lista para Array e delega ao motor do jogo
        jogo.entregarPedido(bandeja.toArray(new Ingrediente[0]));
    }
}