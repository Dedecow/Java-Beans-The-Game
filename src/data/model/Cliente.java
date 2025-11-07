package data.model;

import data.model.Menu.MenuItem;

public abstract class Cliente {
    private String nome;
    private MenuItem pedido;
    private String fraseCache; // NOVO: Armazena a frase gerada pelo DAO

    public Cliente(String nome, MenuItem pedido, String frase) {
        this.nome = nome;
        this.pedido = pedido;
        this.fraseCache = frase;
    }

    public String getNome() {
        return nome;
    }

    public MenuItem getPedido() {
        return pedido;
    }
    
    // Método 'comportamento()' agora é concreto, não abstrato
    public String comportamento() {
        return this.fraseCache; // Apenas retorna a frase recebida
    }
}