package com.produtoapi.repository;

import java.util.List;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.produtoapi.model.Produto;

//interface de projeção
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	//Jpa vai administrar o tipo da entidade (Produto) 
	//crio essa interface para conseguir utilizar os métodos lá na classe Service
	
	List<Produto> findByNome(String nome);
	List<Produto> findByNomeContaining(String nome);
	List<Produto> findByNomeAndStatus(String nome, String status);
	List<Produto> findByNomeStartingWith(String prefix);
	List<Produto> findByNomeEndingWith(String suffix);
	
	List<Produto> findByPreco(BigDecimal preco);
	List<Produto> findByPrecoGreaterThan(BigDecimal preco);
	List<Produto> findByPrecoLessThan(BigDecimal preco);
	
	@Query("SELECT SUM(p.preco) FROM Produto p") //soma preços sem considerar quantidades
	BigDecimal findTotalPreco();
	
	@Query("SELECT SUM(p.preco * p.quantidade) FROM Produto p")
	BigDecimal findValorTotalDeEstoque();
	
	List<Produto> findByStatus(String status);
	List<Produto> findByStatusIsNull();
}
