package view;

import engine.Jogo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import data.model.Menu.MenuItem;

/**
 * Tela de transição que mostra a chegada de um novo cliente.
 * Exibe uma animação, a imagem e a FRASE do cliente antes de ir para a tela de jogo.
 */
public class TelaClienteChegando extends JPanel implements MainUI.Atualizavel {

    private final Jogo jogo;
    private Timer timer;
    private ImageIcon iconeCliente;

    /**
     * Frases estáticas de carregamento.
     */
    private final String[] frasesLoading = {
        "Um cliente está entrando na cafeteria...",
        "Que bebida ele vai pedir...",
        "Será que acerto os ingredientes? ..."
    };


    private String fraseClienteAtual;
    private int etapaAtual = 0;
    private int totalEtapas;

    public TelaClienteChegando(Jogo jogo) {
        this.jogo = jogo;
        //this.iconeCliente = carregarIcone("assets/fotos/cliente-generica.png", 80);
        configurarLayout();
        configurarAnimacao();
    }
    
    
    private void configurarAnimacao() {
        this.totalEtapas = frasesLoading.length + 1;
        this.fraseClienteAtual = "Carregando..."; // Valor padrão

        timer = new Timer(800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (etapaAtual < totalEtapas - 1) {
                    etapaAtual++; // Avança o estado
                    repaint();    // Redesenha o painel com a nova etapa
                } else {
                    timer.stop();
                    jogo.navegarPara(Tela.JOGO); // Vai para a tela principal
                }
            }
        });
    }


    @Override
    public void atualizarInfo() {
        // 1. Reinicia o estado da animação
        this.etapaAtual = 0; //
        
        // 2. (NOVO) Busca o ÍCONE ATUAL (o Jogo.java já fornece um fallback)
        // O Jogo.java já garante um fallback de path
        String pathDoCliente = jogo.getIconePathClienteAtual(); 
        
        // Apenas tenta carregar o que o Jogo mandou
        this.iconeCliente = carregarIcone(pathDoCliente, 80); //

        // 3. (NOVO) Busca a FRASE e o PEDIDO
        try {
            // Pega a frase comportamental (ex: "Estou com pressa!")
            String fraseComportamental = jogo.getFraseClienteAtual(); 
            
            // Pega o item do pedido
            MenuItem pedido = jogo.getPedidoClienteAtual(); 
            
            // Define o nome do pedido, com um fallback
            String nomePedido = "café";
            if (pedido != null) {
                nomePedido = pedido.getName(); 
            }
            
            // Combina as duas para a frase final que será exibida
            this.fraseClienteAtual = fraseComportamental + " " + nomePedido + ".";

        } catch (Exception e) {
            // O fallback agora inclui um pedido genérico
            System.err.println("Erro ao carregar frase/pedido do cliente: " + e.getMessage());
            this.fraseClienteAtual = "Hmm... o que vou pedir? Acho que uma média."; //
        }
        
        // 4. (Re)calcula o total de etapas
        this.totalEtapas = frasesLoading.length + 1; //

        // 5. Inicia o timer da animação
        if (timer != null && !timer.isRunning()) { //
            timer.start(); //
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // --- 1. Ícone do cliente (Imagem) ---
        if (iconeCliente != null) {
            int xIcone = (getWidth() - iconeCliente.getIconWidth()) / 2;
            int yIcone = getHeight() / 2 - 70; 
            g2d.drawImage(iconeCliente.getImage(), xIcone, yIcone, this);
        } else {
            // Fallback (se a imagem falhar)
            g2d.setFont(new Font("Monospaced", Font.PLAIN, 16));
            g2d.setColor(CafeColors.TEXTO_BRANCO);
            g2d.drawString("[imagem do cliente]", getWidth() / 2 - 50, getHeight() / 2 - 50);
        }
        
        // --- 2. Frase de transição (Dinâmica) ---
        String fraseParaExibir;
        
        if (etapaAtual < frasesLoading.length) {
            // Se a etapa atual está dentro do array de loading...
            fraseParaExibir = frasesLoading[etapaAtual];
        } else {
            // Senão, é a última etapa (a frase do cliente)
            fraseParaExibir = this.fraseClienteAtual;
        }
        
        g2d.setFont(new Font("Monospaced", Font.PLAIN, 16));
        g2d.setColor(CafeColors.TEXTO_BRANCO);
        
        FontMetrics fmFrase = g2d.getFontMetrics();
        int xFrase = (getWidth() - fmFrase.stringWidth(fraseParaExibir)) / 2;
        int yFrase = getHeight() / 2 + 50; 
        g2d.drawString(fraseParaExibir, xFrase, yFrase);
        
        // --- 3. Barra de progresso ---
        int barraLargura = 200;
        int barraAltura = 10;
        int xBarra = (getWidth() - barraLargura) / 2;
        int yBarra = yFrase + 30;
        
        g2d.setColor(CafeColors.MARROM_CLARO);
        g2d.fillRect(xBarra, yBarra, barraLargura, barraAltura);
        
        // O progresso é (etapaAtual + 1) de um total de 'totalEtapas'
        int progressoLargura = (barraLargura * (etapaAtual + 1)) / totalEtapas;
        g2d.setColor(CafeColors.BOTAO_ACERTO);
        g2d.fillRect(xBarra, yBarra, progressoLargura, barraAltura);
        
        g2d.setColor(CafeColors.TEXTO_BRANCO);
        g2d.drawRect(xBarra, yBarra, barraLargura, barraAltura);
    }
    
    private void configurarLayout() {
        setLayout(new BorderLayout());
        setBackground(CafeColors.FUNDO_CAFE_ESCURO);
        setPreferredSize(new Dimension(600, 400));
    }

    /**
     * Método utilitário para carregar e escalar imagens com segurança.
     * *** CORRIGIDO O BUG ***
     */
    private ImageIcon carregarIcone(String caminho, int larguraAlvo) {
        try {
            ImageIcon originalIcon = new ImageIcon(caminho);
            Image originalImage = originalIcon.getImage(); // Pega a imagem

            // --- CORREÇÃO AQUI ---
            // Chamando getWidth/getHeight na 'originalImage', não 'originalIcon'
            int originalLargura = originalImage.getWidth(null);
            int originalAltura = originalImage.getHeight(null);
            // --- FIM DA CORREÇÃO ---

            if (larguraAlvo == -1 || originalLargura <= larguraAlvo) {
                return originalIcon;
            }

            int novaAltura = (originalAltura * larguraAlvo) / originalLargura;
            Image imagemEscalada = originalImage.getScaledInstance(larguraAlvo, novaAltura, Image.SCALE_SMOOTH);
            return new ImageIcon(imagemEscalada);

        } catch (Exception e) {
            System.err.println("⚠️ Erro ao carregar imagem: " + caminho + " | " + e.getMessage());
            return null;
        }
    }
}