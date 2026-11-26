package edu.curso.DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.curso.Entity.Medico;

public class MedicoDAOImp implements MedicoDAO{
    private Connection con;
    

    public MedicoDAOImp(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinica", "root", "admin1234");

        } catch (ClassNotFoundException e) {
            System.out.println("Classe Não Encontrada");
        } catch (Exception e){
            System.out.println("Erro ao iniciar o Banco");
        }
    }

    @Override
    public Medico criar(Medico m){
        try{
            String sql = "INSERT INTO medicos (nome, email, cpf, idade, crm, telefone) VALUES (? , ? , ? , ?,  ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getEmail());
            stmt.setLong(3, m.getCpf());
            stmt.setInt(4, m.getIdade());
            stmt.setString(5, m.getCrm());
            stmt.setString(6, m.getTelefone());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                m.setId(rs.getLong(1));
            }

            System.out.println("Cadastrado");


        }catch (Exception e){
            System.out.println("Erro a cadastrar um novo Medico");
            e.printStackTrace();
            return null;
        }

        return m;
    }

    public boolean alterar(Long id, Medico m){
        try{
            String sql = "UPDATE medicos SET nome=?, email=?, cpf=?, idade=?, crm=?, telefone=? WHERE  id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getEmail());
            stmt.setLong(3, m.getCpf());
            stmt.setInt(4, m.getIdade());
            stmt.setString(5, m.getCrm());
            stmt.setString(6, m.getTelefone());
            stmt.setLong(7, id);

            stmt.executeUpdate();

            return true;
            

        }catch (Exception e){
            System.out.println("Erro ao alterar Medico");
            e.printStackTrace();
            
        }
        return false;
    }

    public boolean excluir(Medico m){
        try{
            String sql =  "DELETE FROM medicos where id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, m.getId());

            stmt.executeUpdate();

            return true;
        } catch (Exception e){
            System.out.println("Erro ao Deletar o Medico");
            e.printStackTrace();
        }
    
        return false;

    }

    @Override
    public List<Medico> listarTodos(){
        List<Medico> list = new ArrayList<>();

        try{    
            String sql = "SELECT * FROM medicos";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Medico m = new Medico();
                
                m.setId(rs.getLong("id"));
                m.setNome(rs.getString("nome"));
                m.setEmail(rs.getString("email"));
                m.setCpf(rs.getLong("cpf"));
                m.setIdade(rs.getInt("idade"));
                m.setCrm(rs.getString("crm"));
                m.setTelefone(rs.getString("telefone"));

                list.add(m);
            }


        }catch(Exception e){
            System.out.println("Erro ao criar a lista com o Banco");
            e.printStackTrace();
        }


        return list;
    }


    
}
