package br.fundatec.magojota.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.fundatec.TokenInfo;

@Component
public class MagoJotaDao {

	private EntityManager em;

	@Autowired
	public MagoJotaDao(EntityManager em) {
		this.em = em;
	}

	public List<ProdutoEntity> getProdutos() {
		return em.createQuery("from ProdutoEntity").getResultList();
	}
	public ProdutoEntity getProdutoId(long id) {
		ProdutoEntity pe = em.find(ProdutoEntity.class, id);
		if(pe != null) {
			return pe;
		}
		throw new RuntimeException();
	}
	@Transactional
	public void deleteProduto(long id) {
		ProdutoEntity pe = em.find(ProdutoEntity.class, id);
		if (pe != null) {
			em.remove(pe);
		}
	}

	@Transactional
	public ProdutoEntity pustProduto(ProdutoEntity pe) {
		return em.merge(pe);
	}
	
	@Transactional
	public CarrinhoEntity createCarrinho() {
		CarrinhoEntity ce = new CarrinhoEntity();
		ce.setDono(TokenInfo.getNome());
		ce.setProdutos(new ArrayList<>());
		return em.merge(ce);
	}
	@Transactional
	public CarrinhoEntity updateCarrinho(CarrinhoEntity ce) {
		return em.merge(ce);
	}
	
	public CarrinhoEntity getCarrinho() {
		return em.find(CarrinhoEntity.class, TokenInfo.getNome());
		
	}
	public List<CarrinhoEntity> getCarrinhos(){
		return em.createQuery("from CarrinhoEntity").getResultList();
	}

}
