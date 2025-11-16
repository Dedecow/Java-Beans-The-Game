package data.model;

import data.model.Menu.MenuItem;

public abstract class Cliente {
    private String nome;
    private MenuItem pedido;
    private String fraseCache; 
    
    private String iconePath;
    private int pontosAcerto;
    private int pontosErro;
    
    public Cliente(String nome, MenuItem pedido, String frase, String iconePath, int pontosAcerto, int pontosErro) {
        this.nome = nome;
        this.pedido = pedido;
        this.fraseCache = frase;
        this.iconePath = iconePath;
        this.pontosAcerto = pontosAcerto;
        this.pontosErro = pontosErro; // Armazene como negativo (ex: -5)
    }

    public String getNome() {
        return nome;
    }

    public MenuItem getPedido() {
        return pedido;
    }
    
    public String comportamento() {
        return this.fraseCache;
    }
    
    public String getIconePath() {
        return iconePath;
    }

    public int getPontosAcerto() {
        return pontosAcerto;
    }

    public int getPontosErro() {
        return pontosErro;
    }
}