package edu.curso.DAOImp;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
public class Conexao {
    public static Connection con() {
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mariadb://localhost/clinica", "root", "admin1234");
        } catch (ClassNotFoundException e) {
                System.out.println("Classe Maria DB não encontrada");
        } catch (SQLException e) { 
                System.out.println("Erro ao conectar no banco de dados");
            }
        return null;
    }
}
