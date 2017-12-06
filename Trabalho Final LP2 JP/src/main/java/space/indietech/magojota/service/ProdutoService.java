package space.indietech.magojota.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import space.indietech.magojota.converter.ConverterProduto;
import space.indietech.magojota.dao.ProdutoDao;
import space.indietech.magojota.dao.ProdutoEntity;

@Component
public class ProdutoService {

	private ProdutoDao pDao;

	@Autowired
	public ProdutoService(ProdutoDao pDao) {
		this.pDao = pDao;
	}

	public ProdutoBo save(ProdutoBo pBo) {
		ProdutoEntity pe = ConverterProduto.convertProdutoBoToEntity(pBo);
		pe = pDao.pustProduto(pe);
		pBo = ConverterProduto.convertProdutoEntityToBo(pe);
		return pBo;
	}
	
	public void delete(long id) {
		pDao.deleteProduto(id);
	}
	
	public List<ProdutoBo> getProdutos(){
		List<ProdutoEntity> entities = pDao.getProdutos();
		List<ProdutoBo> bos = ConverterProduto.convertListProdutoEntityToBo(entities);
		return bos;
	}
	
}
