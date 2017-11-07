/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import com.google.gson.Gson;
import dao.PessoaDAO;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.Pessoa;

/**
 *
 * @author Marcelo
 * @since 21/06/2017
 */
@Path("pessoa")
public class PessoaResource {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/salvar")
    public boolean inserir(String json) {
        try {
            Pessoa pessoa = (Pessoa) new Gson().fromJson(json, Pessoa.class);
            return new PessoaDAO().salvar(pessoa);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listar/{cod}")
    public String listar(@PathParam("cod") int cod) {
        Pessoa pessoa = null;
        try {
            pessoa = new PessoaDAO().listar(cod);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return new Gson().toJson(pessoa);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listar")
    public String listar() {
        List<Pessoa> pessoa = null;
        try {
            pessoa = new PessoaDAO().listar();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return new Gson().toJson(pessoa);
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/deletar/{cod}")
    public boolean deletar(@PathParam("cod") int cod) {
        try {
            return new PessoaDAO().deletar(cod);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
    
}
