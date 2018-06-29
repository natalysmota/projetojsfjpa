package net.natalysoares.automoveis.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//Garante que a EntityManagerFactory seja criada apenas uma vez
public class JPAUtil {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("automoveis");

	//Permite que devolva uma EntityManager
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}