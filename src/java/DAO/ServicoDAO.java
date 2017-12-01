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
import model.Cliente;
import model.Funcionario;
import model.Servico;
import model.TipoServico;
import persistencia.ConnectionFactory;



public class ServicoDAO {
    
    private Connection conexao;
    private PreparedStatement comando;
    DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private ClienteDAO cDAO = new ClienteDAO();
    private FuncionarioDAO fDAO = new FuncionarioDAO();
    private TipoServicoDAO tsDAO = new TipoServicoDAO();

    private static ServicoDAO instancia;
    
    public Connection conectar(String sql) throws SQLException, ClassNotFoundException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql);
        return conexao;
    }//fecha conectar    
    
    public static synchronized ServicoDAO getInstance(){
        if(instancia == null){
            instancia = new ServicoDAO();
        }
        return instancia;
    }
    
    public void cadastrarServico(Servico servico) throws SQLException, ClassNotFoundException{
        
        try{   
            String sql = "insert into servico(codCliente, nomePet, dia, horario, codFunc, codTipoServico,"
                    + " valorServico, observacao) values (?, ?, ?, ?, ?, ?, ?, ?)";
               conectar(sql);
                    comando.setInt(1,servico.getCodCliente());
                    comando.setString(2,servico.getNomePet());
                    comando.setInt(3,servico.getDia());
                    comando.setString(4,servico.getHorario());
                    comando.setInt(5,servico.getCodFunc());
                    comando.setInt(6,servico.getCodTipoServico());
                    comando.setDouble(7,servico.getValorServico());
                    comando.setString(8,servico.getObservacao());
                  //  comando.setBoolean(9,servico.isStatus());
                
                                        
                 if(comando.executeUpdate()>0){
                     System.out.println("\nCadastro realizado com sucesso!");
                 }//fecha if
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao cadastrar servico!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally           
    }//fecha cadastrarservico
    
   
    public void editarServico(Servico servico) throws SQLException, ClassNotFoundException{
        try{
            String sql = "UPDATE servico SET codCliente = ?, nomePet = ?, dia = ?, horario = ?,"
                    + " codFunc = ?, codTipoServico = ??, valorServico = ?, observacao = ? "
                    + "WHERE codServico = ?";
 
            conectar(sql);
            comando.setInt(1,servico.getCodCliente());
            comando.setString(2,servico.getNomePet());
            comando.setInt(3,servico.getDia());
            comando.setString(4,servico.getHorario());
            comando.setInt(5,servico.getCodFunc());
            comando.setInt(6,servico.getCodTipoServico());
            comando.setDouble(7,servico.getValorServico());
            comando.setString(8,servico.getObservacao());
            //comando.setBoolean(9,servico.isStatus()); 
            
            comando.setInt(9,servico.getCodServico());

            comando.executeUpdate();

        }catch (SQLException e) {
                 throw new SQLException("\nErro ao editar servico!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
    }//fecha editarFuncionario
    
  
    public void deletarServico(Servico servico) throws SQLException, ClassNotFoundException{
        
        try{
                String sql = "delete from servico where codServico = ?";
                    conectar(sql); 
                    
                comando.setInt(1,servico.getCodServico());
                 if(comando.executeUpdate()>0){
                     System.out.println("\nServico deletado com sucesso!");
                 }//fecha if
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao deletar servico!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally            
    }//fecha deletarServico
           
    public Servico retornaServicoPorCod(int _cod)throws SQLException, ClassNotFoundException {
        try{               
            String sql = "SELECT * FROM servico WHERE codServico = ?";
                conectar(sql); 
                comando.setInt(1,_cod);
                
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int codServico = resultado.getInt("codServico");
                int codCliente = resultado.getInt("codCliente");
                String nomePet = resultado.getString("nomePet");
                int dia = resultado.getInt("dia");
                String horario = resultado.getString("horario");
                int codFunc = resultado.getInt("codFunc");
                int codTipoServico = resultado.getInt("codTipoServico");
                double valorServico = resultado.getDouble("valorServico");
                String observacao = resultado.getString("observacao");
                boolean status = resultado.getBoolean("status");
                
                Cliente cli = cDAO.retornaClientePorId(codCliente);
                Funcionario func = fDAO.retornaFuncionarioPorCod(codFunc);
                TipoServico tipoServ = tsDAO.retornaTipoServicoPorCod(codTipoServico);
                
                Servico serv = new Servico(codServico, cli, nomePet, dia, horario, func, tipoServ, observacao);

                return serv;
            }//terminar caminho feliz (IF)
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao procurar servico!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally        
        return (null);
    }//fecha metodo procurarPorCod
    
    
    public List<Servico> retornaListaServicos() throws ClassNotFoundException, SQLException {
        List<Servico> listaServicos = new ArrayList<>();
        String sql = "SELECT * FROM servico";
        try {
            conectar(sql);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int codServico = resultado.getInt("codServico");
                int codCliente = resultado.getInt("codCliente");
                String nomePet = resultado.getString("nomePet");
                int dia = resultado.getInt("dia");
                String horario = resultado.getString("horario");
                int codFunc = resultado.getInt("codFunc");
                int codTipoServico = resultado.getInt("codTipoServico");
                double valorServico = resultado.getDouble("valorServico");
                String observacao = resultado.getString("observacao");
               // boolean status = resultado.getBoolean("status");
                
                Cliente cli = cDAO.retornaClientePorId(codCliente);
                Funcionario func = fDAO.retornaFuncionarioPorCod(codFunc);
                TipoServico tipoServ = tsDAO.retornaTipoServicoPorCod(codTipoServico);
                
                Servico serv = new Servico(codServico, cli, nomePet, dia, horario, func, tipoServ, observacao);

                    listaServicos.add(serv);                    
            }//fecha while
            return listaServicos;
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao procurar servico!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally    
    }//fecha listarFuncionarios

    public boolean verificaExistServico() throws SQLException, ClassNotFoundException {
        try{
            String sql = "SELECT * FROM servico";      
                conectar(sql);
            ResultSet resultado = comando.executeQuery();
                if(resultado.next()) {                
                    return true;
                }//fecha if
        }catch (SQLException e) {
                throw new SQLException("\nErro ao verificar servico!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
                   return false;   
    }//fecha verificaExistServico
}
