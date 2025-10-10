package data.model.Menu;

import java.util.Arrays;
import java.util.List;

public class Cardapio {
    private static final List<Ingrediente> ingredientes = Arrays.asList(
            new Ingrediente("Cafe"),
            new Ingrediente("Agua"),
            new Ingrediente("Leite"),
            new Ingrediente("Acucar"),
            new Ingrediente("Cacau"),
            new Ingrediente("Matcha")
    );

    private static final List<MenuItem> menu = Arrays.asList(
            new MenuItem("Cafe preto", Arrays.asList(ingredientes.get(0), ingredientes.get(1))),
            new MenuItem("Cafe latte", Arrays.asList(ingredientes.get(0), ingredientes.get(1), ingredientes.get(2))),
            new MenuItem("capuccino", Arrays.asList(ingredientes.get(0), ingredientes.get(1), ingredientes.get(2), ingredientes.get(4))),
            new MenuItem("Matcha", Arrays.asList(ingredientes.get(5), ingredientes.get(1)))
    );

    public static List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public static List<MenuItem> getMenu() {
        return menu;
    }

    public static List<Ingrediente> getTodosIngredientes() {
        return List.of(
                new Ingrediente("Cafe"),
                new Ingrediente("Agua"),
                new Ingrediente("Leite"),
                new Ingrediente("Acucar"),
                new Ingrediente("Cacau"),
                new Ingrediente("Matcha")
        );
    }
}
