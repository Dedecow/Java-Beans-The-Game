package data.setup;

import data.model.*;
import data.model.Menu.Cardapio;
import data.model.Menu.MenuItem;

import java.util.List;
import java.util.Random;

public class ClienteGen {
    private static final Random GERADOR = new Random();
    private static final String[] NOMES = {"Maria", "João", "Ana", "Pedro", "Sofia", "Lucas"};

    private ClienteGen() {} // Evita instanciação

    public static String gerarNome() {
        return NOMES[GERADOR.nextInt(NOMES.length)];
    }

    public static MenuItem gerarPedido() {
        List<MenuItem> menu = Cardapio.getMenu();
        if (menu == null || menu.isEmpty()) {
            throw new IllegalStateException("Cardápio não inicializado ou vazio!");
        }
        return menu.get(GERADOR.nextInt(menu.size()));
    }

    public static Cliente gerarClienteRandom() {
        String nome = gerarNome();
        MenuItem pedido = gerarPedido();

        return switch (GERADOR.nextInt(4)) {
            case 0 -> new ClienteApressado(nome, pedido);
            case 1 -> new ClienteExigente(nome, pedido);
            case 2 -> new ClienteCalmo(nome, pedido);
            default -> new ClienteIndeciso(nome, pedido);
        };
    }
}
