package data.model.Menu;

public class Ingrediente {
    private int id; // NOVO
    private String name;

    // Construtor antigo (pode ser mantido, se necessário)
    public Ingrediente(String name) {
        this.name = name;
    }
    
    // NOVO CONSTRUTOR (para o DAO)
    public Ingrediente(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // NOVO GETTER
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}