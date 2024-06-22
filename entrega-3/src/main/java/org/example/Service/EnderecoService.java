package org.example.Service;

import org.example.Entity.Endereco;
import org.example.Repository.EnderecoRepository;

import java.sql.SQLException;
import java.util.List;

public class EnderecoService {
    private EnderecoRepository repository;

    public EnderecoService(EnderecoRepository repository) {
        this.repository = repository;
    }

    public void createEndereco(Endereco endereco) throws SQLException {
        repository.save(endereco);
    }

    public Endereco getEnderecoById(int id) throws SQLException {
        return repository.findById(id);
    }

    public List<Endereco> getAllEnderecos() throws SQLException {
        return repository.findAll();
    }

    public void updateEndereco(Endereco endereco) throws SQLException {
        repository.update(endereco);
    }

    public void deleteEndereco(int id) throws SQLException {
        repository.delete(id);
    }
}
