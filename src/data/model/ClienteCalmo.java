package data.model;

import data.model.Menu.MenuItem;

public class ClienteCalmo extends Cliente {
    

    public ClienteCalmo(String nome, MenuItem pedido, String frase, String iconePath, int pontosAcerto, int pontosErro) {
        // Passa todas as informações para a classe pai (Cliente)
        super(nome, pedido, frase, iconePath, pontosAcerto, pontosErro);
    }
}