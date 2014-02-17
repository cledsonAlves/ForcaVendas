package com.br.gui;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.br.kelma.R;
import com.br.logica.Logica;
import com.br.objetos.Vendedor;

public class TelaVendedores  extends ListActivity{
	private ArrayList<String> lista;
	private ArrayAdapter<String> adaptador;
	private Logica logica;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		logica = new Logica();
		iniciaComponentes();
		
		
	}
	
	private void iniciaComponentes(){
		ArrayList<Vendedor> lisVendedores =  new ArrayList<Vendedor>();
		
		lista = new ArrayList<String>();
		lisVendedores = logica.buscaVendedor(this);
		
		for(int i =0; i < lisVendedores.size(); i++ ){
			lista.add(lisVendedores.get(i).getCodigo() + "  -  " + lisVendedores.get(i).getNome());
		}
		
		
	  adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
	  
	  setListAdapter(adaptador);
	}
	
	
	//  deletar vendedor  .....

	public boolean onListLongClick(ListView l, View v, int pos, long id) {
		Toast.makeText(this,"OK", Toast.LENGTH_LONG).show();
		return false;
	}  
	
	
}
