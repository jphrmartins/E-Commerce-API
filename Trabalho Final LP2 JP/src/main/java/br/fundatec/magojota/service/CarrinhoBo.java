package br.fundatec.magojota.service;

import java.util.List;

public class CarrinhoBo {

	private String dono;
	private List<ProdutoBo> produtos;

	public String getDono() {
		return dono;
	}

	public void setDono(String dono) {
		this.dono = dono;
	}

	public List<ProdutoBo> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoBo> produtos) {
		this.produtos = produtos;
	}

}
