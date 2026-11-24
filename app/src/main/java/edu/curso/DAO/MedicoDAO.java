package edu.curso.DAO;

import java.util.List;

import edu.curso.Entity.Medico;

public interface MedicoDAO {
    Medico criar( Medico m);
    List<Medico> pesquisarTodos();
    void excluir(long id);
    void atualizar(Medico m);
}
