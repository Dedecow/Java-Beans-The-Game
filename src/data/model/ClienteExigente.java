package data.model;

import data.model.Menu.MenuItem;
// A importação java.awt.* não era necessária aqui.

public class ClienteExigente extends Cliente {
    
    /**
     * CONSTRUTOR ATUALIZADO: Recebe a frase do ClienteGen (via DAO).
     */
    public ClienteExigente(String nome, MenuItem pedido, String frase) {
        // Passa todas as informações para a classe pai (Cliente)
        super(nome, pedido, frase);
    }

    // O método comportamento() foi removido, pois agora está na classe base.
}