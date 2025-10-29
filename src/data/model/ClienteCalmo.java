package data.model;

import data.model.Menu.MenuItem;
// import data.setup.ClienteGen; // Não é mais necessário aqui

public class ClienteCalmo extends Cliente {
    
    // CONSTRUTOR CORRIGIDO
    public ClienteCalmo(String nome, MenuItem pedido) {
        super(nome); // Usa o nome recebido
        setPedido(pedido); // Salva o pedido recebido
    }

    @Override
    public String comportamento() {
        // Busca a frase correta e já inclui o pedido
        return FrasesClientes.getFraseComPedido(TipoDeCliente.CALMO);
    }
}