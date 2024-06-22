package org.example.Service;

import org.example.Entity.Paciente;
import org.example.Repository.PacienteRepository;

import java.sql.SQLException;
import java.util.List;

public class PacienteService {
    private PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public void createPaciente(Paciente paciente) throws SQLException {
        repository.save(paciente);
    }

    public Paciente getPacienteById(int id) throws SQLException {
        return repository.findById(id);
    }

    public List<Paciente> getAllPacientes() throws SQLException {
        return repository.findAll();
    }

    public void updatePaciente(Paciente paciente) throws SQLException {
        repository.update(paciente);
    }

    public void deletePaciente(int id) throws SQLException {
        repository.delete(id);
    }
}