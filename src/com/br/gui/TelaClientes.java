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
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.br.adaptadores.AdaptadorCliente;
import com.br.adaptadores.AdaptadorProduto;
import com.br.baseDados.ManipulaBanco;
import com.br.kelma.R;
import com.br.logica.Consulta;
import com.br.objetos.Cliente;
import com.br.objetos.Produto;

public class TelaClientes extends Activity {
	private List<Cliente> listaClientes;
	 EditText etPesquisa;
	 public static String filtro_cliente = "";
	 AdaptadorCliente adapter;
	 private RatingBar estrelaClientes;
	
	ListView listaItens; 
	private ArrayList<Cliente> novaLista = new ArrayList<Cliente>();

	
	//  construtor 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			
		ManipulaBanco mb = new ManipulaBanco(this);
		listaClientes = new ArrayList<Cliente>();
		listaClientes = mb.buscaCliente();

		setContentView(R.layout.consulta_cliente);
		 listaItens = (ListView) findViewById(R.id.listViewClientes);
		 etPesquisa = (EditText) findViewById(R.id.clientes);
		 etPesquisa.addTextChangedListener(filterTextWatcher);
		 
		
		 
		 etPesquisa.selectAll();
		 etPesquisa.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
		 
		 adapter = new AdaptadorCliente(this, listaClientes);

		
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
		

					Intent it = new Intent();
					it.putExtra("id",nome);
					setResult(2, it);
					finish();

					// senão, pega na lista normal
				} else {
					Cliente c = listaClientes.get(position);
					String nome = c.getNome();

					Intent it = new Intent();
					it.putExtra("id",nome);
					
					setResult(2, it);
					finish();
				}

			}
		});
		
		
		listaItens.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				
				Toast.makeText(getApplicationContext(),"Ok",Toast.LENGTH_LONG).show();

				return false;
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
		
			
		for (int i = 0; i < listaClientes.size(); i++) {
			if (listaClientes.get(i).getNome().contains(pesquisa)) {
				novaLista.add(listaClientes.get(i));

			} else if (String.valueOf(listaClientes.get(i).getCodigo())
					.contains(etPesquisa.getText().toString())) {
				novaLista.add(listaClientes.get(i));

			} 

		}
           }catch(NumberFormatException e){
			this.finish();
		}

	}

}



		
	