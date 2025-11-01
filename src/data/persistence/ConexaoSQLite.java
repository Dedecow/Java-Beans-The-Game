package data.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gerencia a conexão com o banco de dados SQLite local.
 */
public class ConexaoSQLite {

    private static final String URL_CONEXAO = "jdbc:sqlite:javabeans_historico.db";

    public static Connection conectar() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC"); // garante carregamento do driver
            return DriverManager.getConnection(URL_CONEXAO);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver SQLite JDBC não encontrado. Inclua o JAR no classpath.", e);
        }
    }
}
