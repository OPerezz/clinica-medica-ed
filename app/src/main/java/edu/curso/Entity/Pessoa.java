package edu.curso.Entity;

public class Pessoa {
    private long id;
    private String nome;
    private String email;
    private long cpf;
    private int idade;

    public Pessoa() {
    }

    public Pessoa(long id, String nome, String email, long cpf, int idade) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.idade = idade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

}
