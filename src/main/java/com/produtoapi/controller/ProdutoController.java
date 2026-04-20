package com.produtoapi.controller;

import java.util.List;
import java.util.Optional; //pode retornar null, assim evita nullPointerException

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.produtoapi.model.Produto;
import com.produtoapi.service.ProdutoService;

@CrossOrigin(origins = "*") //permite requisições de qualquer origem. Mas pode ser colocado um domínio específico de onde pode ser aceito requisições
@RestController //define que é essa classe que responde as requisições HTTP
@RequestMapping("/produtos") //significa uma rota http://localhost:8080/produtos
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping //significa que a rota é de buscar algo
	public List<Produto> listarTodos() {
		return produtoService.listarTodos();
	}
	
	@GetMapping("/{id}")
	public Optional<Produto> buscarPorId(@PathVariable Long id) {
		return produtoService.findById(id);
	}
	
	@PostMapping //significa que a rota é de salvar algo
	public Produto salvar(@RequestBody Produto produto) { //@RequestBody quer dizer que deve vir informações de produto no corpo da solicitação
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
	
	//perceba que 3 rotas recebem o mesmo argumento (id), o que muda é o tipo de HTTP que será solicitado
}
