package data.persistence;

import data.model.Historico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoDAOMySQL implements IPersistencia {

    private static final String TABELA = "historico";

    public HistoricoDAOMySQL() {
        criarTabelaSeNaoExistir();
    }

    private void criarTabelaSeNaoExistir() {
        String sql = """
            CREATE TABLE IF NOT EXISTS historico (
                id INT PRIMARY KEY AUTO_INCREMENT,
                nomeCliente VARCHAR(255) NOT NULL,
                pontuacao INT NOT NULL
            );
        """;
        
        // 1. Declarar recursos fora do try
        Connection conn = null;
        Statement stmt = null;

        try {
            // 2. Abrir recursos
            conn = ConexaoMySQL.conectar();
            stmt = conn.createStatement();
            
            // 3. Executar lógica
            stmt.execute(sql);
            System.out.println("✅ Tabela MySQL checada/criada.");
            
        } catch (SQLException e) {
            // 4. Tratar exceções de SQL
            System.err.println("❌ Erro ao criar tabela MySQL: " + e.getMessage());
            throw new DBException("Falha ao verificar/criar tabela 'historico'", e);
        } finally {
            // 5. Fechar recursos na ordem INVERSA (primeiro stmt, depois conn)
            ConexaoMySQL.desconectar(stmt);
            ConexaoMySQL.desconectar(conn);
        }
    }

    @Override
    public void salvar(Historico registro) {
        String sql = "INSERT INTO historico (nomeCliente, pontuacao) VALUES (?, ?)";
        
        // 1. Declarar recursos fora do try
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            // 2. Abrir recursos
            conn = ConexaoMySQL.conectar();
            stmt = conn.prepareStatement(sql);
            
            // 3. Executar lógica
            stmt.setString(1, registro.getNomeCliente());
            stmt.setInt(2, registro.getPontuacao());
            stmt.executeUpdate();
            System.out.println(" Registro salvo no MySQL: " + registro.getNomeCliente() + " - " + registro.getPontuacao());

        } catch (SQLException e) {
            // 4. Tratar exceções de SQL
            System.err.println(" Erro ao salvar no MySQL: " + e.getMessage());
            throw new DBException("Falha ao salvar registro no MySQL", e);
        } finally {
            // 5. Fechar recursos na ordem INVERSA
            ConexaoMySQL.desconectar(stmt);
            ConexaoMySQL.desconectar(conn);
        }
    }

    @Override
    public Historico[] lerHistorico() {
        String sql = "SELECT nomeCliente, pontuacao FROM historico ORDER BY pontuacao DESC";
        List<Historico> lista = new ArrayList<>();

        // 1. Declarar recursos fora do try
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 2. Abrir recursos
            conn = ConexaoMySQL.conectar();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            // 3. Executar lógica
            while (rs.next()) {
                String nome = rs.getString("nomeCliente");
                int pontos = rs.getInt("pontuacao");
                lista.add(new Historico(nome, pontos));
            }

        } catch (SQLException e) {
            // 4. Tratar exceções de SQL
            System.err.println("Erro ao ler histórico do MySQL: " + e.getMessage());
            throw new DBException("Falha ao ler histórico do MySQL", e);
        } finally {
            // 5. Fechar recursos na ordem INVERSA (rs, stmt, conn)
            ConexaoMySQL.desconectar(rs);
            ConexaoMySQL.desconectar(stmt);
            ConexaoMySQL.desconectar(conn);
        }

        return lista.toArray(new Historico[0]);
    }
}