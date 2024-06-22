package org.example.Repository;

import org.example.Entity.Consulta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaRepository {
    private Connection connection;

    public ConsultaRepository(Connection connection) {
        this.connection = connection;
    }

    public void save(Consulta consulta) throws SQLException {
        String sql = "INSERT INTO consultas (data, descricao, paciente_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, consulta.getData());
            stmt.setString(2, consulta.getDescricao());
            stmt.setInt(3, consulta.getPacienteId());
            stmt.executeUpdate();
        }
    }

    public Consulta findById(int id) throws SQLException {
        String sql = "SELECT * FROM consultas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(rs.getInt("id"));
                consulta.setData(rs.getDate("data"));
                consulta.setDescricao(rs.getString("descricao"));
                consulta.setPacienteId(rs.getInt("paciente_id"));
                return consulta;
            }
            return null;
        }
    }

    public List<Consulta> findAll() throws SQLException {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM consultas";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(rs.getInt("id"));
                consulta.setData(rs.getDate("data"));
                consulta.setDescricao(rs.getString("descricao"));
                consulta.setPacienteId(rs.getInt("paciente_id"));
                consultas.add(consulta);
            }
        }
        return consultas;
    }

    public void update(Consulta consulta) throws SQLException {
        String sql = "UPDATE consultas SET data = ?, descricao = ?, paciente_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, consulta.getData());
            stmt.setString(2, consulta.getDescricao());
            stmt.setInt(3, consulta.getPacienteId());
            stmt.setInt(4, consulta.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM consultas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

