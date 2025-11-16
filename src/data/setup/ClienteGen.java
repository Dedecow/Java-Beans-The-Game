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
    
    //Endereços dos Icones de cada cliente
    private static final String ICONE_APRESSADO = "assets/fotos/cliente-apressado.png";
    private static final String ICONE_CALMO = "assets/fotos/cliente-calmo.png";
    private static final String ICONE_EXIGENTE = "assets/fotos/cliente-exigente.png";
    private static final String ICONE_INDECISO = "assets/fotos/cliente-confuso.png";
    
    private ClienteGen() {} 
    
    
    public static Cliente gerarClienteRandom(
            ClienteNpcDAO npcDAO, 
            CardapioDAOMySQL cardapioDAO, 
            FrasesDAO frasesDAO) 
    {
        TipoDeCliente tipoCliente = switch (GERADOR.nextInt(4)) {
            case 0 -> TipoDeCliente.APRESSADO;
            case 1 -> TipoDeCliente.EXIGENTE;
            case 2 -> TipoDeCliente.CALMO;
            default -> TipoDeCliente.INDECISO;
        };

        String nome = npcDAO.getNomeAleatorio();
        
        List<MenuItem> menu = cardapioDAO.getMenu();
        if (menu == null || menu.isEmpty()) {
            throw new IllegalStateException("Cardápio do BD está vazio ou nulo!");
        }
        MenuItem pedido = menu.get(GERADOR.nextInt(menu.size()));
        
        String frase = frasesDAO.getFraseAleatoria(tipoCliente);

        // 5. DEFINE OS ATRIBUTOS (usando as constantes)
        String iconePath;
        int pontosAcerto;
        int pontosErro;

        switch (tipoCliente) {
            case APRESSADO:
                iconePath = ICONE_APRESSADO;
                pontosAcerto = 25;
                pontosErro = -10;
                break;
            case EXIGENTE:
                iconePath = ICONE_EXIGENTE;
                pontosAcerto = 20;
                pontosErro = -10;
                break;
            case INDECISO:
                iconePath = ICONE_INDECISO;
                pontosAcerto = 10;
                pontosErro = -0;
                break;
            case CALMO:
            default:
                iconePath = ICONE_CALMO;
                pontosAcerto = 20;
                pontosErro = -5;
                break;
        }
        
        // 6. Monta o Cliente
        // (Requer que os construtores de ClienteApressado, etc. sejam atualizados)
        return switch (tipoCliente) {
            case APRESSADO -> new ClienteApressado(nome, pedido, frase, iconePath, pontosAcerto, pontosErro);
            case EXIGENTE -> new ClienteExigente(nome, pedido, frase, iconePath, pontosAcerto, pontosErro);
            case CALMO -> new ClienteCalmo(nome, pedido, frase, iconePath, pontosAcerto, pontosErro);
            default -> new ClienteIndeciso(nome, pedido, frase, iconePath, pontosAcerto, pontosErro);
        };
    }
}