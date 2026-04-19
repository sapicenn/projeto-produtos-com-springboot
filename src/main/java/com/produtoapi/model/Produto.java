package com.produtoapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotEmpty(message = "Informe um nome.")
	private String nome;
	
	private int quantidade;
	private double preco;
	private String status;
	
	public Produto(String nome, int quantidade, double preco, String status) {
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
	
	public int getQuantidade() {
		return this.quantidade;
	}
	
	public double getPreco() {
		return this.preco;
	}
	
	public String getStatus() {
		return this.status;
	}

}
