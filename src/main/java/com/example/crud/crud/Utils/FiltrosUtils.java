package com.example.crud.crud.Utils;

import com.example.crud.crud.dtos.FiltrosDTO;
import com.example.crud.crud.models.QPedidoModel;
import com.example.crud.crud.models.QProdutoModel;
import com.querydsl.core.BooleanBuilder;

import java.util.UUID;

public class FiltrosUtils {
    public static BooleanBuilder verificaFiltrosNulos(FiltrosDTO filtros) {
        QPedidoModel qPedidoModel = QPedidoModel.pedidoModel;
        QProdutoModel qProdutoModel = QProdutoModel.produtoModel;
        BooleanBuilder where = new BooleanBuilder();
        if (filtros.getPedidoId() != null) {
            where.and(qPedidoModel.pedidoId.eq(UUID.fromString(filtros.getPedidoId())));
        }
        if (filtros.getSituacao() != null) {
            where.and(qPedidoModel.situacao.equalsIgnoreCase(filtros.getSituacao()));
        }
        if (filtros.getValorTotal() != null) {
            where.and(qPedidoModel.valorTotal.eq(filtros.getValorTotal()));
        }
        if (filtros.getProdutoId() != null) {
            where.and(qProdutoModel.produtoId.eq(UUID.fromString(filtros.getProdutoId())));
        }
        if (filtros.getNome() != null) {
            where.and(qProdutoModel.nome.equalsIgnoreCase(filtros.getNome()));
        }
        if (filtros.getPreco() != null) {
            where.and(qProdutoModel.preco.eq(filtros.getPreco()));
        }
        if (filtros.getTipo() != null) {
            where.and(qProdutoModel.tipo.equalsIgnoreCase(filtros.getTipo()));
        }
        return where;
    }
}
