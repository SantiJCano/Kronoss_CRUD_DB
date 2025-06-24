package DAO;

import model.ActividadFisica;
import javax.swing.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class ActividadFisicaCRUD {
    // Realizar conexion base de datos en MySQL
    // credenciales
    private final String url = "jdbc:mysql://localhost:3306/kronoss_";
    private final String user = "root";
    private final String password = "";

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Método para agregar actividad física
    public void agregarActividadFisica(ActividadFisica actividadFisica) {
        String sql = "INSERT INTO recordatoriosactividadFisica (id, actividad, duracion, nivel_intensidad) VALUES (?, ?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmtquery = conn.prepareStatement(sql)) {
            stmtquery.setInt(1, actividadFisica.getId());
            stmtquery.setString(2, actividadFisica.getActividad());
            stmtquery.setInt(3, actividadFisica.getDuracion());
            stmtquery.setString(4, actividadFisica.getNivelIntensidad());
            stmtquery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar actividades físicas
    public List<ActividadFisica> listarActividadesFisicas() {
        List<ActividadFisica> lista = new ArrayList<>();
        String sql = "SELECT * FROM recordatoriosactividadFisica";
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ActividadFisica actividadFisica = new ActividadFisica();
                actividadFisica.setId(rs.getInt("id"));
                actividadFisica.setActividad(rs.getString("actividad"));
                actividadFisica.setDuracion(rs.getInt("duracion"));
                actividadFisica.setNivelIntensidad(rs.getString("nivel_intensidad"));
                lista.add(actividadFisica);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Método para buscar actividad física por título e id
    public List<ActividadFisica> buscarActividadFisicaPorTituloId(String actividad, Integer id) {
        List<ActividadFisica> lista = new ArrayList<>();
        String sql = "SELECT * FROM recordatoriosactividadFisica WHERE actividad = ? AND id = ?";
        try (PreparedStatement stmtquery = conectar().prepareStatement(sql)) {
            stmtquery.setString(1, actividad);
            stmtquery.setInt(2, id);
            try (ResultSet rs = stmtquery.executeQuery()) {
                while (rs.next()) {
                    ActividadFisica actividadFisica = new ActividadFisica();
                    actividadFisica.setId(rs.getInt("id"));
                    actividadFisica.setActividad(rs.getString("actividad"));
                    actividadFisica.setDuracion(rs.getInt("duracion"));
                    actividadFisica.setNivelIntensidad(rs.getString("nivel_intensidad"));
                    lista.add(actividadFisica);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Método para actualizar actividad física
    public void actualizarActividadFisica(ActividadFisica actividadFisica) {
        String sql = "UPDATE recordatoriosactividadFisica SET actividad=?, duracion=?, nivel_intensidad=? WHERE id=?";
        try (Connection conn = conectar();
             PreparedStatement stmtquery = conn.prepareStatement(sql)) {
            stmtquery.setString(1, actividadFisica.getActividad());
            stmtquery.setInt(2, actividadFisica.getDuracion());
            stmtquery.setString(3, actividadFisica.getNivelIntensidad());
            stmtquery.setInt(4, actividadFisica.getId());
            stmtquery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar actividad física por id
    public void eliminarActividadFisica(Integer id) {
        String sql = "DELETE FROM recordatoriosactividadFisica WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement stmtquery = conn.prepareStatement(sql)) {
            stmtquery.setInt(1, id);
            stmtquery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
