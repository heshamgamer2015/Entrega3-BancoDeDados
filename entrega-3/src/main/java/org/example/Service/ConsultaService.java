package org.example.Service;

import org.example.Entity.Consulta;
import org.example.Repository.ConsultaRepository;

import java.sql.SQLException;
import java.util.List;

public class ConsultaService {
    private ConsultaRepository repository;

    public ConsultaService(ConsultaRepository repository) {
        this.repository = repository;
    }

    public void createConsulta(Consulta consulta) throws SQLException {
        repository.save(consulta);
    }

    public Consulta getConsultaById(int id) throws SQLException {
        return repository.findById(id);
    }

    public List<Consulta> getAllConsultas() throws SQLException {
        return repository.findAll();
    }

    public void updateConsulta(Consulta consulta) throws SQLException {
        repository.update(consulta);
    }

    public void deleteConsulta(int id) throws SQLException {
        repository.delete(id);
    }
}