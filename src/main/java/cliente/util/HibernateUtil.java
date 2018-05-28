package cliente.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

@SuppressWarnings("deprecation")

/**
 * @Classe Responsável por fazer a configuração do framework Hibernate utilizando seu arquivo de configuração (hibernate.config.xml)
 * @author ana
 * @Since 18/03/2018 02:46:15
 */
public class HibernateUtil {
	
    private static final SessionFactory sessionFactory = buildSession();
    
    /**
     * @Método Responsável por construir a sessão de conexão com o banco de dados por meio do framework Hibernate
     * @return
     */
    private static SessionFactory buildSession() {
        try {

            AnnotationConfiguration cfg = new AnnotationConfiguration();
            cfg.configure("hibernate.cfg.xml");
            return cfg.buildSessionFactory();
        } catch (Throwable e) {
            System.out.println("Criação do objeto falhou. Erro:" + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
