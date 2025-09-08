package servico;
//@author Lucas Moreira

import modelo.Tarefa;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TarefaServico {

    private List<Tarefa> bancoDeDados = new ArrayList<>();
    private static long proximoId = 1; // Contador de ID estático

    //Cria uma nova tarefa e a adiciona na lista. O ID é gerado automaticamente.
    
    public Tarefa criarTarefa(String titulo, String descricao) {
        Tarefa novaTarefa = new Tarefa(titulo, descricao);
        novaTarefa.setId(proximoId++); // Define o ID e depois incrementa o contador
        bancoDeDados.add(novaTarefa);
        return novaTarefa;
    }

    //Retorna a lista de todas as tarefas cadastradas.
    
    public List<Tarefa> listarTarefas() {
        return new ArrayList<>(bancoDeDados); // Retorna uma cópia para proteger a lista original
    }

    // Busca uma tarefa pelo seu ID. Retorna um Optional para tratar casos onde a tarefa não é encontrada.
    
    private Optional<Tarefa> buscarTarefaPorId(long id) {
        return bancoDeDados.stream()
                           .filter(tarefa -> tarefa.getId() == id)
                           .findFirst();
    }

    //Atualiza o título e a descrição de uma tarefa existente. Retorna true se a atualização foi bem-sucedida, false caso contrário.
    
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

    // Remove uma tarefa da lista pelo seu ID. Retorna true se a remoção foi bem-sucedida, false caso contrário.
    
    public boolean removerTarefa(long id) {
        return bancoDeDados.removeIf(tarefa -> tarefa.getId() == id);
    }
    
    //Marca uma tarefa como concluída. Retorna true se a operação foi bem-sucedida, false caso contrário.
    
    public boolean marcarComoConcluida(long id) {
        Optional<Tarefa> tarefaOptional = buscarTarefaPorId(id);

        if (tarefaOptional.isPresent()) {
            Tarefa tarefaEncontrada = tarefaOptional.get();
            if (!tarefaEncontrada.isCompleta()) { // Evita remarcar
                tarefaEncontrada.setCompleta(true);
                return true;
            }
        }
        return false;
    }
}
