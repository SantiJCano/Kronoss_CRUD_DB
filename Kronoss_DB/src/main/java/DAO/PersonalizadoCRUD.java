package DAO;

import model.Personalizado;
import javax.swing.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class PersonalizadoCRUD {
    private final String url = "jdbc:mysql://localhost:3306/kronoss_";
    private final String user = "root";
    private final String password = "";

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void agregarPersonalizado(Personalizado personalizado) {
        String sql = "INSERT INTO recordatoriospersonalizados (id, categoria, notas) VALUES (?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmtquery = conn.prepareStatement(sql)) {
            stmtquery.setInt(1, personalizado.getId());
            stmtquery.setString(2, personalizado.getCategoria());
            stmtquery.setString(3, personalizado.getNotas());
            stmtquery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Personalizado> listarPersonalizados() {
        List<Personalizado> lista = new ArrayList<>();
        String sql = "SELECT * FROM recordatoriospersonalizados";
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Personalizado personalizado = new Personalizado();
                personalizado.setId(rs.getInt("id"));
                personalizado.setCategoria(rs.getString("categoria"));
                personalizado.setNotas(rs.getString("notas"));
                lista.add(personalizado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Personalizado> buscarPersonalizadoPorTituloId(String categoria, Integer id) {
        List<Personalizado> lista = new ArrayList<>();
        String sql = "SELECT * FROM recordatoriospersonalizados WHERE categoria = ? AND id = ?";
        try (PreparedStatement stmtquery = conectar().prepareStatement(sql)) {
            stmtquery.setString(1, categoria);
            stmtquery.setInt(2, id);
            try (ResultSet rs = stmtquery.executeQuery()) {
                while (rs.next()) {
                    Personalizado personalizado = new Personalizado();
                    personalizado.setId(rs.getInt("id"));
                    personalizado.setCategoria(rs.getString("categoria"));
                    personalizado.setNotas(rs.getString("notas"));
                    lista.add(personalizado);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void actualizarPersonalizado(Personalizado personalizado) {
        String sql = "UPDATE recordatoriospersonalizados SET categoria=?, notas=? WHERE id=?";
        try (Connection conn = conectar();
             PreparedStatement stmtquery = conn.prepareStatement(sql)) {
            stmtquery.setString(1, personalizado.getCategoria());
            stmtquery.setString(2, personalizado.getNotas());
            stmtquery.setInt(3, personalizado.getId());
            stmtquery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarPersonalizado(Integer id) {
        String sql = "DELETE FROM recordatoriospersonalizados WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement stmtquery = conn.prepareStatement(sql)) {
            stmtquery.setInt(1, id);
            stmtquery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
