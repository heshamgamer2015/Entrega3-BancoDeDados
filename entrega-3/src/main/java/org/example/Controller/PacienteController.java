package org.example.Controller;

import org.example.Entity.Paciente;
import org.example.Service.PacienteService;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;

public class PacienteController {
    private PacienteService service;
    private Scanner scanner;

    public PacienteController(PacienteService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void criarPaciente() {
        System.out.println("Digite o nome do paciente:");
        String nome = scanner.nextLine();
        System.out.println("Digite o CPF do paciente:");
        String cpf = scanner.nextLine();
        Paciente paciente = new Paciente();
        paciente.setNome(nome);
        paciente.setCpf(cpf);
        try {
            service.createPaciente(paciente);
            System.out.println("Paciente criado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar paciente: " + e.getMessage());
        }
    }

    public void exibirPaciente() {
        System.out.println("Digite o ID do paciente:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha
        try {
            Paciente paciente = service.getPacienteById(id);
            if (paciente != null) {
                System.out.println(paciente);
            } else {
                System.out.println("Paciente não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao exibir paciente: " + e.getMessage());
        }
    }

    public void listarPacientes() {
        try {
            List<Paciente> pacientes = service.getAllPacientes();
            for (Paciente paciente : pacientes) {
                System.out.println(paciente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar pacientes: " + e.getMessage());
        }
    }

    public void atualizarPaciente() {
        System.out.println("Digite o ID do paciente:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha
        try {
            Paciente paciente = service.getPacienteById(id);
            if (paciente != null) {
                System.out.println("Digite o novo nome do paciente:");
                String nome = scanner.nextLine();
                System.out.println("Digite o novo CPF do paciente:");
                String cpf = scanner.nextLine();
                paciente.setNome(nome);
                paciente.setCpf(cpf);
                service.updatePaciente(paciente);
                System.out.println("Paciente atualizado com sucesso!");
            } else {
                System.out.println("Paciente não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar paciente: " + e.getMessage());
        }
    }

    public void deletarPaciente() {
        System.out.println("Digite o ID do paciente:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha
        try {
            service.deletePaciente(id);
            System.out.println("Paciente deletado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar paciente: " + e.getMessage());
        }
    }
}