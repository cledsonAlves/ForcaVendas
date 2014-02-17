package com.br.gui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
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

import com.br.adaptadores.AdaptadorCliente;
import com.br.baseDados.ManipulaBanco;
import com.br.kelma.R;
import com.br.objetos.Cliente;

public class TelaClientes2 extends Activity {
	private ArrayList<Cliente> listaClientes;
	private List<Cliente> listaProdutos;
	 EditText etPesquisa;
	 public static String filtro_cliente = "";
	 AdaptadorCliente adapter;
	
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
		
			
			
			Cliente cliente = novaLista.get(position);
			String em = cliente.getEmail();  
		
			String cnpj = cliente.getCnpj();
			String razao = cliente.getNome();
			String endereco = cliente.getEnd();
			String cidade = cliente.getCidade();
			String tel = cliente.getTel1();
			
			String mensagem = "Razão Social : " + razao + "\r\n" +
			                  "Cnpj ou CPF : "         + cnpj + "\r\n"  +
			                  "Endereço : "     + endereco + "\r\n"  +
			                  "Cidade  : "       + cidade + "\r\n"  +
			                  "Telefone : "       + tel + "\r\n"  +
			                  "E-mail : "       + em + "\r\n" + 
			                   "\r\n" + 
			                   "\r\n  Atenciosamente,\r\n" + 
			                   "Sistema de Força de Vendas - 2014, versão 1.2" 
			                  ;			
			
			Intent email = new Intent(Intent.ACTION_SEND);
			email.putExtra(Intent.EXTRA_EMAIL, new String[]{"vendas@kelma.com.br"});		  
			email.putExtra(Intent.EXTRA_SUBJECT, "Dados Cliente Novo para Cadastro - Enviado via Sistema de Pedidos");
			email.putExtra(Intent.EXTRA_TEXT, mensagem);
			email.setType("message/rfc822");
			startActivity(Intent.createChooser(email, " Escolha o e-mail do cliente :"));
			

				// se for feito o filtro pega na nova lista ...
			/**if (novaLista.size() > 0) {
					Cliente c = novaLista.get(position);
					String nome = c.getNome();  
				

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
				}**/

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

			adapter = new AdaptadorCliente(TelaClientes2.this, novaLista);
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
