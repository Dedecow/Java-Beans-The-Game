package data.persistence;

import data.model.Historico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação concreta da persistência via MySQL.
 * DEVE IMPLEMENTAR IPersistencia
 */
public class HistoricoDAOMySQL implements IPersistencia {

    private static final String TABELA = "historico";

    public HistoricoDAOMySQL() {
        criarTabelaSeNaoExistir();
    }

    private void criarTabelaSeNaoExistir() {
        // SQL para MySQL: usa INT PRIMARY KEY AUTO_INCREMENT
        String sql = """
            CREATE TABLE IF NOT EXISTS historico (
                id INT PRIMARY KEY AUTO_INCREMENT,
                nomeCliente VARCHAR(255) NOT NULL,
                pontuacao INT NOT NULL
            );
        """;
        try (Connection conn = ConexaoMySQL.conectar(); // Usa a classe ConexaoMySQL corrigida
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("✅ Tabela MySQL checada/criada.");
        } catch (SQLException e) {
            System.err.println("❌ Erro ao criar tabela MySQL: " + e.getMessage());
        }
    }

    @Override
    public void salvar(Historico registro) {
        String sql = "INSERT INTO historico (nomeCliente, pontuacao) VALUES (?, ?)";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, registro.getNomeCliente());
            stmt.setInt(2, registro.getPontuacao());
            stmt.executeUpdate();
            System.out.println("💾 Registro salvo no MySQL: " + registro.getNomeCliente() + " - " + registro.getPontuacao());
        } catch (SQLException e) {
            System.err.println("❌ Erro ao salvar no MySQL: " + e.getMessage());
        }
    }

    @Override
    public Historico[] lerHistorico() {
        String sql = "SELECT nomeCliente, pontuacao FROM historico ORDER BY pontuacao DESC";
        List<Historico> lista = new ArrayList<>();

        try (Connection conn = ConexaoMySQL.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nome = rs.getString("nomeCliente");
                int pontos = rs.getInt("pontuacao");
                lista.add(new Historico(nome, pontos));
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao ler histórico do MySQL: " + e.getMessage());
        }

        return lista.toArray(new Historico[0]);
    }
}