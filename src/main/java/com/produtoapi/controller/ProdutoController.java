package com.produtoapi.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.produtoapi.dto.ProdutoRequestDTO;
import com.produtoapi.dto.ProdutoResponseDTO;
import com.produtoapi.mapper.ProdutoMapper;
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
	public ResponseEntity<List<ProdutoResponseDTO>> listarTodos() {
		List<ProdutoResponseDTO> produtos = produtoService.listarTodos()
			.stream()
			.map(ProdutoMapper::toResponseDTO)
			.toList();

		return ResponseEntity.ok(produtos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
		return produtoService.findById(id)
			.map(ProdutoMapper::toResponseDTO)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping //significa que a rota é de salvar algo
	public ResponseEntity<ProdutoResponseDTO> salvar(@RequestBody @Valid ProdutoRequestDTO dto) {
		Produto produto = ProdutoMapper.toEntity(dto);
		Produto produtoSalvo = produtoService.salvar(produto);

		return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoMapper.toResponseDTO(produtoSalvo));
	}
	
	@PostMapping("/salvarLista")
	public List<Produto> salvarLista(@RequestBody List<Produto> produtos) { //@RequestBody quer dizer que deve vir informações de produto no corpo da solicitação
		return produtoService.salvarLista(produtos);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoResponseDTO> atualizar (
		@PathVariable Long id, @RequestBody @Valid ProdutoRequestDTO dto) {

			if(!produtoService.existsById(id)) {
				return ResponseEntity.notFound().build();
			}

			Produto produto = ProdutoMapper.toEntity(dto);
			produto.setId(id);

			Produto produtoAtualizado = produtoService.salvar(produto);

			return ResponseEntity.ok(ProdutoMapper.toResponseDTO(produtoAtualizado));

		}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!produtoService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		produtoService.deletar(id);
		return ResponseEntity.noContent().build();
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
	public List<Produto> buscarPorPreco(@RequestParam BigDecimal valor) {
		return produtoService.findByPreco(valor);
	}
	//http://localhost:8080/produtos/buscarPorPreco?valor=40
	
	@GetMapping("/buscarPorPrecoMaiorQue")
	public List<Produto> buscarPorPrecoMaiorQue(@RequestParam BigDecimal valor) {
		return produtoService.findByPrecoGreaterThan(valor);
	}
	//http://localhost:8080/produtos/buscarPorPrecoMaiorQue?valor=40
	
	@GetMapping("/buscarPorPrecoMenorQue")
	public List<Produto> buscarPorPrecoMenorQue(@RequestParam BigDecimal valor) {
		return produtoService.findByPrecoLessThan(valor);
	}
	//http://localhost:8080/produtos/buscarPorPrecoMenorQue?valor=1000
	
	@GetMapping("/buscarTotalPreco")
	public BigDecimal buscarTotalPreco() {
		return produtoService.findTotalPreco();
	}
	
	@GetMapping("/buscarValorTotalDeEstoque")
	public BigDecimal buscarValorTotalDeEstoque() {
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
