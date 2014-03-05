package com.br.objetos;

public class ItemPedido {
	
	private int numPedido;
	private int codProduto;
	private String descricao;
	private int quantidade;
	private double vlUnitario;
	private double vlTotal;
	private double peso;
	
	
	
	
	
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getNumPedido() {
		return numPedido;
	}
	public void setNumPedido(int numPedido) {
		this.numPedido = numPedido;
	}
	public int getCodProduto() {
		return codProduto;
	}
	public void setCodProduto(int codProduto) {
		this.codProduto = codProduto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getVlUnitario() {
		return vlUnitario;
	}
	public void setVlUnitario(double vlUnitario) {
		this.vlUnitario = vlUnitario;
	}
	public double getVlTotal() {
		return vlTotal;
	}
	public void setVlTotal(double vlTotal) {
		this.vlTotal = vlTotal;
	}
	
	
			

}
