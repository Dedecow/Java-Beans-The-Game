package data.model.Menu;

import java.util.List;

public class MenuItem {
    private int id; // NOVO
    private String name;
    private List<Ingrediente> ingredientes;

    /**
     * Construtor antigo (pode ser mantido se o Admin criar itens)
     */
    public MenuItem(String name, List<Ingrediente> ingredientes) {
        this.name = name;
        this.ingredientes = ingredientes;
    }
    
    /**
     * NOVO CONSTRUTOR (para o DAO ler do BD)
     */
    public MenuItem(int id, String name, List<Ingrediente> ingredientes) {
        this.id = id;
        this.name = name;
        this.ingredientes = ingredientes;
    }

    // NOVO GETTER
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    @Override
    public String toString() {
        return name + " -> " + ingredientes;
    }
}