/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.TipoServico;
import persistencia.ConnectionFactory;


public class TipoServicoDAO {
    
    private Connection conexao;
    private PreparedStatement comando;
    DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    

    private static TipoServicoDAO instancia;
    
    public Connection conectar(String sql) throws SQLException, ClassNotFoundException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql);
        return conexao;
    }//fecha conectar    
    
    public static synchronized TipoServicoDAO getInstance(){
        if(instancia == null){
            instancia = new TipoServicoDAO();
        }
        return instancia;
    }
    public void cadastrarTipoServico(TipoServico tipoServico) throws SQLException, ClassNotFoundException{
        
        try{   
            String sql = "insert into tipoServico(nome, descricao, valor) values (?, ?, ?)";
               conectar(sql);
                    comando.setString(1,tipoServico.getNome());
                    comando.setString(2,tipoServico.getDescricao());
                    comando.setDouble(3,tipoServico.getValor());                    
                                        
                 if(comando.executeUpdate()>0){
                     System.out.println("\nCadastro realizado com sucesso!");
                 }//fecha if
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao cadastrar tipo de servico!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally           
    }//fecha cadastrarTipoServico
    
   
    public void editarTipoServico(TipoServico tipoServico) throws SQLException, ClassNotFoundException{
        try{
            String sql = "UPDATE tipoServico SET nome = ?, descricao = ?, valor = ? WHERE codTipoServico = ?";
 
            conectar(sql);
            comando.setString(1,tipoServico.getNome());
            comando.setString(2,tipoServico.getDescricao());
            comando.setDouble(3,tipoServico.getValor());             
            
            comando.setInt(4,tipoServico.getCodTipoServico());
            comando.executeUpdate();
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao editar tipo de tipo de servico!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
    }//fecha editarTipoServico
    
  
    public void deletarTipoServico(TipoServico tipoServico) throws SQLException, ClassNotFoundException{
        
        try{
                String sql = "delete from tipoServico where codTipoServico = ?";
                    conectar(sql); 
                    
                comando.setInt(1,tipoServico.getCodTipoServico());
                 if(comando.executeUpdate()>0){
                     System.out.println("\nTipo de Servico deletado com sucesso!");
                 }//fecha if
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao deletar Tipo de Servico!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally            
    }//fecha deletarTipoServico
           
    public TipoServico retornaTipoServicoPorCod(int _cod)throws SQLException, ClassNotFoundException {
        try{               
            String sql = "SELECT * FROM tipoServico WHERE codTipoServico = ?";
                conectar(sql); 
                comando.setInt(1,_cod);
                
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int codTipoServico = resultado.getInt("codTipoServico");
                String nome = resultado.getString("nome");
                String descricao = resultado.getString("descricao");
                Double valor = resultado.getDouble("valor");                
                
                TipoServico tipoServ = new TipoServico(codTipoServico, nome, descricao, valor);

                return tipoServ;
            }//terminar caminho feliz (IF)
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao procurar tipo de servico!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally        
        return (null);
    }//fecha metodo retornarTipoSerivocPorCod
    
    
    public List<TipoServico> retornaListaServicos() throws ClassNotFoundException, SQLException {
        List<TipoServico> listaTiposServico = new ArrayList<>();
        String sql = "SELECT * FROM tipoServico";
        try {
            conectar(sql);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int codTipoServico = resultado.getInt("codTipoServico");
                String nome = resultado.getString("nome");
                String descricao = resultado.getString("descricao");
                Double valor = resultado.getDouble("valor");                
                
                TipoServico tipoServ = new TipoServico(codTipoServico, nome, descricao, valor);
                    listaTiposServico.add(tipoServ);                    
            }//fecha while
            return listaTiposServico;
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao procurar funcionario!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally    
    }//fecha listarTipoServico

    public boolean verificaExistTipoServico() throws SQLException, ClassNotFoundException {
        try{
            String sql = "SELECT * FROM tipoServico";      
                conectar(sql);
            ResultSet resultado = comando.executeQuery();
                if(resultado.next()) {                
                    return true;
                }//fecha if
        }catch (SQLException e) {
                throw new SQLException("\nErro ao verificar tipo de servico!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
                   return false;   
    }//fecha verificaExistTipoServico
}
