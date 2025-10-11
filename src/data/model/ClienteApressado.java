package data.model;

import data.model.Menu.MenuItem;
import data.setup.ClienteGen;

public class ClienteApressado extends Cliente{
    public ClienteApressado(String nome, MenuItem pedido) {
        super(ClienteGen.gerarNome());
    }

    @Override
    public String comportamento() {
        return FrasesClientes.getFraseComPedido(TipoDeCliente.APRESSADO);
    }
}

