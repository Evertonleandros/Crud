package com.example.crud.crud.enuns;
public enum EnumTipoItem {
    Produto("produto","PRODUTO","Produto"),
    Servico("servico","SERVICO","Servico");
    private String minusculo;
    private String maisculo;
    private String primeiraLetraMaiuscula;

    EnumTipoItem(String minusculo, String maisculo, String primeiraLetraMaiuscula) {
        this.minusculo = minusculo;
        this.maisculo = maisculo;
        this.primeiraLetraMaiuscula = primeiraLetraMaiuscula;
    }
}
