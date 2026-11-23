package edu.curso.DAO;

import java.util.List;
import edu.curso.Entity.Paciente;
public interface PacienteDAO {
    Paciente criar( Paciente p);
    List<Paciente> pesquisarTodos();
    void excluir(long id);
    void atualizar(Paciente p);
}
