package DAO;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import model.Cliente;
import conexao.Conexao;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ClienteDAO {
    ResultSet rst = null;
    PreparedStatement pstm = null;
    Connection conexao = null;
    
    public void inserir(Cliente cliente) {
        String sql = "INSERT INTO Cliente (nome, email) VALUES (?, ?)";
        try{
            conexao = Conexao.CriarConexao();
            pstm = (PreparedStatement) conexao.prepareStatement(sql);
            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getEmail());
            
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Sucesso ao inserir dados", "Sucesso", JOptionPane.YES_OPTION);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados: "+ e, "Erro", JOptionPane.ERROR_MESSAGE);
        }finally{
            Conexao.Desconectar(conexao, pstm);
        }
    }
    
    public List<Cliente> listarTudo () throws ClassNotFoundException{
        String sql = "SELECT * FROM cliente";
        List<Cliente> Clientes = new ArrayList();
        try{
            conexao = Conexao.CriarConexao();
            pstm = (PreparedStatement) conexao.prepareStatement(sql);
            rst = pstm.executeQuery();
            //percorrer o result set e salvar as informações dentro de um model curso
            //e salva esse curso dentro da lista
            
            while(rst.next()){
                
                Cliente cliente = new Cliente();
                cliente.setId(rst.getInt("id"));
                cliente.setNome(rst.getString("nome"));
                cliente.setEmail(rst.getString("email"));
                Clientes.add(cliente);
                
            }
            return Clientes;
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao obter dados (*): "+ ex, "Erro", JOptionPane.ERROR_MESSAGE);
        }finally{
            Conexao.Desconectar(conexao, pstm, rst);
        }
        return Clientes;
    }
    
    public void editar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, email = ? WHERE id = ?";
        try {
            conexao = Conexao.CriarConexao();
            pstm = (PreparedStatement) conexao.prepareStatement(sql);
            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getEmail());
            pstm.setInt(3, cliente.getId());
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Sucesso ao editar registro", "Sucesso", JOptionPane.YES_OPTION);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar cliente: "+ e, "Erro", JOptionPane.ERROR_MESSAGE);
        }finally {
            Conexao.Desconectar(conexao, pstm);
        }
    }
    
    public void excluir(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";
        try{
            conexao = Conexao.CriarConexao();
            pstm = (PreparedStatement) conexao.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Sucesso ao excluir o registro", "Sucesso", JOptionPane.YES_OPTION);
        }catch (HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao excluir o registro "+ e, "Erro", JOptionPane.ERROR_MESSAGE);
        }finally{
            Conexao.Desconectar(conexao, pstm);
        }
    }
}
