package DAO;

import model.Tarea;
import javax.swing.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class TareaCRUD {
    private final String url = "jdbc:mysql://localhost:3306/kronoss_";
    private final String user = "root";
    private final String password = "";

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void agregarTarea(Tarea tarea) {
        String sql = "INSERT INTO recordatoriostareas (id, prioridad) VALUES (?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmtquery = conn.prepareStatement(sql)) {
            stmtquery.setInt(1, tarea.getId());
            stmtquery.setInt(2, tarea.getPrioridad());
            stmtquery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Tarea> listarTareas() {
        List<Tarea> lista = new ArrayList<>();
        String sql = "SELECT * FROM recordatoriostareas";
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Tarea tarea = new Tarea();
                tarea.setId(rs.getInt("id"));
                tarea.setPrioridad(rs.getInt("prioridad"));
                lista.add(tarea);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Tarea> buscarTareaPorTituloId(Integer prioridad, Integer id) {
        List<Tarea> lista = new ArrayList<>();
        String sql = "SELECT * FROM recordatoriostareas WHERE prioridad = ? AND id = ?";
        try (PreparedStatement stmtquery = conectar().prepareStatement(sql)) {
            stmtquery.setInt(1, prioridad);
            stmtquery.setInt(2, id);
            try (ResultSet rs = stmtquery.executeQuery()) {
                while (rs.next()) {
                    Tarea tarea = new Tarea();
                    tarea.setId(rs.getInt("id"));
                    tarea.setPrioridad(rs.getInt("prioridad"));
                    lista.add(tarea);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void actualizarTarea(Tarea tarea) {
        String sql = "UPDATE recordatoriostareas SET prioridad=? WHERE id=?";
        try (Connection conn = conectar();
             PreparedStatement stmtquery = conn.prepareStatement(sql)) {
            stmtquery.setInt(1, tarea.getPrioridad());
            stmtquery.setInt(2, tarea.getId());
            stmtquery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarTarea(Integer id) {
        String sql = "DELETE FROM recordatoriostareas WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement stmtquery = conn.prepareStatement(sql)) {
            stmtquery.setInt(1, id);
            stmtquery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
