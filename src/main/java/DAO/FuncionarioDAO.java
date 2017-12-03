
package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Funcionario;
import persistencia.ConnectionFactory;


public class FuncionarioDAO {

    private Connection conexao;
    private PreparedStatement comando;
    DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static FuncionarioDAO instancia;

    public Connection conectar(String sql) throws SQLException, ClassNotFoundException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql);
        return conexao;
    }//fecha conectar

    public static synchronized FuncionarioDAO getInstance(){
        if(instancia == null){
            instancia = new FuncionarioDAO();
        }
        return instancia;
    }
    public void cadastrarFuncionario(Funcionario funcionario) throws SQLException, ClassNotFoundException{

        try{
            String sql = "insert into funcionario(nome, cpf, telefone, dataNascimento, especialidade) values (?, ?, ?, ?, ?)";
               conectar(sql);
                    comando.setString(1,funcionario.getNome());
                    comando.setString(2,funcionario.getCpf());
                    comando.setString(3,funcionario.getTelefone());
                        Date dataSql = Date.valueOf(funcionario.getDataNascimento());
                        comando.setDate(4,dataSql);
                    comando.setString(5,funcionario.getEspecialidade());

                 if(comando.executeUpdate()>0){
                     System.out.println("\nCadastro realizado com sucesso!");
                 }//fecha if
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao cadastrar funcionario!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
    }//fecha cadastrarFuncionario


    public void editarFuncionario(Funcionario funcionario) throws SQLException, ClassNotFoundException{
        try{
            String sql = "UPDATE funcionario SET nome = ?, cpf = ?, telefone = ?, dataNascimento = ?, especialidade = ? "
                    + "WHERE codFunc = ?";

            conectar(sql);
            comando.setString(1, funcionario.getNome());
            comando.setString(2, funcionario.getCpf());
            comando.setString(3, funcionario.getTelefone());
            Date dataSql = Date.valueOf(funcionario.getDataNascimento());
                        comando.setDate(4,dataSql);
            comando.setString(5, funcionario.getEspecialidade());
            comando.setInt(6, funcionario.getCodFunc());
            comando.executeUpdate();
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao editar funcionario!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
    }//fecha editarFuncionario


    public void deletarFuncionario(Funcionario funcionario) throws SQLException, ClassNotFoundException{

        try{
                String sql = "delete from funcionario where codFunc = ?";
                    conectar(sql);

                comando.setInt(1,funcionario.getCodFunc());
                 if(comando.executeUpdate()>0){
                     System.out.println("\nfuncionario deletado com sucesso!");
                 }//fecha if
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao deletar funcionario!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
    }//fecha deletarFuncionario


    public Funcionario retornaFuncionarioPorCpf(String _cpf) throws SQLException, ClassNotFoundException {

        try{
            String sql = "SELECT * FROM funcionario WHERE cpf = ?";

            conectar(sql);
            comando.setString(1, _cpf);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int codFunc = resultado.getInt("codFunc");
                String nome = resultado.getString("nome");
                String cpf = resultado.getString("cpf");
                String telefone = resultado.getString("telefone");
                Date dataSql = resultado.getDate("dataNascimento");
                LocalDate dataNascimento = dataSql.toLocalDate();
                String especialidade = resultado.getString("especialidade");

                Funcionario func = new Funcionario(codFunc, nome, cpf, telefone, dataNascimento, especialidade);

                return func;
            }//fecha if
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao procurar funcionario!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
                return (null);
    }//fecha procurarPorCpf

    //retorna um array para caso tenha mais de um funcionario com o mesmo nome
    public List<Funcionario> retornaFuncionarioPorNome(String _nome) throws ClassNotFoundException, SQLException {
        List<Funcionario> listaFuncionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario WHERE nome LIKE ?";

        try {
            conectar(sql);
            comando.setString(1, "%" + _nome + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int codFunc = resultado.getInt("codFunc");
                String nome = resultado.getString("nome");
                String cpf = resultado.getString("cpf");
                String telefone = resultado.getString("telefone");
                Date dataSql = resultado.getDate("dataNascimento");
                LocalDate dataNascimento = dataSql.toLocalDate();
                String especialidade = resultado.getString("especialidade");

                Funcionario func = new Funcionario(codFunc, nome, cpf, telefone, dataNascimento, especialidade);

                    listaFuncionarios.add(func);
            }//fecha while
            return listaFuncionarios;
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao procurar funcionario!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
    }//fecha metodo listarPorNome

    public Funcionario retornaFuncionarioPorCod(int _cod)throws SQLException, ClassNotFoundException {
        try{
            String sql = "SELECT * FROM funcionario WHERE codFunc = ?";
                conectar(sql);
                comando.setInt(1,_cod);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int codFunc = resultado.getInt("codFunc");
                String nome = resultado.getString("nome");
                String cpf = resultado.getString("cpf");
                String telefone = resultado.getString("telefone");
                Date dataSql = resultado.getDate("dataNascimento");
                LocalDate dataNascimento = dataSql.toLocalDate();
                String especialidade = resultado.getString("especialidade");

                Funcionario func = new Funcionario(codFunc, nome, cpf, telefone, dataNascimento, especialidade);

                return func;
            }//terminar caminho feliz (IF)
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao procurar funcionario!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
        return (null);
    }//fecha metodo procurarPorCod


    public List<Funcionario> retornaListaFuncionarios() throws ClassNotFoundException, SQLException {
        List<Funcionario> listaFuncionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario";
        try {
            conectar(sql);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int codFunc = resultado.getInt("codigo");
                String nome = resultado.getString("nome");
                String cpf = resultado.getString("cpf");
                String telefone = resultado.getString("telefone");
                Date dataSql = resultado.getDate("dataNascimento");
                LocalDate dataNascimento = dataSql.toLocalDate();
                String especialidade = resultado.getString("especialidade");

                Funcionario func = new Funcionario(codFunc, nome, cpf, telefone, dataNascimento, especialidade);
                    listaFuncionarios.add(func);
            }//fecha while
            return listaFuncionarios;
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao procurar funcionario!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
    }//fecha listarFuncionarios


    public boolean verificaFunconarioCpf(String _cpf) throws ClassNotFoundException, SQLException {
        try{
            String sql = "SELECT * FROM funcionario WHERE cpf = ?";
                conectar(sql);
                comando.setString(1, _cpf);
            ResultSet resultado = comando.executeQuery();
                if (resultado.next()) {
                    return true;
                }//fecha if
        }catch (SQLException e) {
                throw new SQLException("\nErro ao verificar funcionario!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
                return false;
    }//fecha verificaCpf

    public boolean verificaFuncionarioNome(String _nome) throws ClassNotFoundException, SQLException {
        try{
            String sql = "SELECT * FROM funcionario WHERE nome = ?";
                conectar(sql);
                comando.setString(1, "%" + _nome + "%");
            ResultSet resultado = comando.executeQuery();
                if (resultado.next()) {
                    return true;
                }//fecha if
        }catch (SQLException e) {
                throw new SQLException("\nErro ao verificar funcionario!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
                return false;
    }//fecha metodo verificaNome

    public boolean verificaExistFuncionario() throws SQLException, ClassNotFoundException {
        try{
            String sql = "SELECT * FROM funcionario";
                conectar(sql);
            ResultSet resultado = comando.executeQuery();
                if(resultado.next()) {
                    return true;
                }//fecha if
        }catch (SQLException e) {
                throw new SQLException("\nErro ao verificar funcionario!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
                   return false;
    }//fecha verificaExistFuncionario
}//fecha classe
