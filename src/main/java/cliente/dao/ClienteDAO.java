package cliente.dao;

import java.util.ArrayList;

import cliente.model.Cliente;
import cliente.resources.ClienteServico;

/**
 * @Classe Responsável por ser a interface entre o controlador e a camada de acesso aos dados 
 * @author ana
 * @Since 18/03/2018 03:05:21
 */

public interface ClienteDAO {

    public void salvar(ClienteServico cliente);

    public void atualizar(ClienteServico cliente);

    public void excluir(ClienteServico cliente);

    public void excluirPorCpf(String cpf);
    
    public void excluirPorCodigo(Integer codigo);

    public Cliente carregar(Integer codigo);
    
    public Cliente buscarClientePorCpf(String cpf);

    public ArrayList<Cliente> listar();
}
