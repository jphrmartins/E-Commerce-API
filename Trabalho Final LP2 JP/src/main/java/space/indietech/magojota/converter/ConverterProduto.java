package space.indietech.magojota.converter;

import java.util.ArrayList;
import java.util.List;

import space.indietech.magojota.dao.ProdutoEntity;
import space.indietech.magojota.service.ProdutoBo;

public class ConverterProduto {

	public static ProdutoEntity convertProdutoBoToEntity(ProdutoBo pBo) {
		ProdutoEntity pe = new ProdutoEntity();
		pe.setDescricao(pBo.getDescricao());
		pe.setId(pBo.getId());
		pe.setNome(pBo.getNome());
		pe.setValor(pBo.getValor());
		return pe;
	}

	public static ProdutoBo convertProdutoEntityToBo(ProdutoEntity pe) {
		ProdutoBo pBo = new ProdutoBo();
		pBo.setId(pe.getId());
		pBo.setDescricao(pe.getDescricao());
		pBo.setNome(pe.getNome());
		pBo.setValor(pe.getValor());
		return pBo;
	}

	public static List<ProdutoBo> convertListProdutoEntityToBo(List<ProdutoEntity> entities) {
		List<ProdutoBo> bos = new ArrayList<ProdutoBo>();
		for (ProdutoEntity pe : entities) {
			ProdutoBo pBo = new ProdutoBo();
			pBo.setId(pe.getId());
			pBo.setDescricao(pe.getDescricao());
			pBo.setNome(pe.getNome());
			pBo.setValor(pe.getValor());
			bos.add(pBo);
		}
		return bos;
	}

}
