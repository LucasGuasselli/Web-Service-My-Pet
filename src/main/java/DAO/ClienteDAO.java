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
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import persistencia.ConnectionFactory;

/**
 *
 * @author lucas
 */
public class ClienteDAO {

    private Connection conexao;
    private PreparedStatement comando;
    private static ClienteDAO instancia;


    public static synchronized ClienteDAO getInstance(){
        if(instancia == null){
            instancia = new ClienteDAO();
        }
        return instancia;
    }
    public Connection conectar(String sql) throws SQLException, ClassNotFoundException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql);
        return conexao;
    }//fecha conectar

    /*CADASTRA CLIENTE NA TABELA cliente NO BANCO DE DADOS
    *@param objeto cliente
    *@return void
    */
    public void cadastrarCliente(Cliente cliente) throws SQLException, ClassNotFoundException{

        try{
            String sql = "insert into cliente(nome, cpf, telefone, email) values (?, ?, ?, ?)";
               conectar(sql);
                    comando.setString(1,cliente.getNome());
                    comando.setString(2,cliente.getCpf());
                    comando.setString(3,cliente.getTelefone());
                    comando.setString(4,cliente.getEmail());
                 if(comando.executeUpdate()>0){
                     System.out.println("\nCadastro realizado com sucesso!");
                 }//fecha if
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao cadastrar cliente!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
    }//fecha cadastrarCliente


    public void editarCliente(Cliente cliente) throws SQLException, ClassNotFoundException{
        try{
            String sql = "UPDATE cliente SET nome = ?, cpf = ?, telefone = ?, email = ? "
                    + "WHERE codCliente = ?";

            conectar(sql);
            comando.setString(1, cliente.getNome());
            comando.setString(2, cliente.getCpf());
            comando.setString(3, cliente.getTelefone());
            comando.setString(4, cliente.getEmail());
            comando.executeUpdate();
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao editar cliente!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
    }//fecha editarCliente


    public void deletarCliete(Cliente cliente) throws SQLException, ClassNotFoundException{

        try{
                String sql = "delete from cliente where codCliente = ?";
                    conectar(sql);

                comando.setInt(1,cliente.getCodCliente());
                 if(comando.executeUpdate()>0){
                     System.out.println("\nCliente deletado com sucesso!");
                 }//fecha if
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao deletar cliente!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
    }//fecha deletarCliente


    public Cliente retornaClientePorCpf(String _cpf) throws SQLException, ClassNotFoundException {

        try{
            String sql = "SELECT * FROM cliente WHERE cpf = ?";

            conectar(sql);
            comando.setString(1, _cpf);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int id = resultado.getInt("codCliente");
                String nome = resultado.getString("nome");
                String cpf = resultado.getString("cpf");
                String telefone = resultado.getString("telefone");
                String email = resultado.getString("email");

                Cliente cli = new Cliente(id, nome, cpf, telefone, email);

                return cli;
            }//fecha if
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao procurar cliente!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
                return (null);
    }//fecha procurarPorCpf


    public List<Cliente> retornaClientePorNome(String _nome) throws ClassNotFoundException, SQLException {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE nome LIKE ?";

        try {
            conectar(sql);
            comando.setString(1, "%" + _nome + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("codCliente");
                String nome = resultado.getString("nome");
                String cpf = resultado.getString("cpf");
                String telefone = resultado.getString("telefone");
                String email = resultado.getString("email");

                Cliente cli = new Cliente(id, nome, cpf, telefone, email);
                    listaClientes.add(cli);
            }//fecha while
            return listaClientes;
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao procurar cliente!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
    }//fecha metodo listarPorNome


    public Cliente retornaClientePorId(int _id)throws SQLException, ClassNotFoundException {
        try{

            String sql = "SELECT * FROM cliente WHERE codCliente = ?";
                conectar(sql);
                comando.setInt(1,_id);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int id = resultado.getInt("codCliente");
                String nome = resultado.getString("nome");
                String cpf = resultado.getString("cpf");
                String telefone = resultado.getString("telefone");
                String email = resultado.getString("email");

                Cliente cli = new Cliente(id, nome, cpf, telefone, email);

                return cli;
            }//terminar caminho feliz (IF)
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao procurar cliente!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
        return (null);
    }//fecha metodo procurarPorId


    public List<Cliente> retornaListaClientes() throws ClassNotFoundException, SQLException {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try {
            conectar(sql);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("codCliente");
                String nome = resultado.getString("nome");
                String cpf = resultado.getString("cpf");
                String telefone = resultado.getString("telefone");
                String email = resultado.getString("email");

                Cliente cli = new Cliente(id, nome, cpf, telefone, email);

                    listaClientes.add(cli);
            }//fecha while
            return listaClientes;
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao procurar cliente!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
    }//fecha listarClientes


    public boolean verificaClienteNome(String _nome) throws ClassNotFoundException, SQLException {
        try{
            String sql = "SELECT * FROM cliente WHERE nome = ?";
                conectar(sql);
                comando.setString(1, "%" + _nome + "%");
            ResultSet resultado = comando.executeQuery();
                if (resultado.next()) {
                    return true;
                }//fecha if
        }catch (SQLException e) {
                throw new SQLException("\nErro ao verificar cliente!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
                return false;
    }//fecha metodo verificaNome

    public boolean verificaExistCliente() throws SQLException, ClassNotFoundException {
        try{
            String sql = "SELECT * FROM cliente";
                conectar(sql);
            ResultSet resultado = comando.executeQuery();
                if(resultado.next()) {
                    return true;
                }//fecha if
        }catch (SQLException e) {
                throw new SQLException("\nErro ao verificar cliente!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
                   return false;
    }//fecha verificaExistCliente
}
