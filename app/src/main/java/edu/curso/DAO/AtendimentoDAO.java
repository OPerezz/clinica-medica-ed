package edu.curso.DAO;

import java.util.List;

import edu.curso.Entity.Atendimento;

public interface AtendimentoDAO {
    public Atendimento agendar(Atendimento a);
    public List<Atendimento> listarTodos();
    public Boolean deletar(Long id);
    public void alterar(Long id, Atendimento a);
}
