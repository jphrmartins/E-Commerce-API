package space.indietech.calculadora.convert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import space.indietech.calculadora.dao.CalculadoraEntity;
import space.indietech.calculadora.service.CalculadoraBO;
import space.indietech.calculadora.web.CalculadoraDTO;
import space.indietech.calculadora.web.CalculadoraDTORecebe;
import space.indietech.calculadora.web.CalculadoraListaDTO;

public class CalculadoraConvert {
	public CalculadoraBO convertEntityBO(CalculadoraEntity entity) {
		CalculadoraBO bo = new CalculadoraBO();
		bo.setExpressao(entity.getExpressao());
		bo.setValor(entity.getValor());
		return bo;
	}

	public CalculadoraEntity convertBOEntity(CalculadoraBO bo) {
		CalculadoraEntity entity = new CalculadoraEntity();
		entity.setExpressao(bo.getExpressao());
		entity.setValor(bo.getValor());
		entity.setCriado_em(new Date());
		return entity;
	}
	public static CalculadoraBO convertDTORecebeBO(CalculadoraDTORecebe caldto) {
		CalculadoraBO bo = new CalculadoraBO();
		bo.setExpressao(caldto.getCalculo());
		return bo;
		
	}
	public static CalculadoraDTO convertBODTOenvia(CalculadoraBO bo) {
		CalculadoraDTO dto = new CalculadoraDTO();
		dto.setValor(bo.getValor());
		return dto;
		
	}

	public List<CalculadoraListaDTO> convertListaBo(List<CalculadoraBO> bo) {
		List<CalculadoraListaDTO> dtos = new ArrayList<>();
		for(CalculadoraBO calculadoraBO : bo) {
			CalculadoraListaDTO listaDTO = new CalculadoraListaDTO();
			listaDTO.setExpressao(calculadoraBO.getExpressao());
			listaDTO.setValor(calculadoraBO.getValor());
			
			dtos.add(listaDTO);
		}
		
		return dtos;
	}
		public List<CalculadoraBO> convertListEntityBO(List<CalculadoraEntity> entitys){
			List<CalculadoraBO> bos = new ArrayList<>();
			for(CalculadoraEntity calculadoraEntity : entitys) {
				CalculadoraBO listaBOS = new CalculadoraBO();
				listaBOS.setExpressao(calculadoraEntity.getExpressao());
				listaBOS.setValor(calculadoraEntity.getValor());
				
				bos.add(listaBOS);
			}
			return bos;
		}
	
}
