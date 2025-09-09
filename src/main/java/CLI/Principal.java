package CLI;
//@author Lucas Moreira

import modelo.Tarefa;
import servico.TarefaServico;

import java.util.List;
import java.util.Scanner;

public class Principal
{

    private static TarefaServico servico = new TarefaServico();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            // Validação de entrada para garantir que seja um número
            while (!scanner.hasNextInt()) {
                System.out.println("Opção inválida. Por favor, digite um número.");
                scanner.next(); // Limpa o buffer
                System.out.print("Escolha uma opção: ");
            }
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    criarTarefa();
                    break;
                case 2:
                    listarTarefas();
                    break;
                case 3:
                    atualizarTarefa();
                    break;
                case 4:
                    removerTarefa();
                    break;
                case 5:
                    marcarComoConcluida();
                    break;
                case 0:
                    System.out.println("Saindo do sistema... Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
            System.out.println(); // Linha em branco para espaçamento
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("========= To-Do List ==========");
        System.out.println("1. Criar Tarefa");
        System.out.println("2. Listar Todas as Tarefas");
        System.out.println("3. Atualizar Tarefa");
        System.out.println("4. Remover Tarefa");
        System.out.println("5. Marcar Tarefa como Concluída");
        System.out.println("0. Sair");
        System.out.println("===============================");
        System.out.print("Escolha uma opção: ");
    }

    private static void criarTarefa() {
        System.out.println("\n--- Criar Nova Tarefa ---");
        System.out.print("Digite o título: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite a descrição: ");
        String descricao = scanner.nextLine();

        Tarefa novaTarefa = servico.criarTarefa(titulo, descricao);
        System.out.println("Tarefa criada com sucesso! ID: " + novaTarefa.getId());
    }

    private static void listarTarefas() {
        System.out.println("\n--- Lista de Tarefas ---");
        List<Tarefa> tarefas = servico.listarTarefas();
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
        } else {
            for (Tarefa tarefa : tarefas) {
                System.out.println(tarefa);
            }
        }
    }

    private static void atualizarTarefa() {
        System.out.println("\n--- Atualizar Tarefa ---");
        System.out.print("Digite o ID da tarefa que deseja atualizar: ");
        long id = scanner.nextLong();
        scanner.nextLine(); // Limpa o buffer

        System.out.print("Digite o novo título: ");
        String novoTitulo = scanner.nextLine();
        System.out.print("Digite a nova descrição: ");
        String novaDescricao = scanner.nextLine();

        if (servico.atualizarTarefa(id, novoTitulo, novaDescricao)) {
            System.out.println("Tarefa atualizada com sucesso!");
        } else {
            System.out.println("Erro: Tarefa com ID " + id + " não encontrada.");
        }
    }

    private static void removerTarefa() {
        System.out.println("\n--- Remover Tarefa ---");
        System.out.print("Digite o ID da tarefa que deseja remover: ");
        long id = scanner.nextLong();
        scanner.nextLine(); // Limpa o buffer

        if (servico.removerTarefa(id)) {
            System.out.println("Tarefa removida com sucesso!");
        } else {
            System.out.println("Erro: Tarefa com ID " + id + " não encontrada.");
        }
    }
    
    private static void marcarComoConcluida() {
        System.out.println("\n--- Marcar Tarefa como Concluída ---");
        System.out.print("Digite o ID da tarefa que deseja marcar como concluída: ");
        long id = scanner.nextLong();
        scanner.nextLine(); // Limpa o buffer

        if (servico.marcarComoConcluida(id)) {
            System.out.println("Tarefa marcada como concluída com sucesso!");
        } else {
            System.out.println("Erro: Tarefa com ID " + id + " não encontrada ou já concluída.");
        }
    }
}
