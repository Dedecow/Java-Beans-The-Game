package data.model.Menu;

import java.util.List;

public class MenuItem {
    private String name;
    private List<Ingrediente> ingredientes;

    public MenuItem(String name, List<Ingrediente> ingredientes) {
        this.name = name;
        this.ingredientes = ingredientes;
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

