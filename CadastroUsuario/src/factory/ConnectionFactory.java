
package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ConnectionFactory {
    
    private static final String URL_DATABASE = "jdbc:mysql://localhost:3306/cadastro";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public Connection getConnection(){
        
        try {
            Connection conn = DriverManager.getConnection(URL_DATABASE, USER, PASSWORD);
            Statement st = conn.createStatement();
            //JOptionPane.showMessageDialog(null, "Conexão estabelecida!.");
            return conn;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados.");
            return null;
        }
    }
}
