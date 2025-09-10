package servico;

import modelo.Tarefa;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TarefaServico {

    private List<Tarefa> bancoDeDados = new ArrayList<>();
    private static long proximoId = 1;

    // Método criarTarefa atualizado para aceitar o novo parâmetro de prioridade
    public Tarefa criarTarefa(String titulo, String descricao, Tarefa.Prioridade prioridade) {
        Tarefa novaTarefa = new Tarefa(titulo, descricao, prioridade); // Passa a prioridade para o construtor
        novaTarefa.setId(proximoId++);
        bancoDeDados.add(novaTarefa);
        return novaTarefa;
    }

    // O restante da classe (listar, atualizar, remover, etc.) não precisa de alterações.
    public List<Tarefa> listarTarefas() {
        return new ArrayList<>(bancoDeDados);
    }
    
    private Optional<Tarefa> buscarTarefaPorId(long id) {
        return bancoDeDados.stream()
                           .filter(tarefa -> tarefa.getId() == id)
                           .findFirst();
    }
    
    public boolean atualizarTarefa(long id, String novoTitulo, String novaDescricao) {
        Optional<Tarefa> tarefaOptional = buscarTarefaPorId(id);
        if (tarefaOptional.isPresent()) {
            Tarefa tarefaEncontrada = tarefaOptional.get();
            tarefaEncontrada.setTitulo(novoTitulo);
            tarefaEncontrada.setDescricao(novaDescricao);
            return true;
        }
        return false;
    }
    
    public boolean removerTarefa(long id) {
        return bancoDeDados.removeIf(tarefa -> tarefa.getId() == id);
    }
    
    public boolean marcarComoConcluida(long id) {
        Optional<Tarefa> tarefaOptional = buscarTarefaPorId(id);
        if (tarefaOptional.isPresent()) {
            Tarefa tarefaEncontrada = tarefaOptional.get();
            if (!tarefaEncontrada.isCompleta()) {
                tarefaEncontrada.setCompleta(true);
                return true;
            }
        }
        return false;
    }
}