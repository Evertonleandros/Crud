package com.example.crud.crud.dtos;

import com.example.crud.crud.enuns.EnumTipoItem;
import com.example.crud.crud.models.ProdutoModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class ProdutoDTO {
    @NotBlank
    private String nome;
    @NotNull
    private Double preco;
    @NotNull
    private Boolean ativo;
    @NotBlank
    private String tipo;

    public ProdutoDTO(){

    }

    public ProdutoDTO(ProdutoModel model) {
        BeanUtils.copyProperties(model, this);
    }
}
