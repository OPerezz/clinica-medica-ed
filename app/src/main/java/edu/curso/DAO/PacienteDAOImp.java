package edu.curso.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.curso.Entity.Paciente;

public class PacienteDAOImp implements PacienteDAO {
    private Connection con;

    public PacienteDAOImp(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinica", "root", "admin1234");
        } catch (ClassNotFoundException e) {
                System.out.println("Classe MySQL não encontrada");
        } catch (SQLException e) { 
                System.out.println("Erro ao conectar no banco de dados");
            }
    }

    @Override
    public Paciente criar(Paciente p){
        try {
            String sql = "INSERT INTO pacientes (nome, email, cpf, idade, endereco, problema) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getEmail());
            stmt.setLong(3, p.getCpf());
            stmt.setInt(4, p.getIdade());
            stmt.setString(5, p.getEndereco());
            stmt.setString(6, p.getProblema());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                p.setId(rs.getLong(1));
            }
            

        } catch (Exception e) {
            System.out.println("Erro ao inserir no banco de dados");
            e.printStackTrace();
        }

        return p;
    }

    @Override
    public void excluir( Paciente p){
        try {
            String sql = "DELETE FROM pacientes WHERE id=?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, p.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao Deletar: Verifiue o Track");
            e.printStackTrace();
        }
    }

    @Override
    public List<Paciente> pesquisarTodos(){
        List<Paciente> list = new ArrayList<>();

        try{
            String sql = "SELECT * FROM pacientes";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Paciente p = new Paciente();

                p.setId(rs.getLong("id"));
                p.setNome(rs.getString("nome"));
                p.setEmail(rs.getString("email"));
                p.setCpf(rs.getLong("cpf"));
                p.setIdade(rs.getInt("idade"));
                p.setEndereco(rs.getString("endereco"));
                p.setProblema(rs.getString("problema"));

                list.add(p);
            }
        }catch(Exception e){
            System.out.println("Erro ao Listar Todos");
        }

        return list;
    }


}
