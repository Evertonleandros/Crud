package com.example.crud.crud.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.NotFound;

import java.util.UUID;

@Data
@Entity
@Table(name = "PRODUTOS")
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID produtoId;

    @NotBlank
    @Column(name = "NOME")
    private String nome;

    @NotNull
    @Column(name = "PRECO")
    private Double preco;

    @NotBlank
    @Column(name = "TIPO")
    private String tipo;

    @NotNull
    @Column(name = "IS_ATIVO")
    private Boolean isAtivo;

    @Override
    public String toString() {
        return "ProdutoModel{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                ", tipo='" + tipo + '\'' +
                ", isAtivo=" + isAtivo +
                '}';
    }
}
