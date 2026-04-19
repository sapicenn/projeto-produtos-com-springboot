package com.produtoapi.service;

import java.util.List;
import java.util.Optional; //pode retornar null, assim evita nullPointerException

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.produtoapi.repository.ProdutoRepository;
import com.produtoapi.model.Produto;

@Service
public class ProdutoService {
	
	@Autowired //isso quer dizer que o spring vai gerenciar o ciclo de vida dessa variável
	private ProdutoRepository produtoRepository;
	
	public List<Produto> listarTodos() {
		return produtoRepository.findAll();
	}
	
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	//para testar é [{produto}, {produto}, {produto}]
	public List<Produto> salvarLista(List<Produto> produtos) {
		return produtoRepository.saveAll(produtos);
	}
	
	public void deletar(Long id) {
		produtoRepository.deleteById(id);
	}
	
	public Produto atualizar(Long id, Produto produto) {
		if(produtoRepository.existsById(id)) {
			produto.setId(id);
			return produtoRepository.save(produto);
		} else {
			throw new RuntimeException("Produto não encontrado");
		}
	}
	
	public Optional<Produto> findById(Long id) {
		return produtoRepository.findById(id);
	}
}
