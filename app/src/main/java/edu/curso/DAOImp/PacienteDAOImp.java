package edu.curso.DAOImp;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import edu.curso.DAO.PacienteDAO;
import edu.curso.Entity.Paciente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class PacienteDAOImp implements PacienteDAO {
    //Separei a conexao do banco a uma classe separada, para que caso seja necessário mudar o banco de dados, não seja necessário alterar todas as classes DAO
    private Connection con = edu.curso.DAOImp.Conexao.con(); 

    public PacienteDAOImp(){}

    @Override
    public Paciente criar(Paciente p){
        try {
            String sql = "INSERT INTO pacientes (nome, email, cpf, idade, endereco, problema) VALUES (?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getEmail());
            stmt.setLong(3, p.getCpf());
            stmt.setInt(4, p.getIdade());
            stmt.setString(5, p.getEndereco());
            stmt.setString(6, p.getProblema());

        } catch (Exception e) {
             throw new RuntimeException("Erro ao salvar pacientes" + e.getMessage(), e);
        }

        return p;
    }
    


    //Aqui vai ter a chamada da procedure, mas acredito que o tratamento 
    // da query vai ser o mesmo que um 'SELECT * FROM pacientes
    @Override
    public List<Paciente> pesquisarTodos() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "CALL pesquisarTodos";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                long cpf = rs.getLong("cpf");
                int idade = rs.getInt("idade");
                String endereco = rs.getString("endereco");
                String problema = rs.getString("problema");
                lista.add(new Paciente(id, nome, email, cpf, idade, endereco, problema));
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao pesquisar pacientes" + e.getMessage(), e);
        }
        return lista;
    }

    @Override
    public void excluir(long id) {
        String sql = "DELETE FROM pacientes WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar pacientes" + e.getMessage(), e);
        }
    }

    @Override
    public void atualizar(Paciente p) {
        String sql = """
                    UPDATE pacientes 
                    SET nome = ?, email = ?, cpf = ?, idade = ?, endereco = ?, problema = ?
                    WHERE id = ?
                    """;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getEmail());
            stmt.setLong(3, p.getCpf());
            stmt.setInt(4, p.getIdade());
            stmt.setString(5, p.getEndereco());
            stmt.setString(6, p.getProblema());
            stmt.setLong(7, p.getId());

            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar pacientes" + e.getMessage(), e);
        }
    }
}
