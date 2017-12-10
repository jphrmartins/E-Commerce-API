package br.fundatec.magojota.converter;

import java.util.ArrayList;
import java.util.List;

import br.fundatec.magojota.dao.ProdutoEntity;
import br.fundatec.magojota.service.ProdutoBo;
import br.fundatec.magojota.web.ProdutoDTO;

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

	public static List<ProdutoDTO> convertListBoToDTO(List<ProdutoBo> bos) {
		List<ProdutoDTO> dtos = new ArrayList<ProdutoDTO>();
		for (ProdutoBo produtoBo : bos) {
			ProdutoDTO pdto = new ProdutoDTO();
			pdto.setId(produtoBo.getId());
			pdto.setDescricao(produtoBo.getDescricao());
			pdto.setNome(produtoBo.getNome());
			pdto.setValor(produtoBo.getValor());
			dtos.add(pdto);
		}
		return dtos;
	}

	public static ProdutoBo convertDtoToBO(ProdutoDTO pdto) {
		ProdutoBo bo = new ProdutoBo();
		bo.setId(pdto.getId());
		bo.setDescricao(pdto.getDescricao());
		bo.setNome(pdto.getNome());
		bo.setValor(pdto.getValor());
		return bo;
	}

	public static ProdutoDTO convertBoToDTO(ProdutoBo bo) {
		ProdutoDTO pdto = new ProdutoDTO();
		pdto.setId(bo.getId());
		pdto.setDescricao(bo.getDescricao());
		pdto.setNome(bo.getNome());
		pdto.setValor(bo.getValor());
		return pdto;
	}

	public static List<ProdutoEntity> convertListProdutoBoToEntity(List<ProdutoBo> produtos) {
		List<ProdutoEntity> entitys = new ArrayList<>();
		for (ProdutoBo produtoBo : produtos) {
			entitys.add(convertProdutoBoToEntity(produtoBo));
		}
		return entitys;
	}

}
