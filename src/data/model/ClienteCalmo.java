package data.model;

import data.model.Menu.MenuItem;
import data.setup.ClienteGen;

public class ClienteCalmo extends Cliente {
    public ClienteCalmo(String nome, MenuItem pedido) {
        super(ClienteGen.gerarNome());
    }

    @Override
    public String comportamento() {
        return FrasesClientes.getFraseComPedido(TipoDeCliente.CALMO);
    }
}
