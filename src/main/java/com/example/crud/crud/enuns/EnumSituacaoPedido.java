package com.example.crud.crud.enuns;
public enum EnumSituacaoPedido {
    Aberto("aberto", "ABERTO", "Aberto"),
    Fechado("fechado", "FECHADO", "Fechado");
   private String minusculo;
   private String maisculo;
   private String primeiraLetraMaiuscula;
    EnumSituacaoPedido(String minusculo, String maisculo, String primeiraLetraMaiuscula) {
        this.minusculo = minusculo;
        this.maisculo = maisculo;
        this.primeiraLetraMaiuscula = primeiraLetraMaiuscula;
    }
}
