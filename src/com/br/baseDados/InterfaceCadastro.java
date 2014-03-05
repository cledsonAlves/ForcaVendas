package com.br.baseDados;

import android.content.ContentValues;

import com.br.objetos.Cidade;
import com.br.objetos.Cliente;
import com.br.objetos.Estado;
import com.br.objetos.Fornecedor;
import com.br.objetos.Produto;

public interface InterfaceCadastro {
	
	//  cadastra cliente
	public void cadastraCliente(Cliente cliente);
	public boolean cadastraCliente(ContentValues dados);
	
	// cadastra produto
	public void cadastraProduto(Produto produto);
	public boolean cadastraProduto(ContentValues dados);
	
	// cadastra fornecedor
	public void cadastraFornecedor(Fornecedor fornecedor);
	public boolean cadastraFornecedor(ContentValues dados);
	
	// cadastra cidade
	public void cadastraCidade(Cidade cidade);
	public boolean cadastraCidade(ContentValues dados);
	
	// cadastra estado
	public void cadastraEstado(Estado estado);
	public boolean cadastraEstado(ContentValues dados);
	

}
