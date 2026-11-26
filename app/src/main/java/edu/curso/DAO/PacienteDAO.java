package edu.curso.DAO;

import java.util.List;

import edu.curso.Entity.Paciente;

public interface PacienteDAO {
    Paciente criar( Paciente p);
    List<Paciente> pesquisarTodos();
    boolean excluir(Paciente p);
    boolean atualizar(long id, Paciente novo);
}
