package data.persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConexaoMySQL {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL_BD;
    private static final String USUARIO;
    private static final String SENHA;

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
            e.printStackTrace();
            throw new DBException("Falha ao carregar config.properties", e);
        }
    }

    public static Connection conectar() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL_BD, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            throw new DBException("Driver MySQL JDBC não encontrado. Inclua o JAR no classpath.", e);
        } catch (SQLException e) {
            throw new DBException("Erro ao conectar ao banco de dados.", e);
        }
    }

    public static void desconectar(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar Connection: " + e.getMessage());
            }
        }
    }


    public static void desconectar(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar Statement: " + e.getMessage());
            }
        }
    }
    

    public static void desconectar(PreparedStatement pstmt) {
        desconectar((Statement) pstmt);
    }


    public static void desconectar(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar ResultSet: " + e.getMessage());
            }
        }
    }
}