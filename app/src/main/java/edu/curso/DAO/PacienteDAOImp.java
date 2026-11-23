package edu.curso.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PacienteDAOImp implements PacienteDAO {
    private Connection con;

    public PacienteDAOImp(){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            con  = DriverManager.getConnection("jdbc:mariadb://localhost/clinica", "root", "admin1234");
        } catch (ClassNotFoundException e) {
                System.out.println("Classe Maria DB não encontrada");
        } catch (SQLException e) { 
                System.out.println("Erro ao conectar no banco de dados");
            }
    }

    @Override
    public Paciente criar(Paciente p){
        try {
            String sql = "INSERT INTO pacientes (nome, email, cpf, idade, endereco, problema) VALUES (?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getEmail());
            stmt.setLong(3, p.getCpf());
            stmt.setInt(4, p.getIdade());
            stmt.setString(5, p.setEndereco());
            stmt.setString(6, p.getProblema());

        } catch (Exception e) {
            System.out.println("Erro ao inserir no banco de dados");
        }

        return p;
    }
}
