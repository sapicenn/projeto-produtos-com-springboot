package com.produtoapi.dto;

import java.math.BigDecimal;

public record ProdutoResponseDTO(
    Long id,
    String nome,
    Integer quantidade,
    BigDecimal preco,
    String status
) {}