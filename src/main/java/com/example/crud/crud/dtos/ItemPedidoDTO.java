package com.example.crud.crud.dtos;

import com.example.crud.crud.models.ItemPedidoModel;
import lombok.Data;

@Data
public class ItemPedidoDTO {

    private String produtoId;
    private String nome;
    private String tipo;
    private Integer quantidade;


    public ItemPedidoDTO(){}
    public ItemPedidoDTO(ItemPedidoModel model){
        if (model == null) {
            return;
        }
        this.produtoId = model.getProduto().getProdutoId().toString();
        this.quantidade = model.getQuantidade();
        this.nome = model.getProduto().getNome();
        this.tipo = model.getProduto().getTipo();
    }
}
