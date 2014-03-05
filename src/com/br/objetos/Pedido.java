package com.br.objetos;

import java.util.ArrayList;

import com.br.gui.ItemListView;

public class Pedido {
	private String representante;
	private int numero;
	private int codigoCliente;
	private String nomeCliente;
	private double valorTotal;
	private int qtdItens;
	private String condPagamento;
	private String tipoVenda;
	private String tipoPagamento;
	private String dataVenda;
	private String filialVenda;
	private String tabela;
	private String status;
	private Double peso;
	private int codTabela;
	private String obs;
	
	
	
	
	
	
	public String getRepresentante() {
		return representante;
	}
	public void setRepresentante(String representante) {
		this.representante = representante;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public int getCodTabela() {
		return codTabela;
	}
	public void setCodTabela(int codTabela) {
		this.codTabela = codTabela;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	private ArrayList<ItemListView> listaItens;
	
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public int getQtdItens() {
		return qtdItens;
	}
	public void setQtdItens(int qtdItens) {
		this.qtdItens = qtdItens;
	}

	
	

	public ArrayList<ItemListView> getListaItens() {
		return listaItens;
	}
	public void setListaItens(ArrayList<ItemListView> listaItens) {
		this.listaItens = listaItens;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getCondPagamento() {
		return condPagamento;
	}
	public void setCondPagamento(String condPagamento) {
		this.condPagamento = condPagamento;
	}
	public String getTipoVenda() {
		return tipoVenda;
	}
	public void setTipoVenda(String tipoVenda) {
		this.tipoVenda = tipoVenda;
	}
	public String getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	public String getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}
	public String getFilialVenda() {
		return filialVenda;
	}
	public void setFilialVenda(String filialVenda) {
		this.filialVenda = filialVenda;
	}
	public String getTabela() {
		return tabela;
	}
	public void setTabela(String tabela) {
		this.tabela = tabela;
	}
	


	
	
	

}
