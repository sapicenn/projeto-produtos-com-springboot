package com.produtoapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotBlank(message = "Informe um nome")
	private String nome;
	@NotNull(message = "Informe uma quantidade")
	private Integer quantidade;
	@NotNull(message = "Informe um preço")
	@Positive(message = "O valor deve ser maior que zero")
	private Double preco;
	private String status;
	
	public Produto(String nome, Integer quantidade, Double preco, String status) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.preco = preco;
		this.status = status;
	}
	
	public Produto() {}
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Integer getQuantidade() {
		return this.quantidade;
	}
	
	public void setQuantidade(Integer qtde) {
		this.quantidade = qtde;
	}
	
	public Double getPreco() {
		return this.preco;
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

}
