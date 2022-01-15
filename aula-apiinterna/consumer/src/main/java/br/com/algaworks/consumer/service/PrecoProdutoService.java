package br.com.algaworks.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.algaworks.consumer.model.ProdutoComPreco;
import reactor.core.publisher.Mono;

@Service
public class PrecoProdutoService {

	@Autowired
	private WebClient webClientProdutos;
	
	@Autowired
	private WebClient webClientPrecos;
	
	public ProdutoComPreco obterPorCodigoParalelo(Long codigoProduto) {
		//se seu implementar apenas esse método o ProdutoComPreco retorna com o preço null
		Mono<ProdutoComPreco> monoProduto = this.webClientProdutos
			.method(HttpMethod.GET) //conf
			.uri("/produtos/{codigo}", codigoProduto) //conf
			.retrieve() //realmente dispara o métod
			.bodyToMono(ProdutoComPreco.class);//ele transforma o response do tipo Produto para o tipo ProdutoComPreco
		                                       //e ignora o campo descrição do produto e o preço do ProdutoComPreco
		
		//se eu implementar apenas esse o ProdutoComPreco retorna com o produto null
		Mono<ProdutoComPreco> monoPreco = this.webClientPrecos
				.method(HttpMethod.GET)
				.uri("/precos/{codigo}", codigoProduto)
				.retrieve()
				.bodyToMono(ProdutoComPreco.class);
		
		/* Seguindo esse passos abaixo e criaria o meu objeto completo
		 * Como eu faço duas requisições a api é melhor não utilizar o block de forma independente e utilizar o zip
		 * ProdutoComPreco produto = monoProduto.block();                esse block faz o fechamento com o objeto pronto
		 * ProdutoComPreco preco = monoPreco.block();
		 * produto.setPreco(preco.getPreco());
		 */											//t1         //t2
		ProdutoComPreco produtoComPreco = Mono.zip(monoProduto, monoPreco)
				.map(tuple -> {												//o map me dá acesso aos tributos dos monos indicados no zip
						tuple.getT1().setPreco(tuple.getT2().getPreco());
						return tuple.getT1();
				}).block();

		return produtoComPreco;
	}
	
	public ProdutoComPreco obterPorCodigoSincrono(Long codigoProduto) {

		Mono<ProdutoComPreco> monoProduto = this.webClientProdutos
			.method(HttpMethod.GET)
			.uri("/produtos/{codigo}", codigoProduto)
			.retrieve()
			.bodyToMono(ProdutoComPreco.class);
	
		Mono<ProdutoComPreco> monoPreco = this.webClientPrecos
				.method(HttpMethod.GET)
				.uri("/precos/{codigo}", codigoProduto)
				.retrieve()
				.bodyToMono(ProdutoComPreco.class);
		
		ProdutoComPreco produto = monoProduto.block();
		ProdutoComPreco preco = monoPreco.block();

		produto.setPreco(preco.getPreco());

		return produto;
	}
	
	public ProdutoComPreco criar(ProdutoComPreco produtoComPreco) {

		Mono<ProdutoComPreco> monoProduto = 
				this.webClientProdutos
					.post()
					.uri("/produtos")
					.body(BodyInserters.fromValue(produtoComPreco))
					.retrieve()
					.bodyToMono(ProdutoComPreco.class);

		return monoProduto.block();
	}
}
