    package engine;

    import data.model.Historico;
    import data.persistence.IPersistencia;
    import data.persistence.HistoricoDAOMySQL; 
    import data.persistence.DBException; 
    import data.persistence.CardapioDAOMySQL;
    import data.persistence.ClienteNpcDAO;
    import data.persistence.FrasesDAO;
    import view.MainUI; 
    import view.Tela;
    import data.model.Cliente;
    import data.setup.ClienteGen;
    import data.model.Menu.Ingrediente;
    import data.model.Menu.MenuItem;
    import java.util.Arrays;
    import java.util.List;
    import javax.swing.JOptionPane;

    public class Jogo {
        private int pontuacaoAtual;
        private Cliente clienteAtual;
        private final IPersistencia persistencia;
        private MainUI orquestrador;
        private boolean jogoAtivo;
        private String nomeJogador; 
        
        private static final String ICONE_GENERICO_PATH = "assets/fotos/cliente-generica.png";


        // DAOs de Conteúdo
        private final CardapioDAOMySQL cardapioDAO;
        private final ClienteNpcDAO clienteNpcDAO;
        private final FrasesDAO frasesDAO;

        // ============================================================
        // CONSTRUTOR
        // ============================================================
        public Jogo() {
            this.persistencia = inicializarPersistencia(); 

            if (this.persistencia != null) {
                this.cardapioDAO = new CardapioDAOMySQL();
                this.clienteNpcDAO = new ClienteNpcDAO();
                this.frasesDAO = new FrasesDAO();
                this.clienteAtual = gerarProximoCliente();
            } else {
                this.cardapioDAO = null;
                this.clienteNpcDAO = null;
                this.frasesDAO = null;
                this.clienteAtual = null;   
            }

            this.pontuacaoAtual = 0;
            this.jogoAtivo = false;
            this.nomeJogador = "KazukiYamamoto"; 
            //this.clienteAtual = gerarProximoCliente();
        }

        private IPersistencia inicializarPersistencia() {
            try {
                System.out.println("...Tentando conectar ao banco de dados...");
                IPersistencia dao = new HistoricoDAOMySQL(); 
                System.out.println(" Conexão com banco de dados estabelecida.");
                return dao;
            } catch (DBException e) {
                System.err.println(" ERRO FATAL DE BANCO DE DADOS: Não foi possível conectar.");
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, 
                    "Não foi possível conectar ao banco de dados.\n" +
                    "Causa: " + e.getMessage(), 
                    "Erro de Conexão", 
                    JOptionPane.ERROR_MESSAGE);
                return null; 
            }
        }

        // ============================================================
        // MÉTODOS DE CICLO DE VIDA
        // ============================================================

        public void setUI(MainUI orquestrador) {
            this.orquestrador = orquestrador;
        }

        public void iniciar() {
            System.out.println("Jogo: Motor iniciado");
        }

        public void iniciarJogo(String nomeJogador) {
            if (orquestrador == null) {
                System.err.println("Jogo: Orquestrador não definido!");
                return;
            }

                this.nomeJogador = (nomeJogador != null && !nomeJogador.trim().isEmpty()) 
                ? nomeJogador.trim() : "KazukiYamamoto";

            this.pontuacaoAtual = 0;
            this.jogoAtivo = true;
            this.clienteAtual = gerarProximoCliente();

            orquestrador.mostrarTela(Tela.CLIENTE_CHEGANDO);

            System.out.println("Jogo: Partida iniciada. Jogador: " + this.nomeJogador + 
                             " | Cliente: " + clienteAtual.getNome());
        }

        public void finalizarJogo() {
            if (orquestrador == null) return;
            this.jogoAtivo = false;

            salvarHistorico(this.nomeJogador);

            orquestrador.mostrarTela(Tela.GAME_OVER);
            System.out.println(" Jogo: Partida finalizada - Jogador: " + nomeJogador + 
                             " | Pontuação: " + pontuacaoAtual);
        }

        public void reiniciarPartida() {
            if (orquestrador == null) return;
            this.pontuacaoAtual = 0;
            this.jogoAtivo = false;
            this.clienteAtual = gerarProximoCliente();
            orquestrador.mostrarTela(Tela.INICIAL);
            System.out.println("Jogo: Reiniciando para menu inicial");
        }

        public void navegarPara(Tela tela) {
            if (orquestrador != null) {
                orquestrador.mostrarTela(tela);
            }
        }

        // ============================================================
        // MECÂNICA DO JOGO
        // ============================================================

        private Cliente gerarProximoCliente() {
            if (persistencia == null) {
                throw new IllegalStateException("Banco de dados offline. Não é possível gerar clientes.");
            }

            return ClienteGen.gerarClienteRandom(clienteNpcDAO, cardapioDAO, frasesDAO);
        }

        public void entregarPedido(Ingrediente[] bandeja) {
            if (!jogoAtivo) return;
            MenuItem pedidoCorreto = clienteAtual.getPedido();

            if (pedidoCorreto == null) {
                registrarPontuacao(false);
                this.clienteAtual = gerarProximoCliente();
                navegarPara(Tela.CLIENTE_CHEGANDO);
                return; 
            }

            Ingrediente[] receitaCorreta = pedidoCorreto.getIngredientes().toArray(new Ingrediente[0]);
            boolean acertou = compararReceitas(bandeja, receitaCorreta);
            registrarPontuacao(acertou);

            this.clienteAtual = gerarProximoCliente();
            System.out.println("Jogo: Próximo cliente: " + clienteAtual.getNome() + 
                             " | Jogador: " + nomeJogador + " | Pontuação: " + pontuacaoAtual);
            navegarPara(Tela.CLIENTE_CHEGANDO);
        }

        private boolean compararReceitas(Ingrediente[] bandeja, Ingrediente[] receitaCorreta) {
            if (bandeja.length != receitaCorreta.length) {
                System.out.println("Jogo: Errou (quantidade de ingredientes errada)");
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
                System.out.println("Jogo: Errou (ingredientes errados)");
            }
            return acertou;
        }

        private void registrarPontuacao(boolean acertou) {
            if (!jogoAtivo) return;
            int valor;
            if (acertou) {
                valor = clienteAtual.getPontosAcerto();
                System.out.println("Pedido correto! +" + valor + " pontos | Jogador: " + nomeJogador);
            } else {
                valor = clienteAtual.getPontosErro();
                System.out.println("Pedido errado! " + valor + " pontos | Jogador: " + nomeJogador);
            }
            
            pontuacaoAtual = Math.max(0, pontuacaoAtual + valor); 
            
            if (orquestrador != null) {
                orquestrador.atualizarStatus("Jogador: " + nomeJogador + " | Pontuação: " + pontuacaoAtual);
            }
        }

        private void salvarHistorico(String nomeJogador) {
            if (persistencia == null) {
                System.err.println("Jogo: Persistência nula. Pulando salvamento de histórico.");
                return;
            }

            try {
                Historico historico = new Historico(nomeJogador, pontuacaoAtual);
                persistencia.salvar(historico);
                System.out.println("Histórico salvo para jogador: " + nomeJogador);

            } catch (DBException e) { 
                System.err.println("Jogo: Erro ao salvar histórico: " + e.getMessage());
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
            if (cardapioDAO == null) {
                System.err.println(" Erro ao obter cardápio: Modo Offline.");
                return List.of();
            }
            try {
                return cardapioDAO.getMenu();
            } catch (Exception e) {
                System.err.println(" Erro ao obter cardápio: " + e.getMessage());
                return List.of();
            }
        }

        public List<Ingrediente> getTodosIngredientes() {
            if (cardapioDAO == null) {
                System.err.println("️ Erro ao obter ingredientes: Modo Offline.");
                return List.of();
            }
            try {
                return cardapioDAO.getTodosIngredientes();
            } catch (Exception e) {
                System.err.println("️ Erro ao obter ingredientes: " + e.getMessage());
                return List.of();
            }
        }
        
        public String getIconePathClienteAtual() {
            if (this.clienteAtual != null) {
                String path = this.clienteAtual.getIconePath();
                if (path != null && !path.trim().isEmpty()) {
                    return path;
                }
            }
            // Retorna o "fallback" genérico que setamos acima, caso não encontre cliente ou path na classe clienteGen
            return ICONE_GENERICO_PATH; 
        }
        
        public String getFraseClienteAtual() {
            return (clienteAtual != null) ? clienteAtual.comportamento() : "Banco de dados offline";
        }

        // ============================================================
        // GETTERS GERAIS
        // ============================================================

        public int getPontuacao() {
            return pontuacaoAtual;
        }
        
        public boolean isJogadorTreinado() {
            // A lógica é calculada toda vez que o método é chamado.
            // Se a pontuação for 121 ou mais, retorna true.
            // Se for 120 ou menos, retorna false.
            return this.pontuacaoAtual > 120;
        }
        
        public String getClienteAtual() {
            return (clienteAtual != null) ? clienteAtual.getNome() : "Nenhum cliente";
        }

        //public String getFraseClienteAtual() {
        //    return (clienteAtual != null) ? clienteAtual.comportamento() : "Bem-vindo!";
        //}

        public boolean isJogoAtivo() {
            return jogoAtivo;
        }

        public Historico[] getRanking() {
            if (persistencia == null) {
                System.err.println("️ Jogo: Persistência nula. Retornando ranking vazio.");
                return new Historico[0]; 
            }

            try {
                return persistencia.lerHistorico();
            } catch (DBException e) { 
                System.err.println(" Erro ao ler histórico: " + e.getMessage());
                return new Historico[0]; 
            }
        }

        public String getNomeJogador() {
            return nomeJogador;
        }
    }