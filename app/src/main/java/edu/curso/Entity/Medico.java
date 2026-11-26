package edu.curso.Entity;


public class Medico extends Pessoa {
    private String crm;
    private String telefone;

    public Medico() {
    }

    public Medico(long id, String nome, String email, long cpf, int idade, String crm, String telefone) {
        super(id, nome, email, cpf, idade);

        this.crm = crm;
        this.telefone = telefone;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }


    @Override 
    public String toString(){
        return String.format("\nNome: %s \n CPF: %d \n Email: %s \n Idade: %d \n CRM: %s \n Telefone: %s ", getNome(), getCpf(), getEmail(), getIdade(), crm , telefone);
    }

}
