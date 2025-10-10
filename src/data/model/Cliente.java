package data.model;

import data.model.Menu.MenuItem;

public abstract class Cliente {
    private String nome;
    private MenuItem pedido;

    public Cliente(String nome) {
        this.nome = nome;
        this.pedido = null;
    }

    public String getNome() {
        return nome;
    }

    public MenuItem getPedido() {
        return pedido;
    }

    public void setPedido(MenuItem pedido) {
        this.pedido = pedido;
    }

    public abstract String comportamento();
}
