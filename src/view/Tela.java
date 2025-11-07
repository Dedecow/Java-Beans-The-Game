package view;

/**
 * Enum que define todas as telas disponíveis no jogo.
 * Usado pelo MainUI para orquestrar as transições.
 * * Versão refatorada: remove duplicatas e escopo futuro (Admin).
 */
public enum Tela {

    INICIAL,
    CLIENTE_CHEGANDO,
    JOGO,
    PREPARO,
    RECEITA,
    RANKING,
    CONFIGURACOES,
    DETALHES_CLIENTE,
    SOBRE,
    GAME_OVER
    
}