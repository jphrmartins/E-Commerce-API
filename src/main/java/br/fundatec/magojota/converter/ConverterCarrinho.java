package br.fundatec.magojota.converter;

import java.util.ArrayList;
import java.util.List;

import br.fundatec.magojota.dao.CarrinhoEntity;
import br.fundatec.magojota.service.CarrinhoBo;
import br.fundatec.magojota.web.CarrinhoDTO;

public class ConverterCarrinho {

	public static CarrinhoBo convertEntityToBo(CarrinhoEntity entity) {
		CarrinhoBo bo = new CarrinhoBo();
		bo.setDono(entity.getDono());
		bo.setProdutos(ConverterProduto.convertListProdutoEntityToBo(entity.getProdutos()));
		return bo;
	}

	public static CarrinhoEntity convertBoToEntity(CarrinhoBo cBo) {
		CarrinhoEntity ce = new CarrinhoEntity();
		ce.setDono(cBo.getDono());
		ce.setProdutos(ConverterProduto.convertListProdutoBoToEntity(cBo.getProdutos()));
		return ce;
	}

	public static CarrinhoDTO convertBoToDTO(CarrinhoBo cbo) {
		CarrinhoDTO dto = new CarrinhoDTO();
		dto.setDono(cbo.getDono());
		dto.setProdutos(ConverterProduto.convertListBoToDTO(cbo.getProdutos()));
		return dto;
	}

	public static List<CarrinhoBo> convertListEntityToBo(List<CarrinhoEntity> carrinhos) {
		List<CarrinhoBo> bos = new ArrayList<CarrinhoBo>();
		for (CarrinhoEntity cEntity : carrinhos) {
			CarrinhoBo bo = new CarrinhoBo();
			bo.setDono(cEntity.getDono());
			bo.setProdutos(ConverterProduto.convertListProdutoEntityToBo(cEntity.getProdutos()));
			bos.add(bo);
		}
		return bos;
	}

}
