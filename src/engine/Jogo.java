package engine;

import data.model.Historico;
import data.persistence.IPersistencia;
import data.persistence.HistoricoDAOSQLite; 
import view.MainUI; 
import view.Tela;
import data.model.Cliente;
import data.setup.ClienteGen;
import data.model.Menu.Ingrediente;
import data.model.Menu.Cardapio;
import data.model.Menu.MenuItem;
import java.util.Arrays;
import java.util.List;

/**
 * Classe central do motor do jogo.
 * * Responsável por controlar o fluxo principal do gameplay,
 * coordenar a pontuação, clientes, navegação de telas e persistência.
 */
public class Jogo {
    private int pontuacaoAtual;
    private Cliente clienteAtual; 
    private final IPersistencia persistencia;
    private MainUI orquestrador;
    private boolean jogoAtivo;

    // ============================================================
    // CONSTRUTOR
    // ============================================================
    public Jogo() {
        this.persistencia = new HistoricoDAOSQLite(); 
        this.pontuacaoAtual = 0;
        this.jogoAtivo = false;
        Cardapio.getMenu(); // Força o carregamento do cardápio
        this.clienteAtual = ClienteGen.gerarClienteRandom(); 
    }
    
    // ============================================================
    // CONFIGURAÇÃO E CICLO DE VIDA
    // ============================================================

    public void setUI(MainUI orquestrador) {
        this.orquestrador = orquestrador;
    }
    
    public void iniciar() {
        System.out.println("🎮 Jogo: Motor iniciado");
    }
    
    public void iniciarJogo() {
        if (orquestrador == null) {
            System.err.println("❌ Jogo: Orquestrador não definido!");
            return;
        }
        this.pontuacaoAtual = 0;
        this.jogoAtivo = true;
        this.clienteAtual = ClienteGen.gerarClienteRandom();
        
        // Navega para a tela de transição
        orquestrador.mostrarTela(Tela.CLIENTE_CHEGANDO);

        System.out.println("🎮 Jogo: Partida iniciada. Novo cliente: " + clienteAtual.getNome());
    }

    public void finalizarJogo() {
        if (orquestrador == null) return;
        this.jogoAtivo = false;
        salvarHistorico(clienteAtual.getNome());
        orquestrador.mostrarTela(Tela.GAME_OVER);
        System.out.println("🎮 Jogo: Partida finalizada - Pontuação: " + pontuacaoAtual);
    }

    public void reiniciarPartida() {
        if (orquestrador == null) return;
        this.pontuacaoAtual = 0;
        this.jogoAtivo = false;
        this.clienteAtual = ClienteGen.gerarClienteRandom();
        orquestrador.mostrarTela(Tela.INICIAL);
        System.out.println("🎮 Jogo: Reiniciando para menu inicial");
    }

    public void navegarPara(Tela tela) {
        if (orquestrador != null) {
            orquestrador.mostrarTela(tela);
        }
    }

    // ============================================================
    // MECÂNICA DO JOGO
    // ============================================================

    public void entregarPedido(Ingrediente[] bandeja) {
        if (!jogoAtivo) return;
        MenuItem pedidoCorreto = clienteAtual.getPedido();
        
        if (pedidoCorreto == null) {
            System.err.println("❌ Jogo: Cliente " + clienteAtual.getNome() + " está sem pedido!");
            registrarPontuacao(false);
            this.clienteAtual = ClienteGen.gerarClienteRandom();
            navegarPara(Tela.CLIENTE_CHEGANDO);
            return; 
        }
        
        Ingrediente[] receitaCorreta = pedidoCorreto.getIngredientes().toArray(new Ingrediente[0]);
        boolean acertou = compararReceitas(bandeja, receitaCorreta);
        registrarPontuacao(acertou);
        this.clienteAtual = ClienteGen.gerarClienteRandom();
        System.out.println("🎮 Jogo: Próximo cliente: " + clienteAtual.getNome());
        navegarPara(Tela.CLIENTE_CHEGANDO);
    }

    private boolean compararReceitas(Ingrediente[] bandeja, Ingrediente[] receitaCorreta) {
        if (bandeja.length != receitaCorreta.length) {
            System.out.println("🎮 Jogo: Errou (quantidade de ingredientes errada)");
            return false;
        }

        String[] nomesBandeja = Arrays.stream(bandeja)
                .map(Ingrediente::getName)
                .sorted()
                .toArray(String[]::new);
        String[] nomesReceita = Arrays.stream(receitaCorreta)
                .map(Ingrediente::getName)
                .sorted()
                .toArray(String[]::new);

        boolean acertou = Arrays.equals(nomesBandeja, nomesReceita);
        if (!acertou) {
            System.out.println("🎮 Jogo: Errou (ingredientes errados)");
        }
        return acertou;
    }

    private void registrarPontuacao(boolean acertou) {
        if (!jogoAtivo) return;
        if (acertou) {
            pontuacaoAtual += 10;
            System.out.println("🎮 Pedido correto! +10 pontos");
        } else {
            pontuacaoAtual = Math.max(0, pontuacaoAtual - 5);
            System.out.println("🎮 Pedido errado! -5 pontos");
        }
        if (orquestrador != null) {
            orquestrador.atualizarStatus("Em Jogo | Pontuação: " + pontuacaoAtual);
        }
    }
    
    private void salvarHistorico(String nomeCliente) {
        try {
            Historico historico = new Historico(nomeCliente, pontuacaoAtual);
            persistencia.salvar(historico);
        } catch (Exception e) {
            System.err.println("❌ Jogo: Erro ao salvar histórico: " + e.getMessage());
        }
    }

    // ============================================================
    // MÉTODOS DE APOIO ÀS TELAS
    // ============================================================
    public MenuItem getPedidoClienteAtual() {
        if (this.clienteAtual != null) {
            return this.clienteAtual.getPedido();
        }
        return null;
    }

    public List<MenuItem> getCardapio() {
        try {
            return Cardapio.getMenu();
        } catch (Exception e) {
            System.err.println("⚠️ Erro ao obter cardápio: " + e.getMessage());
            return List.of();
        }
    }

    public List<Ingrediente> getTodosIngredientes() {
        try {
            return Cardapio.getIngredientes();
        } catch (Exception e) {
            System.err.println("⚠️ Erro ao obter ingredientes: " + e.getMessage());
            return List.of();
        }
    }

    // ============================================================
    // GETTERS GERAIS
    // ============================================================

    public int getPontuacao() {
        return pontuacaoAtual;
    }
    
    public String getClienteAtual() {
        return (clienteAtual != null) ? clienteAtual.getNome() : "Nenhum cliente";
    }

    public String getFraseClienteAtual() {
        return (clienteAtual != null) ? clienteAtual.comportamento() : "Bem-vindo!";
    }
    
    public boolean isJogoAtivo() {
        return jogoAtivo;
    }

    public Historico[] getRanking() {
        try {
            return persistencia.lerHistorico();
        } catch (Exception e) {
            System.err.println("❌ Erro ao ler histórico: " + e.getMessage());
            return new Historico[0];
        }
    }
}