package space.indietech.calculadora.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import space.indietech.calculadora.convert.CalculadoraConvert;
import space.indietech.calculadora.service.CalculadoraBO;
import space.indietech.calculadora.service.CalculadoraService;

	@RestController
	@RequestMapping(value = "/calculadora")
	public class CalculadoraRest {

		private CalculadoraService calculadoraService;

		@Autowired
		public CalculadoraRest(CalculadoraService calculadoraService) {
			this.calculadoraService = calculadoraService;
		}
		@PutMapping
		public ResponseEntity<String> calcular(@RequestBody CalculadoraDTORecebe cal){
			/*CalculadoraBO bo = CalculadoraConvert.convertDTORecebeBO(cal);
			bo = calculadoraService.calcularbo(bo);
			CalculadoraDTO calculadoraDTO = CalculadoraConvert.convertBODTOenvia(bo);*/
			return ResponseEntity.ok("Abacaxi");
		}
		@GetMapping("/admin")
		public ResponseEntity<List<CalculadoraListaDTO>> lista(){
			List<CalculadoraBO> bo = calculadoraService.getBanco();
			List<CalculadoraListaDTO> listaBanco = new CalculadoraConvert().convertListaBo(bo);
			return ResponseEntity.ok(listaBanco);
		}
}
