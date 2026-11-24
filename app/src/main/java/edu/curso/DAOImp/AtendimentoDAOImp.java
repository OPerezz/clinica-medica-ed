package edu.curso.DAOImp;

import edu.curso.DAO.AtendimentoDAO;
import edu.curso.Entity.Atendimento;
import java.util.List;

public class AtendimentoDAOImp implements AtendimentoDAO {

    @Override
    public void atualizar(Atendimento atendimento) {
    }

    @Override
    public Atendimento criar(Atendimento atendimento) {
        return atendimento;
    }

    @Override
    public List<Atendimento> pesquisarTodos() {
        List<Atendimento> atendimentos = new java.util.ArrayList<>();
        return atendimentos;
    }

    @Override
    public void excluir(long id) {
    }
    
}
