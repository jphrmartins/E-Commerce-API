package space.indietech.calculadora.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculadoraDao {
	private EntityManager em;

	@Autowired
	public CalculadoraDao(EntityManager em) {
		this.em = em;
	}
	@Transactional
	public void addCalculo(CalculadoraEntity cal) {
		em.merge(cal);
	}
	public CalculadoraEntity getcalculo(String expressao) {
		CalculadoraEntity calculadora = new CalculadoraEntity();
		calculadora = em.find(CalculadoraEntity.class, expressao);
		return calculadora;
		
	}
	public List<CalculadoraEntity> getBanco() {
		return em.createQuery("from CalculadoraEntity").getResultList();
		
	}
}
