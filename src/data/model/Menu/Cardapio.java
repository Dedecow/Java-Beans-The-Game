package data.model.Menu;

import java.util.Arrays;
import java.util.List;

public class Cardapio {
    private static final List<Ingrediente> ingredientes = Arrays.asList(
            new Ingrediente("Café"),
            new Ingrediente("Água"),
            new Ingrediente("Leite"),
            new Ingrediente("Leite vegetal"),
            new Ingrediente("Açúcar"),
            new Ingrediente("Cacau"),
            new Ingrediente("Matcha"),
            new Ingrediente("Chantilly"),
            new Ingrediente("Canela")
    );

    private static final List<MenuItem> menu = Arrays.asList(
            new MenuItem("Café Preto", Arrays.asList(
                    ingredientes.get(0), // Café
                    ingredientes.get(1)  // Água
            )),
            new MenuItem("Café Latte", Arrays.asList(
                    ingredientes.get(0), // Café
                    ingredientes.get(1), // Água
                    ingredientes.get(2)  // Leite
            )),
            new MenuItem("Cappuccino", Arrays.asList(
                    ingredientes.get(0), // Café
                    ingredientes.get(1), // Água
                    ingredientes.get(2), // Leite
                    ingredientes.get(4), // Açúcar
                    ingredientes.get(5)  // Cacau
            )),
            new MenuItem("Matcha Latte", Arrays.asList(
                    ingredientes.get(6), // Matcha
                    ingredientes.get(1), // Água
                    ingredientes.get(2)  // Leite
            )),
            new MenuItem("Chocolate Quente", Arrays.asList(
                    ingredientes.get(5), // Cacau
                    ingredientes.get(2), // Leite
                    ingredientes.get(4), // Açúcar
                    ingredientes.get(7)  // Chantilly
            )),
            new MenuItem("Café com Canela", Arrays.asList(
                    ingredientes.get(0), // Café
                    ingredientes.get(1), // Água
                    ingredientes.get(8)  // Canela
            ))
    );

    public static List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public static List<MenuItem> getMenu() {
        return menu;
    }

    public static List<Ingrediente> getTodosIngredientes() {
        return ingredientes;
    }
}
