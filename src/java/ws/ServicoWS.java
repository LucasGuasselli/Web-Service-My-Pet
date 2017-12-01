/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import DAO.ServicoDAO;
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
import model.Servico;


@Path("servicos")
public class ServicoWS {
   @Context
    private UriInfo context;

    /**
     * Creates a new instance of FuncionarioWS
     */
    public ServicoWS() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Servico> getServicos() throws ClassNotFoundException, SQLException {
        return ServicoDAO.getInstance().retornaListaServicos();
    }

    /*@GET
    @Path("/{funcionario/cod}")
    @Produces(MediaType.APPLICATION_JSON)
    public Funcionario getFuncionarioPorCod(@PathParam("codFunc") int cod) throws SQLException, ClassNotFoundException{
        return FuncionarioDAO.getInstance().retornaFuncionarioPorCod(cod);
    }*/
    
    @GET
    @Path("/{codServico}")
    @Produces(MediaType.APPLICATION_JSON)
    public Servico getServicoPorCod(@PathParam("codServico") int cod) throws SQLException, ClassNotFoundException{
        return ServicoDAO.getInstance().retornaServicoPorCod(cod); 
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void adicionarFuncionario(Servico servico, @Context final HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException{
        
        ServicoDAO.getInstance().cadastrarServico(servico);
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
    public void alterarServico(@PathParam("codServico") int codServico, Servico servico) throws SQLException, ClassNotFoundException{
        servico.setCodServico(codServico);
        ServicoDAO.getInstance().editarServico(servico);        
    }
    
    @DELETE
    @Path("/{codServico}")
    @Produces(MediaType.APPLICATION_JSON)
    public void removerServico(@PathParam("codServico")int codServico) throws SQLException, ClassNotFoundException{
        Servico servico = ServicoDAO.getInstance().retornaServicoPorCod(codServico);
        ServicoDAO.getInstance().deletarServico(servico);
    }
}
