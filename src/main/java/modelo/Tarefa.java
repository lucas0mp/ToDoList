package modelo;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tarefa {

    private long id;
    private String titulo;
    private String descricao;
    private boolean completa;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataConclusao;

    // Construtor é chamado ao criar uma nova tarefa
    public Tarefa(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.completa = false; // Tarefa sempre começa como não concluída
        this.dataCriacao = LocalDateTime.now(); // Captura a data e hora atuais
        this.dataConclusao = null; // A data de conclusão inicia como nula
    }

    // --- Getters e Setters ---
    // Métodos para acessar e modificar os atributos privados da classe.
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isCompleta() {
        return completa;
    }

    public void setCompleta(boolean completa) {
        this.completa = completa;
        // Se a tarefa for marcada como completa, define a data de conclusão
        if (completa) {
            this.dataConclusao = LocalDateTime.now();
        } else {
            // Caso contrário, remove a data de conclusão
            this.dataConclusao = null;
        }
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }
    
    // --- Método de Exibição ---

    /**
     * Sobrescreve o método toString padrão do Java.
     * Este método é chamado automaticamente pelo System.out.println()
     * para converter o objeto Tarefa em uma String legível.
     */
    @Override
    public String toString() {
        // Define o formato desejado para data e hora (dia/mês/ano hora:minuto)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        // Verifica o status da tarefa para exibir um texto amigável
        String status = completa ? "Concluída" : "Pendente";
        
        // Formata a data de conclusão, ou exibe "N/A" se a tarefa estiver pendente
        String dataConclusaoStr = (dataConclusao != null) ? formatter.format(dataConclusao) : "N/A";
        
        // Monta e retorna a string formatada com todos os detalhes da tarefa
        return "----------------------------------------\n" +
               "ID: " + id + "\n" +
               "Título: " + titulo + "\n" +
               "Descrição: " + descricao + "\n" +
               "Status: " + status + "\n" +
               "Data de Criação: " + formatter.format(dataCriacao) + "\n" +
               "Data de Conclusão: " + dataConclusaoStr + "\n" +
               "----------------------------------------";
    }
}