package data.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por gerenciar a conexão com o banco de dados SQLite.
 * O banco de dados será um arquivo local (javabeans_historico.db) 
 * criado automaticamente na pasta raiz do projeto.
 */
public class ConexaoSQLite {

    // URL de conexão para o SQLite: "jdbc:sqlite:<nome_do_arquivo>.db"
    private static final String URL_CONEXAO = "jdbc:sqlite:javabeans_historico.db";

    /**
     * Estabelece e retorna uma nova conexão com o banco de dados SQLite.
     * * @return Objeto Connection pronto para ser usado.
     * @throws SQLException Se ocorrer um erro de acesso ao banco ou o driver não for encontrado.
     */
    public static Connection conectar() throws SQLException {
        try {
            // Carrega o driver JDBC do SQLite
            // Este passo é opcional em Java moderno, mas garante compatibilidade
            Class.forName("org.sqlite.JDBC");
            
            // Estabelece a conexão com o arquivo DB
            Connection conn = DriverManager.getConnection(URL_CONEXAO);
            
            // Opcional: Para verificar se o arquivo DB foi criado ou acessado
            System.out.println("✅ Conexão SQLite estabelecida: " + conn.getMetaData().getDatabaseProductName());
            
            return conn;
        } catch (ClassNotFoundException e) {
            // Lança uma exceção SQL se o driver não estiver no classpath (necessário adicionar o JAR do SQLite)
            throw new SQLException("❌ Driver SQLite JDBC não encontrado. Certifique-se de incluir o JAR no seu projeto.", e);
        }
    }
}