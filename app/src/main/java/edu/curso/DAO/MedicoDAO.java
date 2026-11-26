package edu.curso.DAO;

import java.util.List;

import edu.curso.Entity.Medico;

public interface MedicoDAO {
    public Medico criar(Medico m);
    public boolean excluir(Medico m);
    public List<Medico> listarTodos();
    public boolean  alterar(Long id, Medico m);
}
