package com.produtoapi.service;

import com.produtoapi.repository.ProdutoRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

import com.produtoapi.model.Produto;

//import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock //simula o repository
    private ProdutoRepository produtoRepository;

    @InjectMocks //injeta o repository no service
    private ProdutoService produtoService;

    @Test 
    void deveListarTodosOsProdutos() {
        Produto produto1 = new Produto("Notebook", 10, new BigDecimal(2500.00), "Disponível");
        Produto produto2 = new Produto("Mouse", 20, new BigDecimal(80.00), "Disponível");

        when(produtoRepository.findAll()).thenReturn(List.of(produto1, produto2));

        List<Produto> resultado = produtoService.listarTodos();

        assertEquals(2, resultado.size());
    }

    @Test 
    void deveSalvarProduto() {
        Produto produto = new Produto("Teclado", 15, new BigDecimal(150.00), "Disponível");

        when(produtoRepository.save(produto)).thenReturn(produto);

        Produto resultado = produtoService.salvar(produto);

        assertEquals("Teclado", resultado.getNome());
    }

    @Test 
    void deveSalvarListaDeProdutos() {
        Produto produto1 = new Produto("Monitor", 5, new BigDecimal(900.00), "Disponível");
        Produto produto2 = new Produto("Cadeira escritório", 10, new BigDecimal(450.00), "Disponível");

        List<Produto> produtos = List.of(produto1, produto2);

        when(produtoRepository.saveAll(produtos)).thenReturn(produtos);

        List<Produto> resultado = produtoService.salvarLista(produtos);

        assertEquals(2, resultado.size());
    }

    @Test
    void deveDeletarProduto() {
        Long id = 1L;

        produtoService.deletar(id);

        verify(produtoRepository).deleteById(id);
    }

    @Test 
    void deveAtualizarProduto() {
        Long id = 1L;

        Produto produto = new Produto("Celular iPhone", 10, new BigDecimal(3000.00), "Disponível");

        when(produtoRepository.existsById(id)).thenReturn(true);
        when(produtoRepository.save(produto)).thenReturn(produto);

        Produto resultado = produtoService.atualizar(id, produto);

        assertEquals(id, resultado.getId());
        assertEquals("Celular iPhone", resultado.getNome());
    }

    @Test 
    void deveLancarExcecaoAoAtualizarProdutoInexistente() {
        Long id = 99L;

        Produto produto = new Produto("Celular iPhone", 10, new BigDecimal(3000.00), "Disponível");

        when(produtoRepository.existsById(id)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> {
            produtoService.atualizar(id, produto);
        });
    }

    @Test 
    void deveBuscarProdutoPorId() {
        Long id = 1L;

        Produto produto = new Produto("Notebook", 10, new BigDecimal(4000.00), "Disponível");
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));

        Optional<Produto> resultado = produtoService.findById(id);

        assertTrue(resultado.isPresent());
        assertEquals("Notebook", resultado.get().getNome());
    }

    @Test 
    void deveRetornarOptionalVazioQuandoProdutoNaoExistir() {
        Long id = 99L;

        when(produtoRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Produto> resultado = produtoService.findById(id);

        assertTrue(resultado.isEmpty());
    }

}
