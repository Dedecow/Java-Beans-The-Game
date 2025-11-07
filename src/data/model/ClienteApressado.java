package data.model;

import data.model.Menu.MenuItem;

public class ClienteApressado extends Cliente {
    
    /**
     * CONSTRUTOR ATUALIZADO: Recebe a frase do ClienteGen (via DAO).
     */
    public ClienteApressado(String nome, MenuItem pedido, String frase) {
        // Passa todas as informações para a classe pai (Cliente)
        super(nome, pedido, frase);
    }
    
    // O método comportamento() foi removido, pois agora está na classe base.
}