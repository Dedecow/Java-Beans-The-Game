package data.persistence;

import data.model.Menu.Ingrediente;
import data.model.Menu.MenuItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DAO para ler o cardápio (Ingredientes e Receitas) do banco de dados MySQL.
 */
public class CardapioDAOMySQL {

    /**
     * Busca todos os ingredientes ativos no banco de dados.
     * @return Lista de objetos Ingrediente.
     */
    public List<Ingrediente> getTodosIngredientes() {
        String sql = "SELECT id_ingrediente, nome FROM ingredientes";
        List<Ingrediente> lista = new ArrayList<>();
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexaoMySQL.conectar();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Usando o construtor que aceita ID (precisa ser criado)
                lista.add(new Ingrediente(
                    rs.getInt("id_ingrediente"), 
                    rs.getString("nome")
                ));
            }
        } catch (SQLException e) {
            throw new DBException("Falha ao ler ingredientes do MySQL", e);
        } finally {
            ConexaoMySQL.desconectar(rs);
            ConexaoMySQL.desconectar(stmt);
            ConexaoMySQL.desconectar(conn);
        }
        return lista;
    }

    /**
     * Busca o menu completo (Receitas + Ingredientes) do banco de dados.
     * Esta é a consulta mais complexa.
     * @return Lista de objetos MenuItem (Receitas) com seus ingredientes.
     */
    public List<MenuItem> getMenu() {
        // SQL que une as 3 tabelas
        String sql = """
            SELECT 
                mi.id_item, 
                mi.nome AS nome_receita, 
                i.id_ingrediente, 
                i.nome AS nome_ingrediente
            FROM menu_itens mi
            JOIN receitas r ON mi.id_item = r.fk_item
            JOIN ingredientes i ON r.fk_ingrediente = i.id_ingrediente
            ORDER BY mi.id_item;
        """;
        
        // Usamos um Map para agrupar os ingredientes por receita
        Map<Integer, MenuItem> menuMap = new HashMap<>();
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexaoMySQL.conectar();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int idReceita = rs.getInt("id_item");
                String nomeReceita = rs.getString("nome_receita");
                
                int idIngrediente = rs.getInt("id_ingrediente");
                String nomeIngrediente = rs.getString("nome_ingrediente");
                
                Ingrediente ingrediente = new Ingrediente(idIngrediente, nomeIngrediente);

                // Se a receita (MenuItem) ainda não está no Map, cria ela
                if (!menuMap.containsKey(idReceita)) {
                    menuMap.put(idReceita, new MenuItem(
                        idReceita, 
                        nomeReceita, 
                        new ArrayList<>() // Cria lista de ingredientes vazia
                    ));
                }
                
                // Adiciona o ingrediente atual na lista da receita correspondente
                menuMap.get(idReceita).getIngredientes().add(ingrediente);
            }

        } catch (SQLException e) {
            throw new DBException("Falha ao ler o menu (receitas) do MySQL", e);
        } finally {
            ConexaoMySQL.desconectar(rs);
            ConexaoMySQL.desconectar(stmt);
            ConexaoMySQL.desconectar(conn);
        }
        
        // Converte os valores do Map (os MenuItems) para uma lista
        return new ArrayList<>(menuMap.values());
    }
}