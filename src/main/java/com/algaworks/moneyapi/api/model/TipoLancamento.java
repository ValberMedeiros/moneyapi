package com.algaworks.moneyapi.api.model;

public enum TipoLancamento {

    RECEITA("Receita"),
    DESPESA("Despeaa");

    private String descricao;


    TipoLancamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
