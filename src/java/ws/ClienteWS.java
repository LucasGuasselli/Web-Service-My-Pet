/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import DAO.ClienteDAO;
import DAO.FuncionarioDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import model.Cliente;
import model.Funcionario;

@Path("clientes")
public class ClienteWS {
    @Context
    private UriInfo context;

    
    public ClienteWS() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getClientes() throws ClassNotFoundException, SQLException {
        return ClienteDAO.getInstance().retornaListaClientes();
    }

       
    @GET
    @Path("/{codCli}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente getClientePorCpf(@PathParam("codCli") String cpf) throws SQLException, ClassNotFoundException{
        return ClienteDAO.getInstance().retornaClientePorCpf(cpf);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void adicionarCliente(Cliente cli, @Context final HttpServletResponse response) throws SQLException, ClassNotFoundException{
        
        ClienteDAO.getInstance().cadastrarCliente(cli);
        //Alterar o codigo para 201 (created)
        response.setStatus(HttpServletResponse.SC_CREATED);
        try{
            response.flushBuffer();
        } catch (IOException e) {
            //Erro 500
            throw new InternalServerErrorException();
        }        
    }
    
    @PUT
    @Path("/{codCli}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void alterarCliente(@PathParam("codCli") int codCli, Cliente cliente) throws SQLException, ClassNotFoundException{
        cliente.setCodCliente(codCli);
        ClienteDAO.getInstance().editarCliente(cliente); 
        
    }
    
    @DELETE
    @Path("/{codCli}")
    @Produces(MediaType.APPLICATION_JSON)
    public void removerCliente(@PathParam("codCli")int codCli) throws SQLException, ClassNotFoundException{
        Cliente cli = ClienteDAO.getInstance().retornaClientePorId(codCli);
        ClienteDAO.getInstance().deletarCliete(cli);
    }
}
