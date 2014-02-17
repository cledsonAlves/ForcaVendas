package com.br.logica;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import com.br.adaptadores.AdaptadorProduto;
import com.br.baseDados.ManipulaBanco;
import com.br.kelma.R;
import com.br.objetos.Produto;

public class Consulta extends Activity {
	public static EditText etPesquisa;
	public static String filtro = "";

	private ArrayList<Produto> novaLista = new ArrayList<Produto>();

	// //////////////////////////////////////////////////////////////////////////////////////
	AdaptadorProduto adapter;
	private List<Produto> listaProdutos;
	private ArrayList<String> listView;
	AdaptadorProduto adaptadorProduto;
	ListView listaItens;
	private Logica logica;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.consulta);

		listaItens = (ListView) findViewById(R.id.lvEstados);
		etPesquisa = (EditText) findViewById(R.id.etProcurar);
		etPesquisa.addTextChangedListener(filterTextWatcher);
		
	
		

		ManipulaBanco mb = new ManipulaBanco(this);
		listaProdutos = mb.buscaProduto();

		adapter = new AdaptadorProduto(this, listaProdutos);

		listaItens.setAdapter(adapter);

		// //////////////////////////////////////////////////////
		etPesquisa.setText(filtro);
		etPesquisa.selectAll();
		etPesquisa.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
		
		//  ocultando o teclado 
		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(etPesquisa.getWindowToken(), 0);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		listaItens.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView arg0, View view, int position,
					long index) {
				filtro = etPesquisa.getText().toString();
				

				// se for feito o filtro pega na nova lista ...
				if (novaLista.size() > 0) {
					Produto p = novaLista.get(position);
					String nome = p.getDescricao();

					Intent it = new Intent();
					it.putExtra("descricao", nome);
					it.putExtra("preco", p.getPreco());
					it.putExtra("codigo", p.getCodigo());
					setResult(2, it);
					finish();

					// senão, pega na lista normal
				} else {
					Produto p = listaProdutos.get(position);
					String nome = p.getDescricao();

					Intent it = new Intent();
					it.putExtra("descricao", nome);
					it.putExtra("preco", p.getPreco());
					it.putExtra("codigo", p.getCodigo());
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

			adapter = new AdaptadorProduto(Consulta.this, novaLista);
			listaItens.setAdapter(adapter);

		}

	};

	public void CarregarEncontrados() {
		int textlength = etPesquisa.getText().length();
		/**if (etPesquisa.getText().toString().equalsIgnoreCase((String) listaProdutos.get(i).getDescricao().subSequence(0, textlength))) {
			novaLista.add(listaProdutos.get(i));
		} else **/
		
		novaLista.clear();
		try{
		String pesquisa = etPesquisa.getText().toString();
		
			
		for (int i = 0; i < listaProdutos.size(); i++) {
			if (listaProdutos.get(i).getDescricao().contains(pesquisa)) {
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
