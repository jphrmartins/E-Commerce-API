package br.fundatec.magojota.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fundatec.magojota.converter.ConverterCarrinho;
import br.fundatec.magojota.converter.ConverterProduto;
import br.fundatec.magojota.service.MagoJotaService;
import br.fundatec.magojota.service.ProdutoBo;

@RestController
@RequestMapping(value = "/magojota")
public class MagoJotaRest {

	private MagoJotaService mjs;

	@Autowired
	public MagoJotaRest(MagoJotaService mjs) {
		this.mjs = mjs;
	}
	
	@GetMapping("/produtos")
	public ResponseEntity<List<ProdutoDTO>> getProdutos(){
		List<ProdutoBo> bos = mjs.getProdutos();
		return ResponseEntity.ok(ConverterProduto.convertListBoToDTO(bos));
	}
	@GetMapping("/produtos/{id}")
	public ResponseEntity<ProdutoDTO> getProdutoId(@PathVariable long id){
		try {
			ProdutoDTO dto = ConverterProduto.convertBoToDTO(mjs.getProdutoId(id));
			return ResponseEntity.ok(dto);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/produtos")
	public ResponseEntity<ProdutoDTO> addProduto(@RequestBody ProdutoDTO pdto){
		ProdutoBo bo = ConverterProduto.convertDtoToBO(pdto);
		bo = mjs.save(bo);
		return ResponseEntity.ok(ConverterProduto.convertBoToDTO(bo));
	}
	@PutMapping("/produtos/{id}")
	public ResponseEntity<ProdutoDTO> attProduto(@RequestBody ProdutoDTO pdto, @PathVariable("id") long id){
		ProdutoBo bo = ConverterProduto.convertDtoToBO(pdto);
		try {
			bo = mjs.update(bo, id);
			return ResponseEntity.ok(ConverterProduto.convertBoToDTO(bo));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/produtos/{id}")
	public ResponseEntity<String> deleteProduto(@PathVariable("id") long id){
		mjs.delete(id);
		return ResponseEntity.ok("Produto excluido com sucesso");
	}
	@GetMapping("carrinhos")
	public ResponseEntity<CarrinhoDTO> getCarrinho(){
		CarrinhoDTO dto = ConverterCarrinho.convertBoToDTO(mjs.getCarrinho());
		return ResponseEntity.ok(dto);
	}
	@PostMapping("carrinhos")
	public ResponseEntity<String> addProduto(@RequestBody ProdutoDTOCarrinho prodDTOcar){
		try {
			mjs.addProduto(prodDTOcar.getId());
			return ResponseEntity.ok("Adicionado com sucesso");
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		
		
	}
	
	
}
