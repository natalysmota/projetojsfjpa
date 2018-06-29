package net.natalysoares.automoveis.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import net.natalysoares.automoveis.model.Automovel;
import net.natalysoares.automoveis.util.JPAUtil;

@ManagedBean
public class AutomovelBean {

	//ATRIBUTOS
	private Automovel automovel = new Automovel();
	private List<Automovel> automoveis;

	//GETTERS/SETTERS
	public Automovel getAutomovel() {
		return automovel;
	}
	public void setAutomovel(Automovel automovel) {
		this.automovel = automovel;
	}
	public List<Automovel> getAutomoveis() {
		if(this.automoveis == null) {
			EntityManager em = JPAUtil.getEntityManager();
			Query query = em.createQuery("SELECT a FROM Automovel a", Automovel.class);
			this.automoveis = query.getResultList();
			em.close();
		}
		return automoveis;
	}

	//MÉTODOS	
	public void salvar() {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(automovel); //Faz com que objeto passado seja gerenciado
		em.getTransaction().commit();
		em.close();
	}
	
	public void excluir(Automovel automovel) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction(); //Dispara a escrita no Banco de Dados
		
		tx.begin();
		automovel = em.merge(automovel); //Devolve a instância gerenciada
		em.remove(automovel);
		tx.commit(); //Faz com objeto gerenciado vá de fato para o banco de dados
		em.close();
	}

}