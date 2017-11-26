/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import DAO.FuncionarioDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import model.Funcionario;

/**
 * REST Web Service
 *
 * 
 */
@Path("funcionarios")
public class FuncionarioWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FuncionarioWS
     */
    public FuncionarioWS() {
    }

     @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Funcionario> getFuncionarios() throws ClassNotFoundException, SQLException {
        return FuncionarioDAO.getInstance().retornaListaFuncionarios();
    }

    /*@GET
    @Path("/{funcionario/cod}")
    @Produces(MediaType.APPLICATION_JSON)
    public Funcionario getFuncionarioPorCod(@PathParam("codFunc") int cod) throws SQLException, ClassNotFoundException{
        return FuncionarioDAO.getInstance().retornaFuncionarioPorCod(cod);
    }*/
    
    @GET
    @Path("/{codFunc}")
    @Produces(MediaType.APPLICATION_JSON)
    public Funcionario getFuncionarioPorCpf(@PathParam("codFunc") String cpf) throws SQLException, ClassNotFoundException{
        return FuncionarioDAO.getInstance().retornaFuncionarioPorCpf(cpf);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void adicionarFuncionario(Funcionario func, @Context final HttpServletResponse response) throws SQLException, ClassNotFoundException{
        
        FuncionarioDAO.getInstance().cadastrarFuncionario(func);
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
    @Path("/{codFunc}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void alterarFuncionario(@PathParam("codFunc") int codFunc, Funcionario funcionario) throws SQLException, ClassNotFoundException{
        funcionario.setCodFunc(codFunc);
        FuncionarioDAO.getInstance().editarFuncionario(funcionario);
        
    }
    
    @DELETE
    @Path("/{codFunc}")
    @Produces(MediaType.APPLICATION_JSON)
    public void removerFuncionario(@PathParam("codFunc")int codFunc) throws SQLException, ClassNotFoundException{
        Funcionario func = FuncionarioDAO.getInstance().retornaFuncionarioPorCod(codFunc);
        FuncionarioDAO.getInstance().deletarFuncionario(func);
    }
    
}
