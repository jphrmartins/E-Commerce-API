package space.indietech.produto;

import java.util.ArrayList;
import java.util.List;

import space.indietech.produto.dao.ProdutoEntity;
import space.indietech.produto.service.ProdutoBO;
import space.indietech.produto.web.ProdutoDTO;

public class ProdutoConvert {
	public List<ProdutoBO> convertEntityBO(List<ProdutoEntity> entities) {
		List<ProdutoBO> bos = new ArrayList<>();
		for (ProdutoEntity entity : entities) {
			ProdutoBO bo = new ProdutoBO();
			bo.setCodigo(entity.getCodigo());
			bo.setNome(entity.getNome());

			double lucro = entity.getPerc_lucro() / 100 + entity.getCusto();
			bo.setValor(entity.getCusto() + lucro);

			bos.add(bo);
		}
		return bos;

	}

	public List<ProdutoDTO> convertBODTO(List<ProdutoBO> bo) {
		List<ProdutoDTO> dtos = new ArrayList<>();
		for (ProdutoBO produtoBO : bo) {
			ProdutoDTO dto = new ProdutoDTO();
			dto.setCodigo(produtoBO.getCodigo());
			dto.setNome(produtoBO.getNome());
			dto.setValor(produtoBO.getValor());

			dtos.add(dto);
		}
		return dtos;
	}

}
