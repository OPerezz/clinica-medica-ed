package edu.curso.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import edu.curso.Entity.Atendimento;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class AtendimentoController {
    private Scanner ler = new Scanner(System.in);
    private PacienteController pc;
    private MedicoController mc;
    private long count = 0;
    private ArrayList<Atendimento> list = new ArrayList<>();
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private LongProperty id = new SimpleLongProperty(0);
    private StringProperty data = new SimpleStringProperty("");
    private LongProperty medicoId = new SimpleLongProperty(0);
    private LongProperty pacienteId = new SimpleLongProperty(0);

    public AtendimentoController(){}

    public AtendimentoController(PacienteController pc, MedicoController mc){
        this.pc = pc;
        this.mc = mc;
    }

    public void agendar() {
        Atendimento a = new Atendimento();
        a.setId(count);

        preencherDados(a);

        list.add(a);

        for(Atendimento atendimento :  list){
            System.out.println(a);
        }

        count++;
    }

    public void preencherDados(Atendimento a) {
        a.setData(LocalDateTime.parse(data.get(), formato));

        

        System.out.println("Insira o id do Medico");
        a.setM(mc.procurar(pacienteId.get()));
    }

    public void listar() {
        for (Atendimento a : list) {
            System.out.println("============================");
            System.out.println(a);
            System.out.println("============================");
        }
    }

    public Atendimento procurar(long id) {
        for (Atendimento a : list) {
            if (id == a.getId()) {
                return a;
            }
        }

        return null;
    }

    public void mostrar() {
        System.out.println("Insira o id do agendamento");
        Atendimento a = procurar(ler.nextLong());

        System.out.println("============================");
        System.out.println(
                a == null ? "Atendimento não encontrado" : a);
        System.out.println("============================");
    }

    public void deletar() {
        System.out.println("Digite o numero do atedimento que deseja deletar");
        Atendimento a = procurar(ler.nextLong());

        if (a == null) {
            System.out.println("Atendimento não Encontrado");
            return;
        }

        if (confirmar()) {
            list.remove(a);
        } else {
            System.out.println("Operação cancelada");
        }
    }

    public void alterar() {
        System.out.println("Digite o numero do atedimento que deseja Alterar");
        Atendimento a = procurar(ler.nextLong());

        if (a == null) {
            System.out.println("Atendimento não Encontrado");
            return;
        }

        if (confirmar()) {
            preencherDados(a);

            System.out.println("Cadastro Alterado");
        } else {
            System.out.println("Operação Cancelada");
        }

    }

    public Boolean confirmar() {
        System.out.println("Deseja Realmente realizar essa Operação ? Digite 'SIM' para confirmar");
        return ler.nextLine().equalsIgnoreCase("SIM");
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

    public LongProperty pacienteIdProperty(){
        return pacienteId;
    }
}
