package com.br.gui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

import com.br.adaptadores.AdaptadorCliente;
import com.br.adaptadores.AdaptadorProduto;
import com.br.baseDados.ManipulaBanco;
import com.br.kelma.R;
import com.br.logica.Consulta;
import com.br.objetos.Cliente;
import com.br.objetos.Produto;

public class TelaClientes extends Activity {
	private ArrayList<Cliente> listaClientes;
	private List<Cliente> listaProdutos;
	 EditText etPesquisa;
	 public static String filtro_cliente = "";
	 AdaptadorCliente adapter;
	 private RatingBar estrelaClientes;
	
	ListView listaItens; 
	private ArrayList<Cliente> novaLista = new ArrayList<Cliente>();
	//listViewClientes
	
	
	
	//  construtor 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		ManipulaBanco mb = new ManipulaBanco(this);
		listaProdutos = new ArrayList<Cliente>();
		listaProdutos = mb.buscaCliente();

		setContentView(R.layout.consulta_cliente);
		 listaItens = (ListView) findViewById(R.id.listViewClientes);
		 etPesquisa = (EditText) findViewById(R.id.clientes);
		 etPesquisa.addTextChangedListener(filterTextWatcher);
		 
		
		 
		 etPesquisa.selectAll();
		 etPesquisa.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
		 
		 adapter = new AdaptadorCliente(this, listaProdutos);

		
		//  adaptador da lista customizado
		 listaItens.setAdapter(adapter);
		
		// //////////////////////////////////////////////////////
		etPesquisa.setText(filtro_cliente);
		etPesquisa.selectAll();
		etPesquisa.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
		
		//  ocultando o teclado 
		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(etPesquisa.getWindowToken(), 0);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		listaItens.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView arg0, View view, int position,
					long index) {
				filtro_cliente = etPesquisa.getText().toString();
				

				// se for feito o filtro pega na nova lista ...
				if (novaLista.size() > 0) {
					Cliente c = novaLista.get(position);
					String nome = c.getNome();  
					/**
					 * 	      Cliente cliente = listaClientes.get(position);
	      String nome = cliente.getNome();
	      Intent it = new Intent();   
	      it.putExtra("id",nome);  
	      setResult(2,it);
	          finish(); 
					 */

					Intent it = new Intent();
					it.putExtra("id",nome);
					setResult(2, it);
					finish();

					// senão, pega na lista normal
				} else {
					Cliente c = listaProdutos.get(position);
					String nome = c.getNome();

					Intent it = new Intent();
					it.putExtra("id",nome);
					
					setResult(2, it);
					finish();
				}

			}
		});

	}

	private TextWatcher filterTextWatcher = new TextWatcher() {

		public void afterTextChanged(Editable s) {
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			CarregarEncontrados();

			adapter = new AdaptadorCliente(TelaClientes.this, novaLista);
			listaItens.setAdapter(adapter);

		}

	};

	public void CarregarEncontrados() {
	
		
		novaLista.clear();
		try{
		String pesquisa = etPesquisa.getText().toString();
		
			
		for (int i = 0; i < listaProdutos.size(); i++) {
			if (listaProdutos.get(i).getNome().contains(pesquisa)) {
				novaLista.add(listaProdutos.get(i));

			} else if (String.valueOf(listaProdutos.get(i).getCodigo())
					.contains(etPesquisa.getText().toString())) {
				novaLista.add(listaProdutos.get(i));

			} 

		}
           }catch(NumberFormatException e){
			this.finish();
		}

	}

}



		
	