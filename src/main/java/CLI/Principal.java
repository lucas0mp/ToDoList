package CLI;

import modelo.Tarefa;
import servico.TarefaServico;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private static TarefaServico servico = new TarefaServico();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida. Por favor, digite um número.");
                opcao = -1; // Define um valor inválido para repetir o loop
            } finally {
                 scanner.nextLine(); // Limpa o buffer do scanner em qualquer caso
            }

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
                    if(opcao != -1) System.out.println("Opção inválida! Tente novamente.");
            }
            System.out.println();
        } while (opcao != 0);

        scanner.close();
    }
    
    // O método exibirMenu() continua o mesmo.
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


    // Método criarTarefa atualizado para solicitar e processar a prioridade.
    private static void criarTarefa() {
        System.out.println("\n--- Criar Nova Tarefa ---");
        System.out.print("Digite o título: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite a descrição: ");
        String descricao = scanner.nextLine();

        Tarefa.Prioridade prioridade = null;
        int escolhaPrioridade = 0;

        // Loop para garantir que o usuário insira uma prioridade válida (1, 2 ou 3)
        while (prioridade == null) {
            System.out.print("Digite a prioridade (1-Baixa, 2-Média, 3-Alta): ");
            try {
                escolhaPrioridade = scanner.nextInt();
                switch (escolhaPrioridade) {
                    case 1:
                        prioridade = Tarefa.Prioridade.BAIXA;
                        break;
                    case 2:
                        prioridade = Tarefa.Prioridade.MEDIA;
                        break;
                    case 3:
                        prioridade = Tarefa.Prioridade.ALTA;
                        break;
                    default:
                        System.out.println("Prioridade inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número (1, 2 ou 3).");
            } finally {
                scanner.nextLine(); // Limpa o buffer para a próxima leitura
            }
        }

        // Chama o serviço com o novo parâmetro de prioridade
        Tarefa novaTarefa = servico.criarTarefa(titulo, descricao, prioridade);
        System.out.println("Tarefa criada com sucesso! ID: " + novaTarefa.getId());
    }

    // Os outros métodos (listarTarefas, atualizarTarefa, etc.) continuam os mesmos.
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
        scanner.nextLine();

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
        scanner.nextLine();

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
        scanner.nextLine();

        if (servico.marcarComoConcluida(id)) {
            System.out.println("Tarefa marcada como concluída com sucesso!");
        } else {
            System.out.println("Erro: Tarefa com ID " + id + " não encontrada ou já concluída.");
        }
    }
}