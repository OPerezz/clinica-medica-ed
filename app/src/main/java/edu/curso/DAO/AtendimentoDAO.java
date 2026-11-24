package edu.curso.DAO;

import java.util.List;

import edu.curso.Entity.Atendimento;

public interface AtendimentoDAO {
    Atendimento criar( Atendimento a);
    List<Atendimento> pesquisarTodos();
    void excluir(long id);
    void atualizar(Atendimento a);
}
