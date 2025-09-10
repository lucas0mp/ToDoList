package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tarefa {

    // Enum para garantir que a prioridade só possa ter valores pré-definidos.
    public enum Prioridade {
        BAIXA,
        MEDIA,
        ALTA
    }

    private long id;
    private String titulo;
    private String descricao;
    private Prioridade prioridade; // Novo campo para a prioridade
    private boolean completa;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataConclusao;

    // Construtor atualizado para receber a prioridade
    public Tarefa(String titulo, String descricao, Prioridade prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade; // Atribui a prioridade recebida
        this.completa = false;
        this.dataCriacao = LocalDateTime.now();
        this.dataConclusao = null;
    }

    // --- Getters e Setters ---
    
    // (Getters e setters de id, titulo, descricao, etc. continuam os mesmos)
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public boolean isCompleta() { return completa; }
    public void setCompleta(boolean completa) {
        this.completa = completa;
        if (completa) {
            this.dataConclusao = LocalDateTime.now();
        } else {
            this.dataConclusao = null;
        }
    }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public LocalDateTime getDataConclusao() { return dataConclusao; }

    // Getter e Setter para o novo campo de prioridade
    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    // --- Método de Exibição ---

    // Método toString() atualizado para incluir a prioridade
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String status = completa ? "Concluída" : "Pendente";
        String dataConclusaoStr = (dataConclusao != null) ? formatter.format(dataConclusao) : "N/A";

        return "----------------------------------------\n" +
               "ID: " + id + "\n" +
               "Título: " + titulo + "\n" +
               "Descrição: " + descricao + "\n" +
               "Prioridade: " + prioridade + "\n" + // Linha adicionada
               "Status: " + status + "\n" +
               "Data de Criação: " + formatter.format(dataCriacao) + "\n" +
               "Data de Conclusão: " + dataConclusaoStr + "\n" +
               "----------------------------------------";
    }
}