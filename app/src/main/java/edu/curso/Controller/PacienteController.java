package edu.curso.Controller;

import java.util.Scanner;

import edu.curso.DAO.PacienteDAO;
import edu.curso.DAO.PacienteDAOImp;
import edu.curso.Entity.Paciente;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PacienteController {
    private long count = 0;
    private ObservableList<Paciente> list = FXCollections.observableArrayList();
    private Scanner ler = new Scanner(System.in);
    private int i = 0;
    private PacienteDAO dao = new PacienteDAOImp();

    private LongProperty id = new SimpleLongProperty();
    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty email = new SimpleStringProperty("");
    private LongProperty cpf= new SimpleLongProperty(0);
    private IntegerProperty idade = new SimpleIntegerProperty(0);
    private StringProperty endereco = new SimpleStringProperty("");
    private StringProperty problema = new SimpleStringProperty("");

    public PacienteController(){
        pesquisarTodos();
    }
    


    public Boolean criar() {
        Paciente p = new Paciente();

        p.setNome(nome.get());
        p.setEmail(email.get());
        p.setCpf(cpf.get());
        p.setIdade(idade.get());
        p.setEndereco(endereco.get());
        p.setProblema(problema.get());

        dao.criar(p);

        limparDados();
        pesquisarTodos();
        return true;
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

    public void limparDados(){
        id.set(0);
        nome.set("");
        email.set("");
        cpf.set(0);
        idade.set(0);
        endereco.set("");
        problema.set("");
    }

    public boolean procurar() {
        i = 0;
        for(Paciente p: list){
            if(id.get() == p.getId()){
                id.set(p.getId());
                nome.set(p.getNome());
                email.set(p.getEmail());
                cpf.set(p.getCpf());
                idade.set(p.getIdade());
                endereco.set(p.getEndereco());
                problema.set(p.getProblema());

                return true;
            }
            i = i + 1;
        }
        return false;
    }


    public void alterar() {
        Paciente p = list.get(i);

        if(p != null){
            Paciente novo = new Paciente();
            novo.setId(id.get());
            novo.setNome(nome.get());
            novo.setEmail(email.get());
            novo.setCpf(cpf.get());
            novo.setIdade(idade.get());
            novo.setEndereco(endereco.get());
            novo.setProblema(problema.get());
            list.set(i, novo);
        }

        limparDados();
    }

    public void deletar() {
        for(Paciente p : list){
            if(id.get() == p.getId()){
                dao.excluir(p);
            }
        }

        pesquisarTodos();
        limparDados();
    }

    public void pesquisarTodos(){
        list.clear();
        list.addAll(dao.pesquisarTodos());
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

    public LongProperty cpfProperty(){
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

    public ObservableList<Paciente> getLista() {
        return list;
    }
}
