package com.example.crud.crud.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "PEDIDOS")
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID pedidoId;

    @OneToMany(mappedBy = "pedido", cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
    private List<ItemPedidoModel> itens;

    @NotNull
    @Column(name = "SITUACAO")
    private String situacao;

    @Column(name = "PERCENTUAL_DESCONTO")
    private Double percentualDesconto;

    @NotNull
    @Column(name = "VALOR_TOTAL")
    private Double valorTotal;

    @Override
    public String toString() {
        return "PedidoModel{" +
                "situacao='" + situacao + '\'' +
                ", percentualDesconto=" + percentualDesconto +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
