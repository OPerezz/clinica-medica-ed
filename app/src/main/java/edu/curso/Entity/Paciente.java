package edu.curso.Entity;

public class Paciente extends Pessoa {
    private String endereco;
    private String problema;

    public Paciente() {
    }

    public Paciente(long id, String nome, String email, long cpf, int idade, String endereco, String problema) {
        super(id, nome, email, cpf, idade);

        this.endereco = endereco;
        this.problema = problema;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }


    @Override 
    public String toString(){
        return String.format("\nNome: %s \n CPF: %d \n Email: %s \n Idade: %d \n Endereço: %s \n Problema ALegado: %s", getNome(), getCpf(), getEmail(), getIdade(), endereco, problema);
    }

}