package com.produtoapi.dto;

import java.math.BigDecimal;

public record ProdutoRequestDTO(
    String nome,
    Integer quantidade,
    BigDecimal preco,
    String status
) {}