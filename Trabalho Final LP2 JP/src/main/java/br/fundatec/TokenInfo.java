package br.fundatec;

public class TokenInfo {

	private static ThreadLocal<String> nome = new ThreadLocal<>();

	public static String getNome() {
		return nome.get();
	}

	public static void setNome(String nome) {
		TokenInfo.nome.set(nome);
	}
	
}
