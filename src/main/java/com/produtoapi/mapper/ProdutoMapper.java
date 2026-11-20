package com.produtoapi.mapper;

import com.produtoapi.dto.ProdutoRequestDTO;
import com.produtoapi.dto.ProdutoResponseDTO;
import com.produtoapi.model.Produto;

public class ProdutoMapper {

    public static Produto toEntity(ProdutoRequestDTO dto) {
        Produto produto = new Produto();

        produto.setNome(dto.nome());
        produto.setQuantidade(dto.quantidade());
        produto.setPreco(dto.preco());
        produto.setStatus(dto.status());
    
        return produto;
    }

    public static ProdutoResponseDTO toResponseDTO(Produto produto) {
        return new ProdutoResponseDTO(
            produto.getId(),
            produto.getNome(),
            produto.getQuantidade(),
            produto.getPreco(),
            produto.getStatus()
        );
    } 
}