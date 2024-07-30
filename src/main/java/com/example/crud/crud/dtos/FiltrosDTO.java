package com.example.crud.crud.dtos;

import lombok.Data;

@Data
public class FiltrosDTO {

    private String produtoId;
    private String situacao;
    private String nome;
    private Double preco;
    private String pedidoId;
    private Double percentualDesconto;
    private Double valorTotal;
    private String tipo;
    private Boolean isAtivo;

}
