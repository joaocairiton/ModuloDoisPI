package com.arionporfirio;

public class Produto {
    private String codigo;
    private String descricao;
    private double preco;
    private String unidade;
    
    public Produto(){
        this.codigo = "";
        this.descricao = "";
        this.preco = 0.00;
        this.unidade = "";
    }
    
    public Produto(String codigo, String descricao, double preco, String unidade) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
        this.unidade = unidade;        
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public double getPreco() {
        return preco;
    }
    
    public String getPrecoFormatado() {
        return String.format("R$ %.2f", preco);
    }
    
    public String getUnidade() {
        return unidade;
    }
    
    public void setCodigo(String codigo) throws IllegalArgumentException {
        if (codigo.length() != 13) {
            throw new IllegalArgumentException("Código do produto inválido!");
        }
        this.codigo = codigo;
    }
    
    public void setDescricao(String descricao) throws IllegalArgumentException {
        if(descricao.isEmpty()) {
            throw new IllegalArgumentException("A descrição do produto não deve ser vazia!");
        }
        this.descricao = descricao;
    }
    
    public void setPreco(double preco) throws IllegalArgumentException {
        if(!(preco > 0.00)) {
            throw new IllegalArgumentException("Preço do produto inválido!");
        }
        this.preco = preco;
    }
    
    public void setUnidade(String unidade) {
        if(unidade.isEmpty()) {
            throw new IllegalArgumentException("Unidade do produto inválida!");
        }
        this.unidade = unidade;
    }
}
