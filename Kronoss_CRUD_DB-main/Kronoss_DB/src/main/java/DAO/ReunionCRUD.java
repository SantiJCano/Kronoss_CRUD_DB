package DAO;

import model.Reunion;
import javax.swing.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class ReunionCRUD {
    private final String url = "jdbc:mysql://localhost:3306/kronoss_";
    private final String user = "root";
    private final String password = "";

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void agregarReunion(Reunion reunion) {
        String sql = "INSERT INTO recordatoriosreuniones (id, ubicacion, enlace_virtual, participantes, organizador) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmtquery = conn.prepareStatement(sql)) {
            stmtquery.setInt(1, reunion.getId());
            stmtquery.setString(2, reunion.getUbicacion());
            stmtquery.setString(3, reunion.getEnlaceVirtual());
            stmtquery.setString(4, reunion.getParticipantes());
            stmtquery.setString(5, reunion.getOrganizador());
            stmtquery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Reunion> listarReuniones() {
        List<Reunion> lista = new ArrayList<>();
        String sql = "SELECT * FROM recordatoriosreuniones";
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Reunion reunion = new Reunion();
                reunion.setId(rs.getInt("id"));
                reunion.setUbicacion(rs.getString("ubicacion"));
                reunion.setEnlaceVirtual(rs.getString("enlace_virtual"));
                reunion.setParticipantes(rs.getString("participantes"));
                reunion.setOrganizador(rs.getString("organizador"));
                lista.add(reunion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Reunion> buscarReunionPorTituloId(String ubicacion, Integer id) {
        List<Reunion> lista = new ArrayList<>();
        String sql = "SELECT * FROM recordatoriosreuniones WHERE ubicacion = ? AND id = ?";
        try (PreparedStatement stmtquery = conectar().prepareStatement(sql)) {
            stmtquery.setString(1, ubicacion);
            stmtquery.setInt(2, id);
            try (ResultSet rs = stmtquery.executeQuery()) {
                while (rs.next()) {
                    Reunion reunion = new Reunion();
                    reunion.setId(rs.getInt("id"));
                    reunion.setUbicacion(rs.getString("ubicacion"));
                    reunion.setEnlaceVirtual(rs.getString("enlace_virtual"));
                    reunion.setParticipantes(rs.getString("participantes"));
                    reunion.setOrganizador(rs.getString("organizador"));
                    lista.add(reunion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void actualizarReunion(Reunion reunion) {
        String sql = "UPDATE recordatoriosreuniones SET ubicacion=?, enlace_virtual=?, participantes=?, organizador=? WHERE id=?";
        try (Connection conn = conectar();
             PreparedStatement stmtquery = conn.prepareStatement(sql)) {
            stmtquery.setString(1, reunion.getUbicacion());
            stmtquery.setString(2, reunion.getEnlaceVirtual());
            stmtquery.setString(3, reunion.getParticipantes());
            stmtquery.setString(4, reunion.getOrganizador());
            stmtquery.setInt(5, reunion.getId());
            stmtquery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarReunion(Integer id) {
        String sql = "DELETE FROM recordatoriosreuniones WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement stmtquery = conn.prepareStatement(sql)) {
            stmtquery.setInt(1, id);
            stmtquery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
