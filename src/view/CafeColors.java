package view;

import java.awt.Color;

/**
 * Classe utilitária para encapsular todas as constantes de cores 
 * usadas na interface gráfica, seguindo o tema retrô cafeteria.
 */
public final class CafeColors {

    private CafeColors() {}
     // Cores da Tela Jogo
    public static final Color BOTAO_ACERTO = new Color(50, 150, 50);  // Verde (Iniciar Preparo)
    public static final Color BOTAO_ERRO = new Color(175, 50, 50);    // Vermelho (Errar Pedido)
    public static final Color PAINEL_CLIENTE = new Color(220, 190, 160); // Bege/Creme claro (Painel do Pedido)
    public static final Color PAINEL_FRASE = new Color(200, 150, 100);   // Marrom Claro (Painel da Frase)

    // Cores Principais do Tema (Baseadas na Tela Game Over)
    public static final Color FUNDO_PRINCIPAL = new Color(139, 0, 0);       // Vinho/Marrom Avermelhado Escuro (da Tela GameOver)
    public static final Color FUNDO_BEGE = new Color(245, 245, 220);         // Bege Claro (simulando papel ou balcão)
    public static final Color MARROM_ESCURO = new Color(102, 51, 0);         // Marrom escuro (para botões, madeira)
    
    // NOVO: Cor de fundo da Tela Inicial (Marrom bem escuro, inspirado na imagem)
    public static final Color FUNDO_CAFE_ESCURO = new Color(70, 40, 20);
    
    // Cores para os Painéis/Destaques
    public static final Color DESTAQUE_ROSA = new Color(255, 192, 203, 200); // Rosa Claro Transparente (para painéis de mensagem)
    
    // Cores de Texto
    public static final Color TEXTO_PADRAO = FUNDO_PRINCIPAL;                 // Texto principal no bege/rosa (usando a cor do fundo para contraste)
    public static final Color TEXTO_BRANCO = Color.WHITE;                    // Texto em áreas escuras

}