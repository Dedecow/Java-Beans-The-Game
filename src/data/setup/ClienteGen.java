package data.setup;

import data.model.*;
import data.model.Menu.MenuItem;
// DAOs são necessários para buscar dados do BD
import data.persistence.CardapioDAOMySQL;
import data.persistence.ClienteNpcDAO;
import data.persistence.FrasesDAO;

import java.util.List;
import java.util.Random;

public class ClienteGen {
    private static final Random GERADOR = new Random();
    
    // As listas estáticas (NOMES, BEBIDAS, etc.) foram REMOVIDAS.

    private ClienteGen() {} // Evita instanciação

    /**
     * MÉTODO PRINCIPAL ATUALIZADO
     * Agora recebe os DAOs (Injeção de Dependência) do Jogo.
     * Ele busca Nomes, Pedidos e Frases do banco de dados.
     */
    public static Cliente gerarClienteRandom(
            ClienteNpcDAO npcDAO, 
            CardapioDAOMySQL cardapioDAO, 
            FrasesDAO frasesDAO) 
    {
        // 1. Gera o Tipo de Cliente (local)
        TipoDeCliente tipoCliente = switch (GERADOR.nextInt(4)) {
            case 0 -> TipoDeCliente.APRESSADO;
            case 1 -> TipoDeCliente.EXIGENTE;
            case 2 -> TipoDeCliente.CALMO;
            default -> TipoDeCliente.INDECISO;
        };

        // 2. Busca Nome (do BD)
        String nome = npcDAO.getNomeAleatorio();
        
        // 3. Busca Pedido (do BD)
        List<MenuItem> menu = cardapioDAO.getMenu();
        if (menu == null || menu.isEmpty()) {
            throw new IllegalStateException("Cardápio do BD está vazio ou nulo!");
        }
        MenuItem pedido = menu.get(GERADOR.nextInt(menu.size()));
        
        // 4. Busca Frase (do BD)
        String frase = frasesDAO.getFraseAleatoria(tipoCliente);

        // 5. Monta o Cliente
        // (Requer que os construtores de ClienteApressado, etc. sejam atualizados)
        return switch (tipoCliente) {
            case APRESSADO -> new ClienteApressado(nome, pedido, frase);
            case EXIGENTE -> new ClienteExigente(nome, pedido, frase);
            case CALMO -> new ClienteCalmo(nome, pedido, frase);
            default -> new ClienteIndeciso(nome, pedido, frase);
        };
    }
}