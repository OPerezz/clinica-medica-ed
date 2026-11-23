package edu.curso.Controller;

import java.util.ArrayList;
import java.util.Scanner;

import edu.curso.Entity.Paciente;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PacienteController {
    private long count = 0;
    private ArrayList<Paciente> list = new ArrayList<>();
    private Scanner ler = new Scanner(System.in);

    private LongProperty id = new SimpleLongProperty(0);
    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty email = new SimpleStringProperty("");
    private StringProperty cpf= new SimpleStringProperty("");
    private IntegerProperty idade = new SimpleIntegerProperty(0);
    private StringProperty endereco = new SimpleStringProperty("");
    private StringProperty problema = new SimpleStringProperty("");


    public void criar() {
        Paciente p = new Paciente();

        System.out.println("Insira abaixo as informações solicitadas");
        p.setId(count);

        preencherDados(p);

        list.add(p);

        count = count + 1;
    }

    public void listar() {
        if (list.isEmpty()) {
            System.out.println("Lista Vazia");
        } else {
            for (Paciente p : list) {
                System.out.println(p);
            }
        }
    }

    public Paciente procurar(long id) {
        for (Paciente p : list) {
            if (id == p.getId()) {
                return p;
            }

        }

        return null;
    }

    public void mostrar() {
        System.out.println("Insira o id do Paciente");
        Paciente p = procurar(ler.nextLong());

        if (p == null) {
            System.out.println("Paciente Não Encontrado");
            return;
        }

        System.out.println(p);
    }

    public void alterar() {
        System.out.println("Insira o id do Paciente");
        Paciente p = procurar(ler.nextLong());

        if (p == null) {
            System.out.println("Paciente Não Encontrado");
            return;
        }

        if (confirmar()) {
            preencherDados(p);

            System.out.println("Cadastro Alterado");
        } else{
            System.out.println("Operação Cancelada");
        }

    }

    public Boolean confirmar() {
        System.out.println("Para continuar, Digite 'SIM' ");

        return ler.nextLine().equalsIgnoreCase("SIM");
    }

    public void preencherDados(Paciente p) {
        System.out.println("Insira abaixo as informações solicitadas");

        System.out.println("Insira o nome");
        p.setNome(ler.nextLine());

        System.out.println("Insira o email");
        p.setEmail(ler.nextLine());

        System.out.println("Insira o CPF");
        p.setCpf(ler.nextLong());

        System.out.println("Insira a idade");
        p.setIdade(ler.nextInt());

        System.out.println("Insira o Endereço");
        p.setEndereco(ler.nextLine());

        System.out.println("Insira o Problema");
        p.setProblema(ler.nextLine());
    }

    public void deletar() {
        System.out.println("Insira o id do Paciente que deseja deletar");

        Paciente p = procurar(ler.nextLong());

        if(p == null){
            System.out.println("Paciente não Encontrado");
            return;
        }

        if(confirmar()){
            list.remove(p);

            System.out.println("Paciente Deletado");
        }   else{
            System.out.println("Operação Cancelada");
        }
    }

    
    public LongProperty idProperty(){
        return id;
    }

    public StringProperty nomeProperty(){
        return nome;
    }

    public StringProperty emailProperty(){
        return email;
    }

    public StringProperty cpfProperty(){
        return cpf;
    }

    public IntegerProperty idadeProperty(){
        return idade;
    }

    public StringProperty enderecoProperty(){
        return endereco;
    }

    public StringProperty problemaProperty(){
        return problema;
    }
}
