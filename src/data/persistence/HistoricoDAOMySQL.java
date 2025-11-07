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
        // CORREÇÃO: Trocamos "DATE DEFAULT CURRENT_DATE" por "DATETIME DEFAULT CURRENT_TIMESTAMP"
        String sql = """
            CREATE TABLE IF NOT EXISTS historico (
                id INT PRIMARY KEY AUTO_INCREMENT,
                nomeJogador VARCHAR(255) NOT NULL,
                pontuacao INT NOT NULL,
                dataPartida DATETIME DEFAULT CURRENT_TIMESTAMP
            );
        """;
        
        Connection conn = null;
        Statement stmt = null;

        try {
            // Esta conexão agora funciona
            conn = ConexaoMySQL.conectar();
            stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("✅ Tabela MySQL checada/criada.");
            
        } catch (SQLException e) {
            System.err.println("❌ Erro ao criar tabela MySQL: " + e.getMessage());
            throw new DBException("Falha ao verificar/criar tabela 'historico'", e);
        } finally {
            ConexaoMySQL.desconectar(stmt);
            ConexaoMySQL.desconectar(conn);
        }
    }

    @Override
    public void salvar(Historico registro) {
        // Este SQL não precisa de 'dataPartida' pois o banco cuidará disso (DEFAULT)
        String sql = "INSERT INTO historico (nomeJogador, pontuacao) VALUES (?, ?)";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConexaoMySQL.conectar();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, registro.getNomeJogador());
            stmt.setInt(2, registro.getPontuacao());
            stmt.executeUpdate();
            
            System.out.println("✅ Registro salvo no MySQL: " + registro.getNomeJogador() + " - " + registro.getPontuacao());

        } catch (SQLException e) {
            System.err.println("❌ Erro ao salvar no MySQL: " + e.getMessage());
            throw new DBException("Falha ao salvar registro no MySQL", e);
        } finally {
            ConexaoMySQL.desconectar(stmt);
            ConexaoMySQL.desconectar(conn);
        }
    }

    @Override
    public Historico[] lerHistorico() {
        String sql = "SELECT nomeJogador, pontuacao FROM historico ORDER BY pontuacao DESC";
        List<Historico> lista = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexaoMySQL.conectar();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String nomeJogador = rs.getString("nomeJogador");
                int pontos = rs.getInt("pontuacao");
                lista.add(new Historico(nomeJogador, pontos));
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao ler histórico do MySQL: " + e.getMessage());
            throw new DBException("Falha ao ler histórico do MySQL", e);
        } finally {
            ConexaoMySQL.desconectar(rs);
            ConexaoMySQL.desconectar(stmt);
            ConexaoMySQL.desconectar(conn);
        }

        return lista.toArray(new Historico[0]);
    }
}