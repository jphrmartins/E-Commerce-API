package br.fundatec.magojota.web;

import java.util.List;

public class CarrinhoDTO {

	private String dono;
	private List<ProdutoDTO> produtos;

	public String getDono() {
		return dono;
	}

	public void setDono(String dono) {
		this.dono = dono;
	}

	public List<ProdutoDTO> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoDTO> produtos) {
		this.produtos = produtos;
	}

}
