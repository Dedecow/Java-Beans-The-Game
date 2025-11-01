package data.persistence;

import data.model.Historico;
import data.connection.ConexaoSQLite;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação concreta da persistência via SQLite.
 * Cria o banco local automaticamente (javabeans_historico.db).
 */
public class HistoricoDAOSQLite implements IPersistencia {

    private static final String TABELA = "historico";

    public HistoricoDAOSQLite() {
        criarTabelaSeNaoExistir();
    }

    private void criarTabelaSeNaoExistir() {
        String sql = """
            CREATE TABLE IF NOT EXISTS historico (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nomeCliente TEXT NOT NULL,
                pontuacao INTEGER NOT NULL
            );
        """;
        try (Connection conn = ConexaoSQLite.conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("❌ Erro ao criar tabela SQLite: " + e.getMessage());
        }
    }

    @Override
    public void salvar(Historico registro) {
        String sql = "INSERT INTO historico (nomeCliente, pontuacao) VALUES (?, ?)";
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, registro.getNomeCliente());
            stmt.setInt(2, registro.getPontuacao());
            stmt.executeUpdate();
            System.out.println("💾 Registro salvo: " + registro.getNomeCliente() + " - " + registro.getPontuacao());
        } catch (SQLException e) {
            System.err.println("❌ Erro ao salvar no SQLite: " + e.getMessage());
        }
    }

    @Override
    public Historico[] lerHistorico() {
        String sql = "SELECT nomeCliente, pontuacao FROM historico ORDER BY pontuacao DESC";
        List<Historico> lista = new ArrayList<>();

        try (Connection conn = ConexaoSQLite.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nome = rs.getString("nomeCliente");
                int pontos = rs.getInt("pontuacao");
                lista.add(new Historico(nome, pontos));
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao ler histórico do SQLite: " + e.getMessage());
        }

        return lista.toArray(new Historico[0]);
    }
}
