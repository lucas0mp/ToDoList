package modelo;
//@author Lucas Moreira

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tarefa {

    private long id;
    private String titulo;
    private String descricao;
    private boolean completa;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataConclusao;

    // Construtor
    public Tarefa(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.completa = false; // Tarefa sempre começa como não concluída
        this.dataCriacao = LocalDateTime.now(); // Data e hora atuais
        this.dataConclusao = null; // Ainda não foi concluída
    }

    // Getters e Setters
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
        if (completa) {
            this.dataConclusao = LocalDateTime.now();
        } else {
            this.dataConclusao = null;
        }
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

}
