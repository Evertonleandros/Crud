package com.example.crud.crud.dtos;

import com.example.crud.crud.models.ItemPedidoModel;
import com.example.crud.crud.models.PedidoModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class PedidoDTO {

    private UUID pedidoId;
    private List<ItemPedidoDTO> itens;
    @NotBlank
    private String situacao;
    private Double percentualDesconto;
    private Double valorTotal;

    public PedidoDTO(PedidoModel model){
        BeanUtils.copyProperties(model, this);
        this.itens = new ArrayList<>();
        for(ItemPedidoModel itemPedidoModel : model.getItens()){
            this.itens.add(new ItemPedidoDTO(itemPedidoModel));
        }
    }
}
