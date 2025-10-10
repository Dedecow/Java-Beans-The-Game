package data.model.Menu;

public class Ingrediente {
    private String name;

    public Ingrediente(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
