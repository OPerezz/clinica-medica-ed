package edu.curso.Entity;

import java.time.LocalDateTime;

public class Atendimento {
    private long id;
    private LocalDateTime data;
    private Paciente p;
    private Medico m;

    public Atendimento(){}

    public Atendimento(long id, Paciente p, Medico m , LocalDateTime data){
        this.id = id;
        this.p = p;
        this.m = m;
        this.data = data;
    }

    public Long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public Paciente getP(){
        return p;
    }

    public void setP(Paciente p){
        this.p = p;
    }

    public Medico getM(){
        return m;
    }

    public void setM(Medico m){
        this.m = m;
    }

    public LocalDateTime getData(){
        return data;
    }

    public void setData(LocalDateTime data){
        this.data = data;
    }

    public Medico getMedico(){
        return m;
    }

    public Paciente getPaciente(){
        return p;
    }

    @Override
    public String toString(){
        return String.format("O Atendimento %d \n Data: %s \n Medico: %s \n Paciente: %s", id, data, m.getNome(), p.getNome());
    }
}
