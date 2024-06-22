package org.example.Controller;

import org.example.Entity.Consulta;
import org.example.Service.ConsultaService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ConsultaController {
    private ConsultaService service;
    private Scanner scanner;

    public ConsultaController(ConsultaService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void criarConsulta() {
        System.out.println("Digite a data da consulta (AAAA-MM-DD):");
        String data = scanner.nextLine();
        System.out.println("Digite a descrição da consulta:");
        String descricao = scanner.nextLine();
        System.out.println("Digite o ID do paciente:");
        int pacienteId = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        Consulta consulta = new Consulta();
        consulta.setData(Date.valueOf(data));
        consulta.setDescricao(descricao);
        consulta.setPacienteId(pacienteId);
        try {
            service.createConsulta(consulta);
            System.out.println("Consulta criada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar consulta: " + e.getMessage());
        }
    }

    public void atualizarConsulta() {
        System.out.println("Digite o ID da consulta:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha
        try {
            Consulta consulta = service.getConsultaById(id);
            if (consulta != null) {
                System.out.println("Digite a nova data da consulta (AAAA-MM-DD):");
                String data = scanner.nextLine();
                System.out.println("Digite a nova descrição da consulta:");
                String descricao = scanner.nextLine();
                System.out.println("Digite o novo ID do paciente:");
                int pacienteId = scanner.nextInt();
                scanner.nextLine(); // Consumir nova linha

                consulta.setData(Date.valueOf(data));
                consulta.setDescricao(descricao);
                consulta.setPacienteId(pacienteId);
                service.updateConsulta(consulta);
                System.out.println("Consulta atualizada com sucesso!");
            } else {
                System.out.println("Consulta não encontrada.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar consulta: " + e.getMessage());
        }
    }

    public void listarConsultas() {
        try {
            List<Consulta> consultas = service.getAllConsultas();
            for (Consulta consulta : consultas) {
                System.out.println(consulta);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar consultas: " + e.getMessage());
        }
    }

    public void deletarConsulta() {
        System.out.println("Digite o ID da consulta:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha
        try {
            service.deleteConsulta(id);
            System.out.println("Consulta deletada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar consulta: " + e.getMessage());
        }
    }

    public void exibirConsultaPorId() {
        System.out.println("Digite o ID da consulta:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha
        try {
            Consulta consulta = service.getConsultaById(id);
            if (consulta != null) {
                System.out.println(consulta);
            } else {
                System.out.println("Consulta não encontrada.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao exibir a consulta: " + e.getMessage());
        }
    }
}