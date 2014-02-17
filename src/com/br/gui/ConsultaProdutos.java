package com.br.gui;


import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.br.adaptadores.AdaptadorProduto;
import com.br.baseDados.ManipulaBanco;
import com.br.objetos.Produto;

public class ConsultaProdutos extends ListActivity {
private ArrayList<Produto> listaProdutos;

	
	//  construtor 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ManipulaBanco mb = new ManipulaBanco(this);
		listaProdutos = new ArrayList<Produto>();
		listaProdutos = mb.buscaProduto();
		//  adaptador da lista customizado
		setListAdapter(new AdaptadorProduto(this,listaProdutos));

		
	}


	public boolean onListLongClick(ListView l, View v, int pos,
			long id) {
		Toast.makeText(this,"OK", Toast.LENGTH_LONG).show();
		return false;
	}  




}
