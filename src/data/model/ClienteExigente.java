package data.model;

import data.model.Menu.MenuItem;
// import data.setup.ClienteGen; // Não é mais necessário aqui
import java.awt.*; // (Esta importação pode ser removida se não for usada)

public class ClienteExigente extends Cliente {
    
    // CONSTRUTOR CORRIGIDO
    public ClienteExigente(String nome, MenuItem pedido) {
        super(nome); // Usa o nome recebido
        setPedido(pedido); // Salva o pedido recebido
    }

    @Override
    public String comportamento() {
        // Busca a frase correta e já inclui o pedido
        return FrasesClientes.getFraseComPedido(TipoDeCliente.EXIGENTE);
    }
}