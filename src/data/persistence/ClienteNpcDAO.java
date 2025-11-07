package data.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DAO para ler dados da tabela clientes_npc.
 */
public class ClienteNpcDAO {

    /**
     * Busca um nome aleatório do banco de dados.
     * @return String com um nome de cliente.
     */
    public String getNomeAleatorio() {
        // SQL específico do MySQL para buscar item aleatório
        String sql = "SELECT nome FROM clientes_npc ORDER BY RAND() LIMIT 1";
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexaoMySQL.conectar();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return rs.getString("nome");
            } else {
                return "Cliente Misterioso"; // Fallback
            }

        } catch (SQLException e) {
            throw new DBException("Falha ao buscar nome aleatório de cliente", e);
        } finally {
            ConexaoMySQL.desconectar(rs);
            ConexaoMySQL.desconectar(stmt);
            ConexaoMySQL.desconectar(conn);
        }
    }
}