package DAO;

import model.Evento;
import javax.swing.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class EventoCRUD {
    private final String url = "jdbc:mysql://localhost:3306/kronoss_";
    private final String user = "root";
    private final String password = "";

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void agregarEvento(Evento evento) {
        String sql = "INSERT INTO recordatorioseventos (id, ubicacion) VALUES (?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmtquery = conn.prepareStatement(sql)) {
            stmtquery.setInt(1, evento.getId());
            stmtquery.setString(2, evento.getUbicacion());
            stmtquery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Evento> listarEventos() {
        List<Evento> lista = new ArrayList<>();
        String sql = "SELECT * FROM recordatorioseventos";
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Evento evento = new Evento();
                evento.setId(rs.getInt("id"));
                evento.setUbicacion(rs.getString("ubicacion"));
                lista.add(evento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Evento> buscarEventoPorTituloId(String ubicacion, Integer id) {
        List<Evento> lista = new ArrayList<>();
        String sql = "SELECT * FROM recordatorioseventos WHERE ubicacion = ? AND id = ?";
        try (PreparedStatement stmtquery = conectar().prepareStatement(sql)) {
            stmtquery.setString(1, ubicacion);
            stmtquery.setInt(2, id);
            try (ResultSet rs = stmtquery.executeQuery()) {
                while (rs.next()) {
                    Evento evento = new Evento();
                    evento.setId(rs.getInt("id"));
                    evento.setUbicacion(rs.getString("ubicacion"));
                    lista.add(evento);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void actualizarEvento(Evento evento) {
        String sql = "UPDATE recordatorioseventos SET ubicacion=? WHERE id=?";
        try (Connection conn = conectar();
             PreparedStatement stmtquery = conn.prepareStatement(sql)) {
            stmtquery.setString(1, evento.getUbicacion());
            stmtquery.setInt(2, evento.getId());
            stmtquery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarEvento(Integer id) {
        String sql = "DELETE FROM recordatorioseventos WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement stmtquery = conn.prepareStatement(sql)) {
            stmtquery.setInt(1, id);
            stmtquery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}