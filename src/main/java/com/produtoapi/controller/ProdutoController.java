package com.produtoapi.controller;

import java.util.List;
import java.util.Optional; //pode retornar null, assim evita nullPointerException

import org.springframework.web.bind.annotation.*;

import com.produtoapi.model.Produto;
import com.produtoapi.service.ProdutoService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*") //permite requisições de qualquer origem. Mas pode ser colocado um domínio específico de onde pode ser aceito requisições
@RestController //define que é essa classe que responde as requisições HTTP
@RequestMapping("/produtos") //significa uma rota http://localhost:8080/produtos
public class ProdutoController {
	
	private final ProdutoService produtoService;

	ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	
	@GetMapping //significa que a rota é de buscar algo
	public List<Produto> listarTodos() {
		return produtoService.listarTodos();
	}
	
	@GetMapping("/{id}")
	public Optional<Produto> buscarPorId(@PathVariable Long id) {
		return produtoService.findById(id);
	}
	
	@PostMapping //significa que a rota é de salvar algo
	public Produto salvar(@RequestBody @Valid Produto produto) { //@RequestBody quer dizer que deve vir informações de produto no corpo da solicitação
		return produtoService.salvar(produto);
	}
	
	@PostMapping("/salvarLista")
	public List<Produto> salvarLista(@RequestBody List<Produto> produtos) {
		return produtoService.salvarLista(produtos);
	}
	
	@PutMapping("/{id}")
	public Produto atualizar(@PathVariable Long id, @RequestBody Produto produto) { //@PathVariable quer dizer que é a informação que está na própria rota
		return produtoService.atualizar(id, produto);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		produtoService.deletar(id);
	}
	
	@GetMapping("/totalProdutos")
	public Long totalDeProdutos() {
		return produtoService.count();
	}
	
	//perceba que 3 rotas recebem o mesmo argumento (id), o que muda é o tipo de HTTP que será solicitado
	
	//============= BUSCAS DETALHADAS POR NOME DE PRODUTO =============
	@GetMapping("/buscarPorNome")
	public List<Produto> buscarPorNome(@RequestParam String valor) {
		return produtoService.findByNome(valor);
	}
	
	@GetMapping("/buscarPorNomeContendo")
	public List<Produto> buscarPorNomeContendo(@RequestParam String valor) {
		return produtoService.findByNomeContaining(valor);
	}
	
	@GetMapping("/buscarPorNomeEStatus")
	public List<Produto> buscarPorNomeEStatus(@RequestParam String nome, @RequestParam String status) {
		return produtoService.findByNomeAndStatus(nome, status);
	}
	//http://localhost:8080/produtos/buscarPorNomeEStatus?nome=Televisão&status=disponível
	
	@GetMapping("/buscarPorNomeComecandoCom")
	public List<Produto> buscarPorNomeComecandoCom(@RequestParam String prefix) {
		return produtoService.findByNomeStartingWith(prefix);
	}
	//http://localhost:8080/produtos/buscarPorNomeComecandoCom?prefix=C
	
	@GetMapping("/buscarPorNomeTerminandoCom")
	public List<Produto> buscarPorNomeTerminandoCom(@RequestParam String suffix) {
		return produtoService.findByNomeEndingWith(suffix);
	}
	//http://localhost:8080/produtos/buscarPorNomeTerminandoCom?suffix=Gamer
	
	//============= BUSCAS DETALHADAS POR PREÇO DE PRODUTO =============
	@GetMapping("/buscarPorPreco")
	public List<Produto> buscarPorPreco(@RequestParam Double valor) {
		return produtoService.findByPreco(valor);
	}
	//http://localhost:8080/produtos/buscarPorPreco?valor=40
	
	@GetMapping("/buscarPorPrecoMaiorQue")
	public List<Produto> buscarPorPrecoMaiorQue(@RequestParam Double valor) {
		return produtoService.findByPrecoGreaterThan(valor);
	}
	//http://localhost:8080/produtos/buscarPorPrecoMaiorQue?valor=40
	
	@GetMapping("/buscarPorPrecoMenorQue")
	public List<Produto> buscarPorPrecoMenorQue(@RequestParam Double valor) {
		return produtoService.findByPrecoLessThan(valor);
	}
	//http://localhost:8080/produtos/buscarPorPrecoMenorQue?valor=1000
	
	@GetMapping("/buscarTotalPreco")
	public Double buscarTotalPreco() {
		return produtoService.findTotalPreco();
	}
	
	@GetMapping("/buscarValorTotalDeEstoque")
	public Double buscarValorTotalDeEstoque() {
		return produtoService.findValorTotalDeEstoque();
	}
	
	//============= BUSCAS DETALHADAS POR STATUS DE PRODUTO =============
	
	@GetMapping("/buscarPorStatus")
	public List<Produto> buscarPorStatus(@RequestParam(required = false) String status) { //required é, por padrão, true. Se você deixa falso quer dizer que é opcional passar o parâmetro
		return produtoService.findByStatus(status);										  //também poderia ser (defaultValue = "Disponível")
	}
	
	@GetMapping("/buscarPorStatusNulo")
	public List<Produto> buscarPorStatusNulo() {
		return produtoService.findByStatusIsNull();
	}
}
