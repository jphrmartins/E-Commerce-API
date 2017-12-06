package space.indietech.magojota.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoDao {

	private EntityManager em;

	@Autowired
	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	public List<ProdutoEntity> getProdutos(){
		return em.createQuery("from ProdutoEntity").getResultList();
	}
	
	@Transactional
	public void deleteProduto(long id) {
		ProdutoEntity pe = new ProdutoEntity();
		pe.setId(id);
		em.remove(pe);
	}
	
	@Transactional
	public ProdutoEntity pustProduto(ProdutoEntity pe) {
		return em.merge(pe);
	}
	
}
