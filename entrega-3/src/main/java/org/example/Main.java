package org.example;

import org.example.Controller.ConsultaController;
import org.example.Controller.EnderecoController;
import org.example.Controller.PacienteController;
import org.example.Repository.ConsultaRepository;
import org.example.Repository.EnderecoRepository;
import org.example.Repository.PacienteRepository;
import org.example.Service.ConsultaService;
import org.example.Service.EnderecoService;
import org.example.Service.PacienteService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/entrega_3", "postgres", "sua_senha")) {
            PacienteRepository pacienteRepository = new PacienteRepository(connection);
            PacienteService pacienteService = new PacienteService(pacienteRepository);
            PacienteController pacienteController = new PacienteController(pacienteService);

            EnderecoRepository enderecoRepository = new EnderecoRepository(connection);
            EnderecoService enderecoService = new EnderecoService(enderecoRepository);
            EnderecoController enderecoController = new EnderecoController(enderecoService);

            ConsultaRepository consultaRepository = new ConsultaRepository(connection);
            ConsultaService consultaService = new ConsultaService(consultaRepository);
            ConsultaController consultaController = new ConsultaController(consultaService);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("1. Criar Paciente");
                System.out.println("2. Exibir Paciente");
                System.out.println("3. Atualizar Paciente");
                System.out.println("4. Deletar Paciente");
                System.out.println("5. Listar Pacientes");
                System.out.println("6. Criar Endereço");
                System.out.println("7. Atualizar Endereço");
                System.out.println("8. Listar Endereços");
                System.out.println("9. Deletar Endereço");
                System.out.println("10. Exibir Endereço");
                System.out.println("11. Criar Consulta");
                System.out.println("12. Atualizar Consulta");
                System.out.println("13. Listar Consultas");
                System.out.println("14. Deletar Consulta");
                System.out.println("15. Exibir Consulta");
                System.out.println("16. Sair");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir nova linha

                switch (opcao) {
                    case 1:
                        pacienteController.criarPaciente();
                        break;
                    case 2:
                        pacienteController.exibirPaciente();
                        break;
                    case 3:
                        pacienteController.atualizarPaciente();
                        break;
                    case 4:
                        pacienteController.deletarPaciente();
                        break;
                    case 5:
                        pacienteController.listarPacientes();
                        break;
                    case 6:
                        enderecoController.criarEndereco();
                        break;
                    case 7:
                        enderecoController.atualizarEndereco();
                        break;
                    case 8:
                        enderecoController.listarEnderecos();
                        break;
                    case 9:
                        enderecoController.deletarEndereco();
                        break;
                    case 10:
                        enderecoController.exibirEnderecoPorId();
                        break;
                    case 11:
                        consultaController.criarConsulta();
                        break;
                    case 12:
                        consultaController.atualizarConsulta();
                        break;
                    case 13:
                        consultaController.listarConsultas();
                        break;
                    case 14:
                        consultaController.deletarConsulta();
                        break;
                    case 15:
                        consultaController.exibirConsultaPorId();
                        break;
                    case 16:
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}