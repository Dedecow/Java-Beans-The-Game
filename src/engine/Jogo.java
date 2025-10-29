package engine;

import data.model.Historico;
import data.persistence.IPersistencia;
// Importa a implementação específica que será usada
import data.persistence.HistoricoDAOSQLite; 
import view.MainUI; 
import view.Tela;
import data.model.Cliente;
import data.setup.ClienteGen;
import data.model.Menu.Ingrediente;
import data.model.Menu.MenuItem;
import java.util.Arrays;

public class Jogo {
    private int pontuacaoAtual;
    private Cliente clienteAtual; 
    private final IPersistencia persistencia; // Continua genérico
    private MainUI orquestrador;
    private boolean jogoAtivo;

    public Jogo() {
        // --- AQUI ESTÁ A ARQUITETURA SIMPLES ---
        // O Jogo decide qual persistência usar.
        this.persistencia = new HistoricoDAOSQLite(); 
        // --- FIM DA MUDANÇA ---
        
        this.pontuacaoAtual = 0;
        this.jogoAtivo = false;
        this.clienteAtual = ClienteGen.gerarClienteRandom(); 
    }
    
    // O restante do arquivo está 100% correto
    // com todas as correções de bugs que fizemos.

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
        orquestrador.mostrarTela(Tela.JOGO);
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
    
    public void entregarPedido(Ingrediente[] bandeja) {
        if (!jogoAtivo) return;
        MenuItem pedidoCorreto = clienteAtual.getPedido();
        
        if (pedidoCorreto == null) {
            System.err.println("❌ Jogo: Cliente " + clienteAtual.getNome() + " está sem pedido!");
            registrarPontuacao(false);
            
            // Corrige o fluxo para pegar um novo cliente mesmo se o anterior falhar
            this.clienteAtual = ClienteGen.gerarClienteRandom();
            navegarPara(Tela.JOGO); // Volta para a tela de jogo
            return; 
        }
        
        Ingrediente[] receitaCorreta = pedidoCorreto.getIngredientes().toArray(new Ingrediente[0]);
        boolean acertou = compararReceitas(bandeja, receitaCorreta);
        registrarPontuacao(acertou);
        this.clienteAtual = ClienteGen.gerarClienteRandom();
        System.out.println("🎮 Jogo: Próximo cliente: " + clienteAtual.getNome());
        navegarPara(Tela.JOGO);
    }

    private boolean compararReceitas(Ingrediente[] bandeja, Ingrediente[] receitaCorreta) {
        if (bandeja.length != receitaCorreta.length) {
            System.out.println("🎮 Jogo: Errou (Quantidade de ingredientes errada)");
            return false;
        }
        String[] nomesBandeja = new String[bandeja.length];
        for(int i = 0; i < bandeja.length; i++) {
            nomesBandeja[i] = bandeja[i].getName();
        }
        String[] nomesReceita = new String[receitaCorreta.length];
        for(int i = 0; i < receitaCorreta.length; i++) {
            nomesReceita[i] = receitaCorreta[i].getName();
        }
        Arrays.sort(nomesBandeja);
        Arrays.sort(nomesReceita);
        boolean acertou = Arrays.equals(nomesBandeja, nomesReceita);
        if (!acertou) {
            System.out.println("🎮 Jogo: Errou (Ingredientes errados)");
        }
        return acertou;
    }

    private void registrarPontuacao(boolean acertou) {
        if (!jogoAtivo) return;
        if (acertou) {
            pontuacaoAtual += 10;
            System.out.println("🎮 Jogo: Pedido correto! +10 pontos");
        } else {
            pontuacaoAtual = Math.max(0, pontuacaoAtual - 5);
            System.out.println("🎮 Jogo: Pedido errado! -5 pontos");
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
    
    public int getPontuacao() {
        return pontuacaoAtual;
    }
    
    public String getClienteAtual() {
        if (this.clienteAtual != null) {
            return this.clienteAtual.getNome();
        }
        return "Nenhum cliente";
    }

    public String getFraseClienteAtual() {
        if (this.clienteAtual != null) {
            return this.clienteAtual.comportamento();
        }
        return "Bem-vindo!";
    }
    
    public boolean isJogoAtivo() {
        return jogoAtivo;
    }

    public Historico[] getRanking() {
        try {
            return persistencia.lerHistorico();
        } catch (Exception e) {
            System.err.println("❌ Jogo: Erro ao ler histórico: " + e.getMessage());
            return new Historico[0];
        }
    }
}