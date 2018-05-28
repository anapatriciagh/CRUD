/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.factory;

import org.hibernate.Session;

import cliente.util.HibernateUtil;
																																																				
public class ConexaoHibernatePostgre {
	
	public static void main(String[] args) {
		Session sessao = null;
		try {
			sessao = HibernateUtil.getSessionFactory().openSession();																																																																																																																																																																																																																																																																																				
			System.out.println("Conectou!");
		} finally {
			sessao.close();																																																																																	
		}
	}
}
