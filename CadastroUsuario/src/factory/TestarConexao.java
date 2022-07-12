
package factory;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class TestarConexao {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory conexao =  new ConnectionFactory();
        
        conexao.getConnection();
    }
}
