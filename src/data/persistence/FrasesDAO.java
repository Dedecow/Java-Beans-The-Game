package data.persistence;

import data.model.TipoDeCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO para ler dados da tabela frases.
 */
public class FrasesDAO {

    /**
     * Busca uma frase aleatória do banco de dados para um tipo de cliente.
     * @param tipo O Enum TipoDeCliente (APRESSADO, CALMO, etc.)
     * @return String com uma frase.
     */
    public String getFraseAleatoria(TipoDeCliente tipo) {
        String sql = "SELECT texto_frase FROM frases WHERE tipo_cliente = ? ORDER BY RAND() LIMIT 1";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexaoMySQL.conectar();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, tipo.name()); // Converte o Enum para String (ex: "APRESSADO")
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("texto_frase");
            } else {
                return "Olá! O que temos hoje?"; // Fallback
            }

        } catch (SQLException e) {
            throw new DBException("Falha ao buscar frase aleatória", e);
        } finally {
            ConexaoMySQL.desconectar(rs);
            ConexaoMySQL.desconectar(stmt);
            ConexaoMySQL.desconectar(conn);
        }
    }
}