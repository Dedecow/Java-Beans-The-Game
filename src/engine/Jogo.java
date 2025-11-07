package engine;

import data.model.Historico;
import data.persistence.IPersistencia;
import data.persistence.HistoricoDAOMySQL;
import data.persistence.DBException; 
import view.MainUI; 
import view.Tela;
import data.model.Cliente;
import data.setup.ClienteGen;
import data.model.Menu.Ingrediente;
import data.model.Menu.Cardapio;
import data.model.Menu.MenuItem;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane; 

/**
 * REVISÃO: Lógica de inicialização da persistência movida para um
 * método helper (inicializarPersistencia) para resolver erro de 
 * compilação "variable might already have been assigned" em 'final'.
 */
public class Jogo {
    private int pontuacaoAtual;
    private Cliente clienteAtual; 
    
    // A persistência continua final, o que é uma boa prática
    private final IPersistencia persistencia; 
    
    private MainUI orquestrador;
    private boolean jogoAtivo;
    private String nomeJogador; 

    // ============================================================
    // CONSTRUTOR
    // ============================================================
    
    /**
     * CONSTRUTOR CORRIGIDO
     * Agora chama um método helper para inicializar a variável 'final'.
     * Isso garante que ela seja atribuída apenas UMA vez, 
     * resolvendo o erro de compilação.
     */
    public Jogo() {
        // MUDANÇA: A lógica de conexão foi movida para um método separado.
        this.persistencia = inicializarPersistencia(); 
        
        // O resto do jogo é inicializado normalmente
        this.pontuacaoAtual = 0;
        this.jogoAtivo = false;
        this.nomeJogador = "Barista"; // Valor padrão
        Cardapio.getMenu(); // Força o carregamento do cardápio
        this.clienteAtual = ClienteGen.gerarClienteRandom(); 
    }
    
    // ============================================================
    // MÉTODO DE INICIALIZAÇÃO (NOVO)
    // ============================================================

    /**
     * NOVO MÉTODO HELPER
     * Tenta inicializar a persistência.
     * Esta abordagem resolve o erro de compilação "variable might already
     * have been assigned" da variável 'final'.
     * @return Uma instância de IPersistencia (DAO) ou null se a conexão falhar.
     */
    private IPersistencia inicializarPersistencia() {
        try {
            System.out.println("...Tentando conectar ao banco de dados...");
            // Se o banco estiver offline (como no seu print), 
            // a DBException será lançada AQUI.
            IPersistencia dao = new HistoricoDAOMySQL(); 
            
            System.out.println("✅ Conexão com banco de dados estabelecida.");
            return dao;
            
        } catch (DBException e) {
            // Se a exceção ocorrer, pulamos para cá.
            System.err.println("❌ ERRO FATAL DE BANCO DE DADOS: Não foi possível conectar.");
            e.printStackTrace(); // Mostra o erro completo no console
            
            // Avisa o usuário de forma amigável
            JOptionPane.showMessageDialog(null, 
                "Não foi possível conectar ao banco de dados.\n" +
                "O jogo funcionará normalmente, mas o histórico de pontuação\n" +
                "não poderá ser salvo ou lido.\n\n" +
                "Causa: " + e.getMessage(), 
                "Erro de Conexão", 
                JOptionPane.ERROR_MESSAGE);
            
            return null; // Retorna nulo para o modo "offline"
        }
    }
    
    // ============================================================
    // CONFIGURAÇÃO E CICLO DE VIDA (Sem alterações)
    // ============================================================

    public void setUI(MainUI orquestrador) {
        this.orquestrador = orquestrador;
    }
    
    public void iniciar() {
        System.out.println("🎮 Jogo: Motor iniciado");
    }
    
    public void iniciarJogo(String nomeJogador) {
        if (orquestrador == null) {
            System.err.println("❌ Jogo: Orquestrador não definido!");
            return;
        }
        
        this.nomeJogador = (nomeJogador != null && !nomeJogador.trim().isEmpty()) 
            ? nomeJogador.trim() : "Barista";
        
        this.pontuacaoAtual = 0;
        this.jogoAtivo = true;
        this.clienteAtual = ClienteGen.gerarClienteRandom();
        
        orquestrador.mostrarTela(Tela.CLIENTE_CHEGANDO);

        System.out.println("🎮 Jogo: Partida iniciada. Jogador: " + this.nomeJogador + 
                         " | Cliente: " + clienteAtual.getNome());
    }

    public void iniciarJogo() {
        iniciarJogo("Barista"); 
    }

    public void finalizarJogo() {
        if (orquestrador == null) return;
        this.jogoAtivo = false;
        
        salvarHistorico(this.nomeJogador);
        
        orquestrador.mostrarTela(Tela.GAME_OVER);
        System.out.println("🎮 Jogo: Partida finalizada - Jogador: " + nomeJogador + 
                         " | Pontuação: " + pontuacaoAtual);
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
    // MECÂNICA DO JOGO (Sem alterações)
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
        System.out.println("🎮 Jogo: Próximo cliente: " + clienteAtual.getNome() + 
                         " | Jogador: " + nomeJogador + " | Pontuação: " + pontuacaoAtual);
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
            System.out.println("🎮 Pedido correto! +10 pontos | Jogador: " + nomeJogador);
        } else {
            pontuacaoAtual = Math.max(0, pontuacaoAtual - 5);
            System.out.println("🎮 Pedido errado! -5 pontos | Jogador: " + nomeJogador);
        }
        if (orquestrador != null) {
            orquestrador.atualizarStatus("Jogador: " + nomeJogador + " | Pontuação: " + pontuacaoAtual);
        }
    }
    
    /**
     * MÉTODO MODIFICADO
     * Agora verifica se a persistência é nula antes de tentar salvar.
     */
    private void salvarHistorico(String nomeJogador) {
        if (persistencia == null) {
            System.err.println("⚠️ Jogo: Persistência nula. Pulando salvamento de histórico.");
            return;
        }
        
        try {
            Historico historico = new Historico(nomeJogador, pontuacaoAtual);
            persistencia.salvar(historico);
            System.out.println("✅ Histórico salvo para jogador: " + nomeJogador);
        
        } catch (DBException e) { 
            System.err.println("❌ Jogo: Erro ao salvar histórico: " + e.getMessage());
        }
    }

    // ============================================================
    // MÉTODOS DE APOIO ÀS TELAS (Sem alterações)
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
    // GETTERS GERAIS (Sem alterações)
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

    /**
     * MÉTODO MODIFICADO
     * Agora verifica se a persistência é nula antes de tentar ler.
     */
    public Historico[] getRanking() {
        if (persistencia == null) {
            System.err.println("⚠️ Jogo: Persistência nula. Retornando ranking vazio.");
            return new Historico[0]; 
        }
        
        try {
            return persistencia.lerHistorico();
        } catch (DBException e) { 
            System.err.println("❌ Erro ao ler histórico: " + e.getMessage());
            return new Historico[0]; 
        }
    }
    
    public String getNomeJogador() {
        return nomeJogador;
    }
}