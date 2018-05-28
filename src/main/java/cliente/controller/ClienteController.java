package cliente.controller;

import java.util.ArrayList;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cliente.dao.ClienteDAO;
import cliente.factory.DAOFactory;
import cliente.model.Cliente;
import cliente.resources.ClienteServico;

/**
 * @Classe Responsável por controlar as requisições direcionadas à camada de Acesso aos dados por meio de objetos, do inglês, Data Access Object (DAO)
 * @author ana
 * @Since 18/03/2018 02:35:58
 *
 */
@Path("/clienteservico")
public class ClienteController {

    private ClienteDAO clienteDAO;
    
    /**
     * @Método Responsável por inicializar um usuário com informações de uma nova sessão de conexão com o banco de dados
     */
    public ClienteController() {
        this.clienteDAO = DAOFactory.criarClienteDAO();
    }
    
    /**
     * @Método Responsável por buscar no banco de dados um usuário por meio do seu CPF
     * @param cpf
     * @return
     */
    @GET
	@Produces("application/json; charset=UTF-8")
	@Path("/getCliente/{codigo}")
    public Cliente carregar(@PathParam("codigo") Integer codigo) {
        Cliente cliente =  this.clienteDAO.carregar(codigo);
        if(cliente != null)
			return cliente;
 
		return null;
    }
    
    /**
     * @Método Responsável por salvar um cliente no banco de dados
     * @param cliente
     */
    @POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/salvar")
    public String salvar(ClienteServico cliente) {
        try {
        	
                this.clienteDAO.salvar(cliente);
                return "Registro cadastrado com sucesso!";

		} catch (Exception e) {
			return "Erro ao cadastrar um registro " + e.getMessage();
		}
        
    }
    
    /**
     * @Método Responsável por listar os clientes existentes no banco de dados
     * @return
     */
    @GET
	@Produces("application/json; charset=UTF-8")
	@Path("/listar")
    public ArrayList<Cliente> listar() {
        return this.clienteDAO.listar();
    }
    
    /**
     * @Método Responsável por excluir um cliente do banco de dados pelo seu cpf
     * @param cpf
     */
    public String excluirPorCpf(String cpf) {
        try {
        	 
        	 this.clienteDAO.excluirPorCpf(cpf);
 
			return "Registro excluido com sucesso!";
 
		} catch (Exception e) {
 
			return "Erro ao excluir o registro! " + e.getMessage();
		}
    }
    
    @DELETE
	@Produces("application/json; charset=UTF-8")
	@Path("/excluir/{codigo}")	
	public String excluir(@PathParam("codigo") Integer codigo){
 
		try {
 
			this.clienteDAO.excluirPorCodigo(codigo);
 
			return "Registro excluido com sucesso!";
 
		} catch (Exception e) {
 
			return "Erro ao excluir o registro! " + e.getMessage();
		}
 
	}
    
    @PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")	
	@Path("/alterar")
	public String Alterar(ClienteServico cliente){
 
		try {
			this.clienteDAO.atualizar(cliente);
 
			return "Registro alterado com sucesso!";
 
		} catch (Exception e) {
 
			return "Erro ao alterar o registro " + e.getMessage();
 
		}
 
	}
}
