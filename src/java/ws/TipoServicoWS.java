/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import DAO.TipoServicoDAO;
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
import model.TipoServico;


@Path("tipoServicos")
public class TipoServicoWS {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FuncionarioWS
     */
    public TipoServicoWS() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoServico> getTiposServico() throws ClassNotFoundException, SQLException {
        return TipoServicoDAO.getInstance().retornaListaServicos();
    }

    /*@GET
    @Path("/{funcionario/cod}")
    @Produces(MediaType.APPLICATION_JSON)
    public Funcionario getFuncionarioPorCod(@PathParam("codFunc") int cod) throws SQLException, ClassNotFoundException{
        return FuncionarioDAO.getInstance().retornaFuncionarioPorCod(cod);
    }*/
    
    @GET
    @Path("/{codTipoServico}")
    @Produces(MediaType.APPLICATION_JSON)
    public TipoServico getTipoServicoPorCod(@PathParam("codTipoServico") int cod) throws SQLException, ClassNotFoundException{
        return TipoServicoDAO.getInstance().retornaTipoServicoPorCod(cod);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void adicionarTipoServico(TipoServico tipoServico, @Context final HttpServletResponse response) throws SQLException, ClassNotFoundException{
        
        TipoServicoDAO.getInstance().cadastrarTipoServico(tipoServico);
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
    @Path("/{codTipoServico}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void alterarTipoServico(@PathParam("codTipoServico") int codTipoServico, TipoServico tipoServico) throws SQLException, ClassNotFoundException{
        //tipoServico.setCodTipoServico(codTipoServico);
        TipoServicoDAO.getInstance().editarTipoServico(tipoServico);
        
    }
    
    @DELETE
    @Path("/{codTipoServico}")
    @Produces(MediaType.APPLICATION_JSON)
    public void removerTipoServico(@PathParam("codTipoServico")int codTipoServico) throws SQLException, ClassNotFoundException{
        TipoServico tipoServico = TipoServicoDAO.getInstance().retornaTipoServicoPorCod(codTipoServico);
        TipoServicoDAO.getInstance().deletarTipoServico(tipoServico);
    }
}
