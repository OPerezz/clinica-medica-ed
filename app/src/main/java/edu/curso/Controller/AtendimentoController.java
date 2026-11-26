package edu.curso.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import edu.curso.DAO.AtendimentoDAO;
import edu.curso.DAO.AtendimentoDAOImp;
import edu.curso.Entity.Atendimento;
import edu.curso.Entity.Medico;
import edu.curso.Entity.Paciente;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AtendimentoController {
    AtendimentoDAO dao = new AtendimentoDAOImp();
    
    private MedicoController mc = new MedicoController();
    private PacienteController pc = new PacienteController();

    
    private ObservableList<Atendimento> list = FXCollections.observableArrayList();
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    private LongProperty id = new SimpleLongProperty(0);
    private StringProperty data = new SimpleStringProperty("");
    private LongProperty medicoId = new SimpleLongProperty(0);
    private LongProperty pacienteId = new SimpleLongProperty(0);

    private StringProperty medicoNome = new SimpleStringProperty();
    private StringProperty pacienteNome = new SimpleStringProperty();
    private StringProperty erro = new SimpleStringProperty("");

    public AtendimentoController(){
        listarTodos();
    }

    public boolean agendar() {

    if(!validar()){
        return false;
    }

    Atendimento a = new Atendimento();

    Medico m = mc.procurar(medicoId.get());
    Paciente p = pc.procurar(pacienteId.get());

    a.setData(LocalDateTime.parse(data.get(), formato));
    a.setM(m);
    a.setP(p);

    if(dao.agendar(a) == null){
        erro.set("Erro ao cadastrar atendimento.");
        return false;
    }

    limparDados();
    listarTodos();

    return true;
    }

    public void limparDados(){
        id.set(0);
        data.set("");
        medicoId.set(0);
        pacienteId.set(0);
        medicoNome.set("");
        pacienteNome.set("");
    }

    public void listarTodos(){
        list.setAll(dao.listarTodos());
    }

    public boolean alterar(){
        Atendimento a = new Atendimento();
        Medico m = mc.procurar(medicoId.get());
        Paciente p = pc.procurar(pacienteId.get());

         if(!validar()){
            return false;
        }

        a.setM(mc.procurar(medicoId.get()));
        a.setP(pc.procurar(pacienteId.get()));
        a.setData(LocalDateTime.parse(data.get(), formato));
        
        dao.alterar(id.get(), a);
        

        listarTodos();

        return true;
    }

    public boolean procurar() {
        for(Atendimento a : list){
            
            if(id.get() == a.getId()){
                id.set(a.getId());
                medicoId.set(a.getM().getId());
                medicoNome.set(a.getM().getNome());
                pacienteId.set(a.getP().getId());
                pacienteNome.set(a.getP().getNome());
                data.set(a.getData().format(formato));
                
                return true;
            }
        }

        return false;
    }

    public void deletar() {
        
        dao.deletar(id.get());
        listarTodos();
    }

    public boolean validar(){

    erro.set("");

    System.out.println("ID Medico: " + medicoId.get());
    System.out.println("ID Paciente: " + pacienteId.get());

    if(medicoId.get() <= 0){
        erro.set("Informe o ID do médico.");
        return false;
    }

    if(mc.procurar(medicoId.get()) == null){
        erro.set("Médico não encontrado.");
        return false;
    }

    if(pacienteId.get() <= 0){
        erro.set("Informe o ID do paciente.");
        return false;
    }

    if(pc.procurar(pacienteId.get()) == null){
        erro.set("Paciente não encontrado.");
        return false;
    }

    if(data.get() == null || data.get().isBlank()){
        erro.set("Informe a data do atendimento.");
        return false;
    }

    try{
        LocalDateTime.parse(data.get(), formato);
    }catch(DateTimeParseException e){
        erro.set("Data inválida. Utilize dd/MM/yyyy HH:mm.");
        return false;
    }

    return true;
    }
    
    public LongProperty idProperty(){
        return id;
    }

    public StringProperty dataProperty(){
        return data;
    }

    public LongProperty medicoIdProperty(){
        return medicoId;
    }

    public StringProperty medicoNomeProperty(){
        return medicoNome;
    }

    public LongProperty pacienteIdProperty(){
        return pacienteId;
    }

    public StringProperty pacienteNomeProperty(){
        return pacienteNome;
    }

    public StringProperty erroProperty(){
        return erro;
    }

    public ObservableList<Atendimento> getList(){
        return list;
    }
}
