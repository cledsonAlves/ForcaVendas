package com.br.objetos;

public class Produto {
	private int codigo;
	private String descricao;
	private String embalagem;
	private double peso;
	private String departamento;
	private double preco;
	private String categoria;
	private String fornecedor;
	private int estoque;
	private String foto;

	
	
	

	public int getEstoque() {
		return estoque;
	}


	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}


	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}


	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getEmbalagem() {
		return embalagem;
	}


	public void setEmbalagem(String embalagem) {
		this.embalagem = embalagem;
	}


	public double getPeso() {
		return peso;
	}


	public void setPeso(double peso) {
		this.peso = peso;
	}


	public String getDepartamento() {
		return departamento;
	}


	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}


	public double getPreco() {
		return preco;
	}


	public void setPreco(double preco) {
		this.preco = preco;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public String getFornecedor() {
		return fornecedor;
	}


	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}


	// POLIMORFISMO  construtor da classe vazio
	public Produto(){
		
	}
	

	// POLIMORFISMO construtor da classe com atributos . ...
	public Produto(int codigo, String descricao, String embalagem, double preco,String
			categoria,String fornecedor) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.embalagem = embalagem;
		this.preco = preco;
		this.categoria = categoria;
		this.fornecedor = fornecedor;
	}

}
