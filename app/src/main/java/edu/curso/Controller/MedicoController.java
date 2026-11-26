package edu.curso.Controller;


import edu.curso.DAO.MedicoDAO;
import edu.curso.DAO.MedicoDAOImp;
import edu.curso.Entity.Medico;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MedicoController {
    MedicoDAO dao = new MedicoDAOImp();

    private ObservableList<Medico> list = FXCollections.observableArrayList();

    private LongProperty id = new SimpleLongProperty();
    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty email = new SimpleStringProperty("");
    private LongProperty cpf = new SimpleLongProperty();
    private IntegerProperty idade = new SimpleIntegerProperty();
    private StringProperty crm = new SimpleStringProperty();
    private StringProperty telefone = new SimpleStringProperty();

    public MedicoController(){
        listarTodos();
    }

    public boolean criar(){
        if(nome.get() == null || nome.get().isBlank() ||
            email.get() == null || email.get().isBlank() ||
            cpf.get() <= 0 ||
            idade.get() <= 0 ||
            crm.get() == null || crm.get().isBlank()){

            System.out.println("Preencha todos os campos corretamente");
            return false;
        }
        
        Medico m = new Medico();

        m.setNome(nome.get());
        m.setEmail(email.get());
        m.setCpf(cpf.get());
        m.setIdade(idade.get());
        m.setCrm(crm.get());
        m.setTelefone(telefone.get());

        if(dao.criar(m) == null){
            return false;
        }

        limparDados();
        listarTodos();
        return true;
    }

    public void limparDados(){
        id.set(0);
        nome.set("");
        email.set("");
        cpf.set(0);
        idade.set(0);
        crm.set("");
        telefone.set("");

    }

    public void listarTodos(){
        list.clear();
        list.setAll(dao.listarTodos());
    }

    public Medico procurar(){
        listarTodos();

        for(Medico m: list){
            if(m.getId() == id.get()){
                nome.set(m.getNome());
                email.set(m.getEmail());
                cpf.set(m.getCpf());
                idade.set(m.getIdade());
                crm.set(m.getCrm());
                telefone.set(m.getTelefone());

                return m;
            }
        }

        return null;
    }

    public Medico procurar(Long id){
        listarTodos();

        for(Medico m: list){
            if(m.getId() == id){
                nome.set(m.getNome());
                email.set(m.getEmail());
                cpf.set(m.getCpf());
                idade.set(m.getIdade());
                crm.set(m.getCrm());
                telefone.set(m.getTelefone());

                return m;
            }
        }

        return null;
    }


    public boolean alterar(){
        Medico novo = new Medico();
        
        novo.setNome(nome.get());
        novo.setEmail(email.get());
        novo.setCpf(cpf.get());
        novo.setIdade(idade.get());
        novo.setCrm(crm.get());
        novo.setTelefone(telefone.get());

        if(!dao.alterar(id.get(), novo)){
            return false;
        }

        

        listarTodos();
        limparDados();
        return true;
    }
    

    public boolean excluir(){
        for(Medico m : list){
            if(id.get() == m.getId()){

                if(!dao.excluir(m)){
                    return false;
                }

                listarTodos();
                limparDados();

                return true;
            }
        }

        return false;
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

    public StringProperty crmProperty(){
        return crm;
    }

    public StringProperty telefoneProperty(){
        return telefone;
    }

    public ObservableList<Medico> getList(){
        return list;
    }
}