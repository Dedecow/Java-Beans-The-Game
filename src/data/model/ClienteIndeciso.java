package data.model;

import data.model.Menu.MenuItem;

public class ClienteIndeciso extends Cliente {
    

    public ClienteIndeciso(String nome, MenuItem pedido, String frase, String iconePath, int pontosAcerto, int pontosErro) {
        // Passa todas as informações para a classe pai (Cliente)
        super(nome, pedido, frase, iconePath, pontosAcerto, pontosErro);
    }
}