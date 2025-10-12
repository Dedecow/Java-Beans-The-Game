package view;

import java.awt.Color;

/**
 * Classe utilitária para encapsular todas as constantes de cores 
 * usadas na interface gráfica, seguindo o tema retrô cafeteria.
 */
public final class CafeColors {

    private CafeColors() {} // Evita instanciação
    
    // ==================================================
    // CORES PRINCIPAIS DO TEMA
    // ==================================================
    
    // Cores de fundo
    public static final Color FUNDO_PRINCIPAL = new Color(139, 0, 0);       // Vinho/Marrom Avermelhado Escuro
    public static final Color FUNDO_BEGE = new Color(245, 245, 220);        // Bege Claro (papel/balcão)
    public static final Color FUNDO_CAFE_ESCURO = new Color(70, 40, 20);    // Marrom bem escuro (Tela Inicial)
    
    // Cores de elementos
    public static final Color MARROM_ESCURO = new Color(102, 51, 0);        // Marrom escuro (botões, madeira)
    public static final Color MARROM_CLARO = new Color(174, 125, 85);       // Marrom claro (destaques)
    public static final Color MARROM_MEIO = new Color(139, 69, 19);         // Marrom médio (SaddleBrown)
    
    // ==================================================
    // CORES PARA AÇÕES E ESTADOS
    // ==================================================
    
    // Botões de ação
    public static final Color BOTAO_ACERTO = new Color(50, 150, 50);        // Verde (ações positivas)
    public static final Color BOTAO_ERRO = new Color(175, 50, 50);          // Vermelho (ações negativas/cancelar)
    public static final Color BOTAO_AVISO = new Color(218, 165, 32);        // Dourado (GoldenRod - avisos)
    public static final Color BOTAO_INFO = new Color(30, 144, 255);         // Azul (DodgerBlue - informações)
    
    // ==================================================
    // CORES PARA PAINÉIS E SEÇÕES
    // ==================================================
    
    public static final Color PAINEL_CLIENTE = new Color(220, 190, 160);    // Bege/Creme claro (Painel do Pedido)
    public static final Color PAINEL_FRASE = new Color(200, 150, 100);      // Marrom Claro (Painel da Frase)
    public static final Color PAINEL_DESTAQUE = new Color(255, 248, 220);   // Cornsilk (destaque suave)
    public static final Color PAINEL_SECUNDARIO = new Color(245, 222, 179); // Wheat (fundo secundário)
    
    // ==================================================
    // CORES PARA DESTAQUES E EFEITOS
    // ==================================================
    
    public static final Color DESTAQUE_ROSA = new Color(255, 192, 203, 200); // Rosa Claro Transparente
    public static final Color DESTAQUE_DOURADO = new Color(255, 215, 0, 180); // Gold transparente
    public static final Color DESTAQUE_VERDE = new Color(144, 238, 144, 180); // LightGreen transparente
    
    // ==================================================
    // CORES PARA RANKING E CONQUISTAS
    // ==================================================
    
    public static final Color OURO = new Color(255, 215, 0);                // Gold (1º lugar)
    public static final Color PRATA = new Color(192, 192, 192);             // Silver (2º lugar)
    public static final Color BRONZE = new Color(205, 127, 50);             // Bronze (3º lugar)
    public static final Color RANKING_NORMAL = new Color(245, 245, 245);    // Branco fumê (demais posições)
    
    // ==================================================
    // CORES DE TEXTO
    // ==================================================
    
    public static final Color TEXTO_PADRAO = new Color(139, 0, 0);          // Vinho escuro (texto principal)
    public static final Color TEXTO_BRANCO = Color.WHITE;                   // Texto em áreas escuras
    public static final Color TEXTO_PRETO = Color.BLACK;                    // Texto em áreas claras
    public static final Color TEXTO_CINZA = new Color(105, 105, 105);       // DimGray (texto secundário)
    public static final Color TEXTO_DOUrado = new Color(184, 134, 11);      // DarkGoldenRod (texto premium)
    
    // ==================================================
    // CORES PARA FORMULÁRIOS E INPUTS
    // ==================================================
    
    public static final Color INPUT_FUNDO = Color.WHITE;                    // Fundo de campos de texto
    public static final Color INPUT_BORDA = new Color(160, 82, 45);         // Sienna (borda de inputs)
    public static final Color INPUT_FOCUS = new Color(30, 144, 255, 50);    // Azul com transparência (foco)
    
    // ==================================================
    // CORES PARA STATUS E INDICADORES
    // ==================================================
    
    public static final Color STATUS_SUCESSO = new Color(34, 139, 34);      // ForestGreen (sucesso)
    public static final Color STATUS_ERRO = new Color(178, 34, 34);         // FireBrick (erro)
    public static final Color STATUS_AVISO = new Color(255, 140, 0);        // DarkOrange (aviso)
    public static final Color STATUS_INFO = new Color(70, 130, 180);        // SteelBlue (informação)
    
    // ==================================================
    // CORES PARA PROGRESSO E BARRAS
    // ==================================================
    
    public static final Color BARRA_PROGRESSO_FUNDO = new Color(220, 220, 220); // Cinza claro
    public static final Color BARRA_PROGRESSO_PREENCHIMENTO = new Color(46, 139, 87); // SeaGreen
    public static final Color BARRA_PROGRESSO_TEXTO = Color.BLACK;          // Texto na barra de progresso
    
    // ==================================================
    // CORES ESPECÍFICAS PARA TELAS
    // ==================================================
    
    // TelaJogo
    public static final Color JOGO_TEMPO_NORMAL = new Color(34, 139, 34);   // Verde (tempo normal)
    public static final Color JOGO_TEMPO_AVISO = new Color(255, 165, 0);    // Laranja (tempo acabando)
    public static final Color JOGO_TEMPO_URGENTE = new Color(178, 34, 34);  // Vermelho (tempo crítico)
    
    // TelaPreparo
    public static final Color PREPARO_INGREDIENTE_NORMAL = new Color(245, 222, 179); // Wheat
    public static final Color PREPARO_INGREDIENTE_SELECIONADO = new Color(210, 180, 140); // Tan
    
    // TelaHistorico
    public static final Color HISTORICO_LINHA_IMPAR = new Color(253, 245, 230); // OldLace
    public static final Color HISTORICO_LINHA_PAR = new Color(250, 240, 230);   // Linen
    
    // TelaConfiguracoes
    public static final Color CONFIG_SECAO = new Color(222, 184, 135);      // BurlyWood (seções)
    
    // ==================================================
    // CORES PARA HOVER E ESTADOS INTERATIVOS
    // ==================================================
    
    public static final Color HOVER_ESCURO = new Color(85, 40, 0);          // Hover em elementos escuros
    public static final Color HOVER_CLARO = new Color(210, 180, 140);       // Hover em elementos claros
    public static final Color HOVER_VERDE = new Color(60, 180, 60);         // Hover em botões verdes
    public static final Color HOVER_VERMELHO = new Color(200, 60, 60);      // Hover em botões vermelhos
    
    // ==================================================
    // CORES PARA BORDAS E SEPARADORES
    // ==================================================
    
    public static final Color BORDA_CLARA = new Color(210, 180, 140);       // Tan
    public static final Color BORDA_ESCURA = new Color(101, 67, 33);        // Marrom escuro para bordas
    public static final Color SEPARADOR = new Color(160, 82, 45);           // Sienna (separadores)
}