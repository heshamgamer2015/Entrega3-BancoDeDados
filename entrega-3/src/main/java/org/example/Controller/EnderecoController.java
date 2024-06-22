package org.example.Controller;

import org.example.Entity.Endereco;
import org.example.Service.EnderecoService;
import org.example.Service.ViaCepClient;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import java.io.IOException;

public class EnderecoController {
    private EnderecoService service;
    private ViaCepClient viaCepClient;
    private Scanner scanner;

    public EnderecoController(EnderecoService service) {
        this.service = service;
        this.viaCepClient = new ViaCepClient();
        this.scanner = new Scanner(System.in);
    }

    public void criarEndereco() {
        System.out.println("Digite o CEP do endereço:");
        String cep = scanner.nextLine();
        System.out.println("Digite o número da residência:");
        String numero = scanner.nextLine();
        System.out.println("Digite o ID do paciente:");
        int pacienteId = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        try {
            Endereco endereco = viaCepClient.buscarEnderecoPorCep(cep);
            endereco.setNumero(numero);
            endereco.setPacienteId(pacienteId);
            service.createEndereco(endereco);
            System.out.println("Endereço criado com sucesso!");
        } catch (IOException | SQLException e) {
            System.out.println("Erro ao criar o endereço: " + e.getMessage());
        }
    }

    public void atualizarEndereco() {
        System.out.println("Digite o ID do endereço:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        try {
            Endereco endereco = service.getEnderecoById(id);
            if (endereco != null) {
                System.out.println("Digite o novo CEP do endereço:");
                String cep = scanner.nextLine();
                System.out.println("Digite o novo número da residência:");
                String numero = scanner.nextLine();
                System.out.println("Digite o novo ID do paciente:");
                int pacienteId = scanner.nextInt();
                scanner.nextLine(); // Consumir nova linha

                Endereco novoEndereco = viaCepClient.buscarEnderecoPorCep(cep);
                novoEndereco.setId(id);
                novoEndereco.setNumero(numero);
                novoEndereco.setPacienteId(pacienteId);
                service.updateEndereco(novoEndereco);
                System.out.println("Endereço atualizado com sucesso!");
            } else {
                System.out.println("Endereço não encontrado.");
            }
        } catch (IOException | SQLException e) {
            System.out.println("Erro ao atualizar o endereço: " + e.getMessage());
        }
    }

    public void listarEnderecos() {
        try {
            List<Endereco> enderecos = service.getAllEnderecos();
            for (Endereco endereco : enderecos) {
                System.out.println(endereco);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar os endereços: " + e.getMessage());
        }
    }

    public void deletarEndereco() {
        System.out.println("Digite o ID do endereço:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha
        try {
            service.deleteEndereco(id);
            System.out.println("Endereço deletado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar o endereço: " + e.getMessage());
        }
    }

    public void exibirEnderecoPorId() {
        System.out.println("Digite o ID do endereço:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha
        try {
            Endereco endereco = service.getEnderecoById(id);
            if (endereco != null) {
                System.out.println(endereco);
            } else {
                System.out.println("Endereço não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao exibir o endereço: " + e.getMessage());
        }
    }
}