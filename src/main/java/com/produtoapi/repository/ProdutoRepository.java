package com.produtoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.produtoapi.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	//Jpa vai administrar o tipo da entidade (Produto) 
	//crio essa interface para conseguir utilizar os métodos lá na classe Service
}
