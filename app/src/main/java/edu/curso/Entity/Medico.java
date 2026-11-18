package edu.curso.Entity;


public class Medico extends Pessoa {
    private String crm;

    public Medico() {
    }

    public Medico(long id, String nome, String email, long cpf, int idade, String crm) {
        super(id, nome, email, cpf, idade);

        this.crm = crm;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }


    @Override 
    public String toString(){
        return String.format("\nNome: %s \n CPF: %d \n Email: %s \n Idade: %d \n CRM: %s", getNome(), getCpf(), getEmail(), getIdade(), crm );
    }

}
