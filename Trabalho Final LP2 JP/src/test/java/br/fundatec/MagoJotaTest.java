package br.fundatec;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class MagoJotaTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void crudProduto() throws Exception {
		getListaProdutoVazia();
		creatProduto();
		getLisaProdutoPopulada();
		getProdutoPorId();
		atualizaProduto();
		getProdutoAtualizado();
		deletaProduto();
		getListaProdutoVazia();
		// ==========================================================================================//
		getCarrinhoVazio();
		creatProdutoParaCarrinho();
		addProdutoCarrinho();
		getCarrinhoCheio();
	}

	private void addProdutoCarrinho() throws Exception {
		String location = "/magojota/carrinho";
		String json = "{" + "\"id\" : 2 " + "}";
		mockMvc.perform(post(location).header("Content-type", "application/json").header("token",
				"eyJhbGciOiJIUzI1NiJ9.eyJjcmVhdGVkIjoiMjAxNy0xMi0xMSAyMToyMzoxNC44ODgiLCJ1c3VhcmlvIjoiam9hbyBwZWRybyJ9.rlYchUQ0UVZ0HPFYSQMXvt1iMJaCgZeFxQJdXKXh_3k")
				.content(json))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").value("Adicionado com sucesso"));
	}

	private void creatProdutoParaCarrinho() throws Exception {
		String location = "/magojota/produtos";
		String json = "{" + "\"nome\":\"notebook\"," + "\"valor\":5000,"
				+ "\"descricao\":\"2 gibas, processador: intel 2 cores, selo anti-arthur de qualidade, vem com o godzila giroflex\""
				+ "}";
		mockMvc.perform(post(location).header("Content-type", "application/json").header("token",
				"eyJhbGciOiJIUzI1NiJ9.eyJjcmVhdGVkIjoiMjAxNy0xMi0xMSAxOTowMjo0NS45NjMiLCJ1c3VhcmlvIjoiYWRtIn0.jJXf9gU5JS9YyFBnWJGandgyuWEIxuNT8Uqr4WPe1n8")
				.content(json)).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(2))
				.andExpect(jsonPath("$.nome").value("notebook")).andExpect(jsonPath("$.valor").value(5000))
				.andExpect(jsonPath("$.descricao").value(
						"2 gibas, processador: intel 2 cores, selo anti-arthur de qualidade, vem com o godzila giroflex"));
	}

	@Test
	public void crudErrorProduto() throws Exception {
		getProdutoPorIdInexistente();
		addProdutoInvalido();
		updateProdutoInexistente();
	}

	private void getCarrinhoCheio() throws Exception {
		String location = "/magojota/carrinho";
		mockMvc.perform(get(location).header("Content-type", "application/json").header("token",
				"eyJhbGciOiJIUzI1NiJ9.eyJjcmVhdGVkIjoiMjAxNy0xMi0xMSAyMToyMzoxNC44ODgiLCJ1c3VhcmlvIjoiam9hbyBwZWRybyJ9.rlYchUQ0UVZ0HPFYSQMXvt1iMJaCgZeFxQJdXKXh_3k"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.dono").value("joao pedro"))
				.andExpect(jsonPath("$.produtos[0]").exists()).andExpect(jsonPath("$.produtos[0].id").value(2))
				.andExpect(jsonPath("$.produtos[0].nome").value("notebook"))
				.andExpect(jsonPath("$.produtos[0].valor").value(5000))
				.andExpect(jsonPath("$.produtos[0].descricao").value(
						"2 gibas, processador: intel 2 cores, selo anti-arthur de qualidade, vem com o godzila giroflex"));
	}

	private void getCarrinhoVazio() throws Exception {
		String location = "/magojota/carrinho";
		mockMvc.perform(get(location).header("Content-type", "application/json").header("token",
				"eyJhbGciOiJIUzI1NiJ9.eyJjcmVhdGVkIjoiMjAxNy0xMi0xMSAyMToyMzoxNC44ODgiLCJ1c3VhcmlvIjoiam9hbyBwZWRybyJ9.rlYchUQ0UVZ0HPFYSQMXvt1iMJaCgZeFxQJdXKXh_3k"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.dono").value("joao pedro"))
				.andExpect(jsonPath("$.produtos[0]").doesNotExist());
	}

	private void updateProdutoInexistente() throws Exception {
		String location = "/magojota/produtos/1";
		String json = "{" + "\"nome\":\"açai\"," + "\"valor\":10," + "\"descricao\":\"AGORA SEM Gosto de terra\"" + "}";
		mockMvc.perform(put(location).header("Content-type", "application/json").header("token",
				"eyJhbGciOiJIUzI1NiJ9.eyJjcmVhdGVkIjoiMjAxNy0xMi0xMSAxOTowMjo0NS45NjMiLCJ1c3VhcmlvIjoiYWRtIn0.jJXf9gU5JS9YyFBnWJGandgyuWEIxuNT8Uqr4WPe1n8")
				.content(json)).andExpect(status().isNotFound());
	}

	private void addProdutoInvalido() throws Exception {
		String location = "/magojota/produtos";
		String json = "{" + "\"nome\":\"\"," + "\"valor\":0," + "\"descricao\":\"Gosto de terra\"" + "}";
		mockMvc.perform(post(location).header("Content-type", "application/json").header("token",
				"eyJhbGciOiJIUzI1NiJ9.eyJjcmVhdGVkIjoiMjAxNy0xMi0xMSAxOTowMjo0NS45NjMiLCJ1c3VhcmlvIjoiYWRtIn0.jJXf9gU5JS9YyFBnWJGandgyuWEIxuNT8Uqr4WPe1n8")
				.content(json)).andExpect(status().isBadRequest());
	}

	private void getProdutoPorIdInexistente() throws Exception {
		String location = "/magojota/produtos/1";
		mockMvc.perform(get(location).header("Content-type", "application/json")).andExpect(status().isNotFound());
	}

	private void deletaProduto() throws Exception {
		String location = "/magojota/produtos/1";
		mockMvc.perform(delete(location).header("Content-type", "application/json").header("token",
				"eyJhbGciOiJIUzI1NiJ9.eyJjcmVhdGVkIjoiMjAxNy0xMi0xMSAxOTowMjo0NS45NjMiLCJ1c3VhcmlvIjoiYWRtIn0.jJXf9gU5JS9YyFBnWJGandgyuWEIxuNT8Uqr4WPe1n8"))
				.andExpect(status().isOk()).andExpect(jsonPath("$").value("Produto excluido com sucesso"));
	}

	private void getProdutoAtualizado() throws Exception {
		String location = "/magojota/produtos/1";
		mockMvc.perform(get(location).header("Content-type", "application/json")).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.nome").value("açai"))
				.andExpect(jsonPath("$.valor").value(10))
				.andExpect(jsonPath("$.descricao").value("AGORA SEM Gosto de terra"));
	}

	private void atualizaProduto() throws Exception {
		String location = "/magojota/produtos/1";
		String json = "{" + "\"nome\":\"açai\"," + "\"valor\":10," + "\"descricao\":\"AGORA SEM Gosto de terra\"" + "}";
		mockMvc.perform(put(location).header("Content-type", "application/json").header("token",
				"eyJhbGciOiJIUzI1NiJ9.eyJjcmVhdGVkIjoiMjAxNy0xMi0xMSAxOTowMjo0NS45NjMiLCJ1c3VhcmlvIjoiYWRtIn0.jJXf9gU5JS9YyFBnWJGandgyuWEIxuNT8Uqr4WPe1n8")

				.content(json)).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.nome").value("açai")).andExpect(jsonPath("$.valor").value(10))
				.andExpect(jsonPath("$.descricao").value("AGORA SEM Gosto de terra"));
	}

	private void getProdutoPorId() throws Exception {
		String location = "/magojota/produtos/1";
		mockMvc.perform(get(location).header("Content-type", "application/json")).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.nome").value("açai"))
				.andExpect(jsonPath("$.valor").value(10)).andExpect(jsonPath("$.descricao").value("Gosto de terra"));

	}

	private void getLisaProdutoPopulada() throws Exception {
		String location = "/magojota/produtos";
		mockMvc.perform(get(location).header("Content-type", "application/json")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0]").exists()).andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].nome").value("açai")).andExpect(jsonPath("$[0].valor").value(10))
				.andExpect(jsonPath("$[0].descricao").value("Gosto de terra"));
	}

	private void creatProduto() throws Exception {
		String location = "/magojota/produtos";
		String json = "{" + "\"nome\":\"açai\"," + "\"valor\":10," + "\"descricao\":\"Gosto de terra\"" + "}";
		mockMvc.perform(post(location).header("Content-type", "application/json").header("token",
				"eyJhbGciOiJIUzI1NiJ9.eyJjcmVhdGVkIjoiMjAxNy0xMi0xMSAxOTowMjo0NS45NjMiLCJ1c3VhcmlvIjoiYWRtIn0.jJXf9gU5JS9YyFBnWJGandgyuWEIxuNT8Uqr4WPe1n8")
				.content(json)).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.nome").value("açai")).andExpect(jsonPath("$.valor").value(10))
				.andExpect(jsonPath("$.descricao").value("Gosto de terra"));
	}

	private void getListaProdutoVazia() throws Exception {
		String location = "/magojota/produtos";
		mockMvc.perform(get(location).header("Content-type", "application/json")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0]").doesNotExist());

	}
}
