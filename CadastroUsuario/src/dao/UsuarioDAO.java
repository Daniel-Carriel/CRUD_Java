
package dao;

import factory.ConnectionFactory;
import model.Usuario;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    private ConnectionFactory conexao;
    private Connection conn;
    private ResultSet rs;
    private ArrayList<Usuario> lista = new ArrayList<>();
    
    public UsuarioDAO(){
        this.conexao = new ConnectionFactory();
        this.conn = this.conexao.getConnection();
    }
    
    public void salvarUsuario(Usuario usuario){
        
        String sql = "INSERT INTO usuario (nome, cpf, email, telefone)VALUES" + "(?, ?, ?, ?)";
        try {
            PreparedStatement st = this.conn.prepareStatement(sql);
            
            st.setString(1, usuario.getNome());
            st.setString(2, usuario.getCpf());
            st.setString(3, usuario.getEmail());
            st.setString(4, usuario.getTelefone());
            st.execute();
            
            JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o usuário: "+ e.getMessage());
        }
    }
    
    public ArrayList<Usuario> pesquisarUsuario(){
        String sql = "SELECT * FROM usuario";
        this.conn = this.conexao.getConnection();
        try {
            PreparedStatement st = this.conn.prepareStatement(sql);
            rs = st.executeQuery();
            
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                
                lista.add(usuario);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar o usuário: "+ e.getMessage());
        }
        
        return lista;
    }
    
    public void alterarDados(Usuario usuario){
        String sql = "UPDATE usuario SET nome = ?, cpf = ?, email = ?, telefone = ? WHERE id = ?";
        
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, usuario.getNome());
            st.setString(2, usuario.getCpf());
            st.setString(3, usuario.getEmail());
            st.setString(4, usuario.getTelefone());
            st.setInt(5, usuario.getId());
            
            st.execute();
            JOptionPane.showMessageDialog(null, "Dados alterados!");
            st.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar os dados: "+ e.getMessage());
        }
    }
    
    public void excluirDados(Usuario usuario){
        String sql = "DELETE FROM usuario WHERE id = ?";
        
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, usuario.getId());
            st.execute();
            JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir os dados: "+ e.getMessage());
        }
    }
}
