package br.com.mongo.escola.model;

public class Curso {

    private String nome;

    public Curso(String nome) {
        this.nome = nome;
    }

    public Curso() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
