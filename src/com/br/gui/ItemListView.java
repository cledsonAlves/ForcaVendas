package com.br.gui;


public class ItemListView {

    private String descricao;
    private int codigo;
    private int quantidade;
    private double valorUnitario;
    private double valorTotal;
    private double peso;
    
    
///  construtor vazio 
    public ItemListView() {
    }
//  construtor de atributos;
    public ItemListView(String descricao, int codigo,int quantidade, double valorUnitario, double valorTotal, double peso) {
        this.descricao = descricao;
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.peso = peso;
    }
    
    //  getters e setter;
    
    
    
	public String getDescricao() {
		return descricao;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
    

}
