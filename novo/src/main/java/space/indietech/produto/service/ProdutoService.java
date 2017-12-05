package space.indietech.produto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import space.indietech.produto.ProdutoConvert;
import space.indietech.produto.dao.ProdutoDao;
import space.indietech.produto.dao.ProdutoEntity;

@Component
public class ProdutoService {
	private ProdutoDao pDao;

	@Autowired
	public ProdutoService(ProdutoDao pDao) {
		this.pDao = pDao;
	}

	public List<ProdutoBO> getLista() {
		List<ProdutoBO> produtosBO = new ProdutoConvert().convertEntityBO(pDao.getListaProduto());
		return produtosBO;
	}

	// public ProdutoEntity getProdutoCodigo(Long codigo) {
	// List<ProdutoEntity> minhaLista = getLista();
	// for (ProdutoEntity produto : minhaLista) {
	// if (produto.getCodigo() == codigo) {
	// return produto;
	// }
	// }
	// throw new RuntimeException();
	// }

	public ProdutoEntity addProdutoList(ProdutoEntity produto) {
		produto = pDao.setProdutoLista(produto);
		return produto;
	}

	public void deletaProduto(Long codigo) {
		pDao.deletarProduto(codigo);
	}

}
