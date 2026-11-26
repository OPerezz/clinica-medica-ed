package edu.curso.Controller;

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
    private ObservableList<Paciente> list = FXCollections.observableArrayList();
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
        if(nome.get() == null || nome.get().isBlank() ||
        email.get() == null || email.get().isBlank() ||
        cpf.get() <= 0 ||
        idade.get() <= 0 ||
        endereco.get() == null || endereco.get().isBlank() ||
        problema.get() == null || problema.get().isBlank()){

            System.out.println("Preencha todos os campos corretamente");
            return false;
        }
        Paciente p = new Paciente();

        p.setNome(nome.get());
        p.setEmail(email.get());
        p.setCpf(cpf.get());
        p.setIdade(idade.get());
        p.setEndereco(endereco.get());
        p.setProblema(problema.get());

        if(dao.criar(p) == null){
            return false;
        }

        limparDados();
        pesquisarTodos();
        return true;
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

    public Paciente procurar() {
        pesquisarTodos();

        for(Paciente p: list){
            if(id.get() == p.getId()){
                id.set(p.getId());
                nome.set(p.getNome());
                email.set(p.getEmail());
                cpf.set(p.getCpf());
                idade.set(p.getIdade());
                endereco.set(p.getEndereco());
                problema.set(p.getProblema());

                return p;
            }
        }
        return null;
    }

    public Paciente procurar(Long id) {
    pesquisarTodos();

        for(Paciente p : list){
            if(id != null && id.longValue() == p.getId()){
                nome.set(p.getNome());
                email.set(p.getEmail());
                cpf.set(p.getCpf());
                idade.set(p.getIdade());
                endereco.set(p.getEndereco());
                problema.set(p.getProblema());

                return p;
            }
        }

        return null;
    }


    public boolean alterar() {
        Paciente novo = new Paciente();

        novo.setNome(nome.get());
        novo.setEmail(email.get());
        novo.setCpf(cpf.get());
        novo.setIdade(idade.get());
        novo.setEndereco(endereco.get());
        novo.setProblema(problema.get());

        if(!dao.atualizar(id.get(), novo)){
            return false;
        }

        pesquisarTodos();
        limparDados();

        return true;
    }

    public boolean deletar() {
        for(Paciente p : list){
            if(id.get() == p.getId()){
                if(!dao.excluir(p)){
                    return false;
                }

                pesquisarTodos();
                limparDados();

                return true;
                }
            }

        return false;
    }

    public void pesquisarTodos(){
        list.clear();
        list.setAll(dao.pesquisarTodos());
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
