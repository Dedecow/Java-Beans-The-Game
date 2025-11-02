package data.persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties; // Import necessário

/**
 * Gerencia a conexão com o banco de dados MySQL, lendo as
 * credenciais de um arquivo 'config.properties' externo.
 */
public class ConexaoMySQL {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL_BD;
    private static final String USUARIO;
    private static final String SENHA;

    // Bloco estático: Roda UMA VEZ quando a classe é carregada
    static {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
            
            URL_BD = props.getProperty("db.url");
            USUARIO = props.getProperty("db.user");
            SENHA = props.getProperty("db.pass");
            
            if (URL_BD == null || USUARIO == null || SENHA == null) {
                throw new IOException("Propriedades 'db.url', 'db.user', ou 'db.pass' não encontradas no config.properties");
            }
            
        } catch (IOException e) {
            System.err.println("❌ ERRO FATAL: Não foi possível ler o 'config.properties'");
            System.err.println("Certifique-se que o arquivo 'config.properties' está na raiz do projeto (C:\\...\\Java-Beans-The-Game)");
            e.printStackTrace();
            
            // -------------------------------------------------------------------
            // ESTA É A CORREÇÃO: A classe correta é 'ExceptionInInitializerError'
            // -------------------------------------------------------------------
            throw new ExceptionInInitializerError(e);
            // -------------------------------------------------------------------
        }
    }

    /**
     * Tenta estabelecer e retornar uma nova conexão com o banco de dados.
     */
    public static Connection conectar() throws SQLException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL_BD, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL JDBC não encontrado. Inclua o JAR no classpath.", e);
        }
    }
    
    /**
     * O método main continua funcionando para testes
     */
    public static void main(String[] args) {
        System.out.println("Tentando conectar com credenciais do 'config.properties'...");
        try (Connection testeConexao = conectar()) {
            if (testeConexao != null) {
                System.out.println("✅ TESTE BEM-SUCEDIDO: Conexão MySQL estabelecida com sucesso!");
            }
        } catch (SQLException e) {
            System.err.println("❌ TESTE FALHOU: Erro ao conectar ao MySQL.");
            e.printStackTrace();
        }
    }
}