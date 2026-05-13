package com.produtoapi.runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.produtoapi.model.Produto;

@Component
public class StartupRestClientRunner implements CommandLineRunner { //executa uma chamada quando a aplicação é inicializada

	@Override
	public void run(String... args) throws Exception { //(String...args) é uma varargs, quer dizer que o parâmetro pode variar
		System.out.println("RestClient sendo executado");
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/produtos";
		
		Produto[] produtosArray = restTemplate.getForObject(url, Produto[].class);
		List<Produto> produtos = Arrays.asList(produtosArray);
		
		produtos.forEach(produto -> System.out.println("ID: "+produto.getId()+". "+produto.getNome()+": "+produto.getPreco()));
	} 
	
}
