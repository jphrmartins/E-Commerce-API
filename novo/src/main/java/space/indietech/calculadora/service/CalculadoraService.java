package space.indietech.calculadora.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import space.indietech.calculadora.convert.CalculadoraConvert;
import space.indietech.calculadora.dao.CalculadoraAppDao;
import space.indietech.calculadora.dao.CalculadoraDao;
import space.indietech.calculadora.dao.CalculadoraEntity;



@Component
public class CalculadoraService {
	private CalculadoraAppDao appDao; 
	private CalculadoraDao cDao;
	
	@Autowired
	public CalculadoraService(CalculadoraAppDao appDao, CalculadoraDao cdao) {
		this.appDao = appDao;
		this.cDao = cdao;
	}
	public CalculadoraBO calcularbo(CalculadoraBO calbo) {
		CalculadoraEntity ce = cDao.getcalculo(calbo.getExpressao());
		if(ce != null) {
			return new CalculadoraConvert().convertEntityBO(ce);
		}else{
			double resultado = appDao.calcularbo(calbo.getExpressao());
			calbo.setValor(resultado);
			cDao.addCalculo(new CalculadoraConvert().convertBOEntity(calbo));
			return calbo;	
		}
	}
	public List<CalculadoraBO> getBanco() {
		return new CalculadoraConvert().convertListEntityBO(cDao.getBanco());
	}

//		CalculadoraEntity calc = cDao.getcalculo(calbo.getExpressao());
//		if (calc != null) {
//			return new CalculadoraConvert().convertEntityBO(calc);
//		}
//		if (calbo.getExpressao().contains("+")) {
//			String[] expressao = calbo.getExpressao().split("\\+");
//			double valor1 = Double.parseDouble(expressao[0]);
//			double valor2 = Double.parseDouble(expressao[1]);
//			calbo.setValor(valor1 + valor2);
//			cDao.addCalculo(new CalculadoraConvert().convertBOEntity(calbo));
//			return calbo;
//
//		} else if (calbo.getExpressao().contains("-")) {
//			String[] expressao = calbo.getExpressao().split("\\-");
//			double valor1 = Double.parseDouble(expressao[0]);
//			double valor2 = Double.parseDouble(expressao[1]);
//			calbo.setValor(valor1 - valor2);
//			cDao.addCalculo(new CalculadoraConvert().convertBOEntity(calbo));
//			return calbo;
//
//		} else if (calbo.getExpressao().contains("*")) {
//			String[] expressao = calbo.getExpressao().split("\\*");
//			double valor1 = Double.parseDouble(expressao[0]);
//			double valor2 = Double.parseDouble(expressao[1]);
//			calbo.setValor(valor1 * valor2);
//			cDao.addCalculo(new CalculadoraConvert().convertBOEntity(calbo));
//			return calbo;
//
//		} else if (calbo.getExpressao().contains("/")) {
//			String[] expressao = calbo.getExpressao().split("\\/");
//			double valor1 = Double.parseDouble(expressao[0]);
//			double valor2 = Double.parseDouble(expressao[1]);
//			calbo.setValor(valor1 / valor2);
//			cDao.addCalculo(new CalculadoraConvert().convertBOEntity(calbo));
//			return calbo;
//
//		}
//		throw new RuntimeException("Botar algo valido !");
//	}

}
