package DAO;

import model.Medicamento;
import javax.swing.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class MedicamentoCRUD {
    private final String url = "jdbc:mysql://localhost:3306/kronoss_";
    private final String user = "root";
    private final String password = "";

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void agregarMedicamento(Medicamento medicamento) {
        String sql = "INSERT INTO recordatoriosmedicamentos (id, medicamento, dosis, frecuencia, duracion_dias) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmtquery = conn.prepareStatement(sql)) {
            stmtquery.setInt(1, medicamento.getId());
            stmtquery.setString(2, medicamento.getMedicamento());
            stmtquery.setString(3, medicamento.getDosis());
            stmtquery.setString(4, medicamento.getFrecuencia());
            stmtquery.setInt(5, medicamento.getDuracionDias());
            stmtquery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Medicamento> listarMedicamentos() {
        List<Medicamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM recordatoriosmedicamentos";
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Medicamento medicamento = new Medicamento();
                medicamento.setId(rs.getInt("id"));
                medicamento.setMedicamento(rs.getString("medicamento"));
                medicamento.setDosis(rs.getString("dosis"));
                medicamento.setFrecuencia(rs.getString("frecuencia"));
                medicamento.setDuracionDias(rs.getInt("duracion_dias"));
                lista.add(medicamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Medicamento> buscarMedicamentoPorTituloId(String medicamento, Integer id) {
        List<Medicamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM recordatoriosmedicamentos WHERE medicamento = ? AND id = ?";
        try (PreparedStatement stmtquery = conectar().prepareStatement(sql)) {
            stmtquery.setString(1, medicamento);
            stmtquery.setInt(2, id);
            try (ResultSet rs = stmtquery.executeQuery()) {
                while (rs.next()) {
                    Medicamento m = new Medicamento();
                    m.setId(rs.getInt("id"));
                    m.setMedicamento(rs.getString("medicamento"));
                    m.setDosis(rs.getString("dosis"));
                    m.setFrecuencia(rs.getString("frecuencia"));
                    m.setDuracionDias(rs.getInt("duracion_dias"));
                    lista.add(m);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void actualizarMedicamento(Medicamento medicamento) {
        String sql = "UPDATE recordatoriosmedicamentos SET medicamento=?, dosis=?, frecuencia=?, duracion_dias=? WHERE id=?";
        try (Connection conn = conectar();
             PreparedStatement stmtquery = conn.prepareStatement(sql)) {
            stmtquery.setString(1, medicamento.getMedicamento());
            stmtquery.setString(2, medicamento.getDosis());
            stmtquery.setString(3, medicamento.getFrecuencia());
            stmtquery.setInt(4, medicamento.getDuracionDias());
            stmtquery.setInt(5, medicamento.getId());
            stmtquery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarMedicamento(Integer id) {
        String sql = "DELETE FROM recordatoriosmedicamentos WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement stmtquery = conn.prepareStatement(sql)) {
            stmtquery.setInt(1, id);
            stmtquery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
