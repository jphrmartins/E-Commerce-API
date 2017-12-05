 package space.indietech.produto.dao;

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

	@Transactional
	public ProdutoEntity setProdutoLista(ProdutoEntity produto) {
	return em.merge(produto);

	}

	public List<ProdutoEntity> getListaProduto(){
	List<ProdutoEntity> produtos = em.createQuery("from ProdutoEntity").getResultList();
	return produtos;
}

	@Transactional
	public void deletarProduto(Long codigo) {
		ProdutoEntity produto = new ProdutoEntity();
		produto.setCodigo(codigo);
		produto = em.find(ProdutoEntity.class, codigo);
		if (produto != null) {
			em.remove(produto);
		}
	}
}
