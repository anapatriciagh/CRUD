package cliente.dao;

import java.util.ArrayList;

import org.eclipse.jdt.internal.compiler.ast.Clinit;
import org.hibernate.*;

import cliente.model.Cliente;
import cliente.resources.ClienteServico;

/**
 * @author ana
 * @Classe Responsável por implementar os métodos da regra de negócio assinalados na classe ClienteDAO.java
 * @Since 18/03/2018 02:53:25
 */

public class ClienteDAOHibernate implements ClienteDAO {

    private Session session;
    private Transaction transaction;
    
    public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	/**
     * @Metodo Responsavel por retornar um objeto Cliente
     * @param codigo
     * @return
     */
    public Cliente GetPessoa(Integer codigo){
    	 
		return this.session.get(Cliente.class, codigo);
	}
    
    /**
     * @Método Responsável por salvar um cliente no banco de dados utilizando o método save do framework Hibernate
     */
    @Override
    public void salvar(ClienteServico cliente) {
    	Cliente clienteEntidade = new Cliente();
    	clienteEntidade.setAtivo(true);
    	clienteEntidade.setNomeCompleto(cliente.getNomeCompleto());
    	clienteEntidade.setCpf(cliente.getCpf());
    	clienteEntidade.setTelefone(clienteEntidade.getTelefone());
    	clienteEntidade.setEmail(cliente.getEmail());
    	clienteEntidade.setSexo(cliente.getSexo());
    	clienteEntidade.setCodigo(cliente.getCodigo());
    	clienteEntidade.setLogradouro(cliente.getLogradouro());
    	clienteEntidade.setNumero(cliente.getNumero());
    	clienteEntidade.setBairro(cliente.getBairro());
    	clienteEntidade.setCidade(cliente.getCidade());
    	clienteEntidade.setUf(cliente.getUf());
        this.session.save(clienteEntidade);
    }

    /**
     * @Método Responsável por atualizar um cliente no banco de dados utilizando o método update do framework Hibernate
     */
    @Override
    public void atualizar(ClienteServico cliente) {
    	Cliente clienteEntidade = new Cliente();
    	clienteEntidade.setAtivo(cliente.isAtivo());
    	clienteEntidade.setNomeCompleto(cliente.getNomeCompleto());
    	clienteEntidade.setCpf(cliente.getCpf());
    	clienteEntidade.setTelefone(clienteEntidade.getTelefone());
    	clienteEntidade.setEmail(cliente.getEmail());
    	clienteEntidade.setSexo(cliente.getSexo());
    	clienteEntidade.setCodigo(cliente.getCodigo());
    	clienteEntidade.setLogradouro(cliente.getLogradouro());
    	clienteEntidade.setNumero(cliente.getNumero());
    	clienteEntidade.setBairro(cliente.getBairro());
    	clienteEntidade.setCidade(cliente.getCidade());
    	clienteEntidade.setUf(cliente.getUf());
        this.session.update(cliente);
    }
    
    /**
     * @Método Responsável por excluir um cliente do banco de dados utilizando o método delete do framework Hibernate
     */
    @Override
    public void excluir(ClienteServico cliente) {
        this.session.delete(cliente);
    }
    
    /**
     * @Metodo Responsavel por retornar uma lista de Clientes cadastrados
     */
    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<Cliente> listar() {
        return (ArrayList<Cliente>) this.session.createCriteria(Cliente.class).list();
    }
    
    /**
     * @Método Responsável por buscar um cliente no banco de dados por meio do seu cpf
     */
    @Override
    public Cliente carregar(Integer codigo) {
        return (Cliente) this.session.get(Cliente.class, codigo);
    }
    
    /**
     * @Método Responsável por buscar um cliente no banco de dados por meio do seu cpf
     */
    @Override
    public Cliente buscarClientePorCpf(String cpf){
        String hql = "select c from Cliente c where c.cpf = :cpf";
        Query consulta = this.session.createQuery(hql); 
        consulta.setString("cpf", cpf);
        System.out.println(consulta.uniqueResult());
        return (Cliente) consulta.uniqueResult();
    }
	
	/**
     * @Método Responsável por excluir um cliente do banco de dados por meio do seu id
     */
	@Override
	public void excluirPorCodigo(Integer codigo) {
		Cliente cliente = this.GetPessoa(codigo);
		this.session.delete(cliente);
	}
	
	 /**
     * @Método Responsável por excluir um cliente do banco de dados por meio do seu cpf
     */
    @Override
    public void excluirPorCpf(String cpf) {
        String hql = "delete c from Cliente c where c.cpf = :cpf";
        this.setTransaction(this.session.beginTransaction());
        Query consulta = this.session.createQuery(hql);
        consulta.setString("cpf", cpf);
    }
}
