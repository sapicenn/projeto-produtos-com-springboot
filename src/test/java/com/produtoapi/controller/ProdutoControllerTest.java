package com.produtoapi.controller;

import com.produtoapi.service.ProdutoService;
import com.produtoapi.model.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {
    
    @Autowired 
    private MockMvc mockMvc;

    @MockitoBean 
    private ProdutoService produtoService;

    @Test 
    void deveRetornarListaDeProdutos() throws Exception {

        Produto produto = new Produto("Notebook", 10, new BigDecimal(4000.00), "Disponível");

        produto.setId(1L);

        when(produtoService.listarTodos()).thenReturn(List.of(produto));

        mockMvc.perform(get("/produtos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].nome").value("Notebook"))
            .andExpect(jsonPath("$[0].quantidade").value(10));
    }

    @Test 
    void deveRetornar404QuandoProdutoNaoExistir() throws Exception {

        when(produtoService.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/produtos/99"))
            .andExpect(status().isNotFound());
    }
}
