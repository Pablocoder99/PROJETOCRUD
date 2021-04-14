package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {
    //Driver
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    //Nome de usuário
    private static final String USERNAME = "root";
    //Senha do banco
    private static final String PASSWORD = "Pipocaassada@123";
    //Caminho que o driver tem que fazer
    private static final String URL = "jdbc:mysql://localhost:3306/projeto";
    
    public static Connection CriarConexao() throws ClassNotFoundException{   
        Class.forName(DRIVER);
        try{
            Connection conexao = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Conectado com sucesso");
            return conexao;
        }catch(SQLException ex){
            throw new RuntimeException("Erro ao criar conexão: "+ex);
        }
    }
    
    public static void Desconectar(Connection conexao){
        try{
            if(conexao != null){
                conexao.close();
            }
            System.out.println("Conexões fechadas com sucesso");
        }catch(SQLException ex){
            throw new RuntimeException("Erro ao desconectar do banco: "+ ex);
        }
    }
    
    public static void Desconectar(Connection conexao, PreparedStatement pstm){
        try{
            if(conexao != null){
                conexao.close();
            }
            
            if(pstm != null){
                pstm.close();
            }
            
            System.out.println("Conexões fechadas com sucesso");
        }catch(SQLException ex){
            throw new RuntimeException("Erro ao desconectar do banco: "+ ex);
        }
    }
    
    public static void Desconectar(Connection conexao, ResultSet rst){
        try{
            if(conexao != null){
                conexao.close();
            }
            
            if(rst != null){
                rst.close();
            }
            
            System.out.println("Conexões fechadas com sucesso");
        }catch(SQLException ex){
            throw new RuntimeException("Erro ao desconectar do banco: "+ ex);
        }
    }
    
    public static void Desconectar(Connection conexao, PreparedStatement pstm, ResultSet rst){
        try{
            if(conexao != null){
                conexao.close();
            }
            
            if(rst != null){
                rst.close();
            }
            
            if(pstm != null){
                pstm.close();
            }
            System.out.println("Conexões fechadas com sucesso");
        }catch(SQLException ex){
            throw new RuntimeException("Erro ao desconectar do banco: "+ ex);
        }
    }
}
