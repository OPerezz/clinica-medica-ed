package edu.curso.DAOImp;
import edu.curso.DAO.MedicoDAO;
import edu.curso.Entity.Medico;
import java.util.List;

public class MedicoDAOImp implements MedicoDAO {

	@Override
	public void atualizar(Medico medico) {
	}

	@Override
	public Medico criar(Medico medico) {
		return medico;
	}

	@Override
	public List<Medico> pesquisarTodos() {
        List<Medico> medicos = new java.util.ArrayList<>();


        return medicos;
	}

	@Override
	public void excluir(long id) {
	}

}
