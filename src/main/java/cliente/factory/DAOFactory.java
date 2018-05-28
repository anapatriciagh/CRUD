package cliente.factory;

import cliente.dao.ClienteDAO;
import cliente.dao.ClienteDAOHibernate;
import cliente.util.HibernateUtil;

/**
 * @Classe Responsável por criar um usuário para a sessão de conexão com o banco de dados
 * @author ana
 * @Since 18/03/2018 02:49:05
 *
 */

public class DAOFactory {
	
	/**
	 * @Método Responsável por setar a conexão corrente na classe responsável por fazer o acesso aos dados dispostos no banco
	 * @return
	 */
    public static ClienteDAO criarClienteDAO() {
        ClienteDAOHibernate clienteDAOHibernate = new ClienteDAOHibernate();
        clienteDAOHibernate.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return clienteDAOHibernate;
    }
}
