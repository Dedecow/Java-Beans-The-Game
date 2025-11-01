package view;

import engine.Jogo;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Tela de Configurações — permite ao jogador ajustar preferências visuais e de áudio.
 * <p>
 * Não persiste dados (simulação), apenas exibe feedback ao salvar.
 * Segue o padrão de arquitetura limpa: apenas interage com Jogo via métodos públicos.
 * </p>
 */
public class TelaConfiguracoes extends JPanel {

    private final Jogo jogo;

    // Componentes de configuração
    private JCheckBox chkEfeitosSonoros;
    private JCheckBox chkMusicaFundo;
    private JSlider sldVolume;
    private JComboBox<String> cmbTemaVisual;

    public TelaConfiguracoes(Jogo jogo) {
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

        JLabel titulo = new JLabel("Configurações", SwingConstants.CENTER);
        titulo.setFont(new Font("Monospaced", Font.BOLD, 24));
        titulo.setForeground(CafeColors.TEXTO_PADRAO);
        painel.add(titulo, BorderLayout.CENTER);

        return painel;
    }

    private JPanel criarPainelCentral() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // --- Linha 1: Efeitos Sonoros ---
        gbc.gridx = 0; gbc.gridy = 0;
        painel.add(new JLabel("Efeitos Sonoros"), gbc);

        chkEfeitosSonoros = new JCheckBox();
        chkEfeitosSonoros.setSelected(true);
        chkEfeitosSonoros.setOpaque(false);
        gbc.gridx = 1;
        painel.add(chkEfeitosSonoros, gbc);

        // --- Linha 2: Música de Fundo ---
        gbc.gridx = 0; gbc.gridy = 1;
        painel.add(new JLabel("Música de Fundo"), gbc);

        chkMusicaFundo = new JCheckBox();
        chkMusicaFundo.setSelected(true);
        chkMusicaFundo.setOpaque(false);
        gbc.gridx = 1;
        painel.add(chkMusicaFundo, gbc);

        // --- Linha 3: Volume ---
        gbc.gridx = 0; gbc.gridy = 2;
        painel.add(new JLabel("Volume Geral"), gbc);

        sldVolume = new JSlider(0, 100, 75);
        sldVolume.setMajorTickSpacing(25);
        sldVolume.setMinorTickSpacing(5);
        sldVolume.setPaintTicks(true);
        sldVolume.setPaintLabels(true);
        sldVolume.setFont(new Font("Monospaced", Font.PLAIN, 12));
        sldVolume.setBackground(CafeColors.FUNDO_BEGE);
        gbc.gridx = 1;
        painel.add(sldVolume, gbc);

        // --- Linha 4: Tema Visual ---
        gbc.gridx = 0; gbc.gridy = 3;
        painel.add(new JLabel("Tema Visual"), gbc);

        String[] temas = {"Padrão (Cafeteria)", "Escuro", "Claro", "Vintage"};
        cmbTemaVisual = new JComboBox<>(temas);
        cmbTemaVisual.setFont(new Font("Monospaced", Font.PLAIN, 14));
        cmbTemaVisual.setBackground(Color.WHITE);
        gbc.gridx = 1;
        painel.add(cmbTemaVisual, gbc);

        return painel;
    }

    private JPanel criarPainelInferior() {
        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        rodape.setOpaque(false);

        JButton btnSalvar = criarBotao("Salvar Configurações", CafeColors.BOTAO_ACERTO, e -> salvarConfiguracoes());
        JButton btnVoltar = criarBotao("Voltar", CafeColors.MARROM_ESCURO, e -> jogo.navegarPara(Tela.INICIAL));

        rodape.add(btnSalvar);
        rodape.add(btnVoltar);
        return rodape;
    }

    /**
     * Simula salvamento das configurações com feedback visual.
     */
    private void salvarConfiguracoes() {
        try {
            String statusEfeitos = chkEfeitosSonoros.isSelected() ? "ativados" : "desativados";
            String statusMusica = chkMusicaFundo.isSelected() ? "ativada" : "desativada";
            int volume = sldVolume.getValue();
            String tema = (String) cmbTemaVisual.getSelectedItem();

            String mensagem = String.format(
                """
                Configurações salvas com sucesso!
                
                • Efeitos: %s
                • Música: %s
                • Volume: %d%%
                • Tema: %s
                """,
                statusEfeitos, statusMusica, volume, tema
            );

            JOptionPane.showMessageDialog(
                this,
                mensagem,
                "Configurações Aplicadas",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception ex) {
            System.err.println("Erro ao salvar configurações: " + ex.getMessage());
            JOptionPane.showMessageDialog(
                this,
                "Erro ao salvar configurações. Tente novamente.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Atualiza os componentes com o estado atual do jogo.
     * <p>
     * Como não há persistência real, apenas reseta para valores padrão.
     * </p>
     */
    public void atualizarInfo() {
        chkEfeitosSonoros.setSelected(true);
        chkMusicaFundo.setSelected(true);
        sldVolume.setValue(75);
        cmbTemaVisual.setSelectedIndex(0);
    }

    /**
     * Cria botões estilizados com o padrão do projeto.
     */
    protected JButton criarBotao(String texto, Color cor, java.awt.event.ActionListener acao) {
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