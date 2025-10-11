package data.model;

import data.model.Menu.MenuItem;
import data.setup.ClienteGen;

import java.awt.*;

public class ClienteExigente extends Cliente{
    public ClienteExigente(String nome, MenuItem pedido) {
        super(ClienteGen.gerarNome());
    }

    @Override
    public String comportamento() {
        return FrasesClientes.getFraseComPedido(TipoDeCliente.EXIGENTE);
    }
}
