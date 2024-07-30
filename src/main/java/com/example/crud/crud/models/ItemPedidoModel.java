package com.example.crud.crud.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "ITENS_PEDIDO")
public class ItemPedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID itensPedidoId;

    @ManyToOne
    private PedidoModel pedido;

    @ManyToOne
    @JoinColumn(name = "PRODUTO_ID")
    @NotNull
    private ProdutoModel produto;

    @Column(name = "QUANTIDADE")
    private int quantidade;

}
