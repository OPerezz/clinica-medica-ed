package edu.curso.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.curso.Entity.Atendimento;
import edu.curso.Entity.Medico;
import edu.curso.Entity.Paciente;

public class AtendimentoDAOImp implements AtendimentoDAO {
    Connection con;

    public AtendimentoDAOImp(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinica", "root" , "admin1234");
        } catch (ClassNotFoundException e) {
            System.out.println("Classe Não Encontrada");
        } catch (Exception e){
            System.out.println("Erro ao iniciar o Banco");
        }
    }
    

    @Override
    public Atendimento agendar(Atendimento a){
        try {
            String sql = "INSERT INTO atendimentos (medico_id, paciente_id, data) VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            stmt.setLong(1, a.getM().getId());
            stmt.setLong(2, a.getP().getId());
            stmt.setObject(3, a.getData());
            stmt.executeUpdate();
            
            ResultSet rs =  stmt.getGeneratedKeys();
            if(rs.next()){
                a.setId(rs.getLong(1));
            }

        } catch (Exception e) {
            System.out.println("Erro ao agendar");
            e.printStackTrace();
            return null;
        }

        return a;
    }

    @Override
    public List<Atendimento> listarTodos(){
        List<Atendimento> list =  new ArrayList<>();

        try {
        String sql = """
            SELECT 
                a.id AS atendimentos_id, a.data,
                m.id AS medico_id, m.nome AS medico_nome,
                p.id AS paciente_id, p.nome AS paciente_nome
                FROM atendimentos a
                    INNER JOIN medicos m ON a.medico_id = m.id
                    INNER JOIN pacientes p ON a.paciente_id = p.id
            """;
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Atendimento a = new Atendimento();
                Medico m =  new Medico();
                Paciente p = new Paciente();

                a.setId(rs.getLong("atendimentos_id"));
                m.setId(rs.getLong("medico_id"));
                m.setNome(rs.getString("medico_nome"));
                a.setM(m);
                p.setId(rs.getLong("paciente_id"));
                p.setNome(rs.getString("paciente_nome"));
                a.setP(p);
                a.setData(rs.getObject("data", LocalDateTime.class));

                list.add(a);
            }
            
        } catch (Exception e) {
            System.out.println("Erro ao listar todos do Banco");
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Boolean deletar(Long id){
        
        try{
            String sql =  "DELETE FROM atendimentos WHERE id = ?";
            PreparedStatement stmt =  con.prepareStatement(sql);

            stmt.setLong(1, id);
            stmt.executeUpdate();

            return true;

        }catch(Exception e){
            System.out.println("Erro ao deletar o agendamento do Banco");
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void alterar(Long id, Atendimento a){
        try {
            String sql = "UPDATE atendimentos SET medico_id=?, paciente_id=?, data=? WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, a.getM().getId());
            stmt.setLong(2, a.getP().getId());
            stmt.setObject(3, a.getData());
            stmt.setLong(4, id);
            stmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("Erro ao fazer a alteraçao do Atendimento");
            e.printStackTrace();
        }
    }
}
