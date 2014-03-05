package com.br.gui;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.br.baseDados.ManipulaBanco;
import com.br.kelma.R;
import com.br.logica.Logica;
import com.br.objetos.ItemPedido;
import com.br.objetos.Pedido;

public class TelaCabecalho extends Activity {
	public static Pedido pedido ;
	private EditText editTextObs, editTextPrazo, editTextTabela,editTextvalor,editTextQtds;
	private Spinner spinnerPlanos, spTabela;
	private String[] lista_tabela = new String[] { "A Vista", "14 dias",
			"21 dias", "28 dias", "35 dias", "42 dias" };
	private ArrayAdapter<String> adaptador1, adaptador2;
	private Logica logica;
	private Handler handler = new Handler();
	ManipulaBanco mb;
	ArrayList<ItemListView> listaItens;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editar_cabecalho);

		logica = new Logica();

		editTextObs = (EditText) findViewById(R.id.editTextObs);
		editTextObs.setText(pedido.getObs());

		editTextPrazo = (EditText) findViewById(R.id.editTextPrazo);
		editTextPrazo.setEnabled(false);
		editTextPrazo.setText(pedido.getCondPagamento());

		editTextTabela = (EditText) findViewById(R.id.editTextTabela);
		editTextTabela.setEnabled(false);
		editTextTabela.setText(pedido.getTabela());
		
		editTextvalor = (EditText) findViewById(R.id.editTextvalor);
		editTextvalor.setEnabled(false);
		editTextvalor.setText(String.valueOf(pedido.getValorTotal()));
		
		editTextQtds = (EditText) findViewById(R.id.editTextQtds);
		editTextQtds.setEnabled(false);
		editTextQtds.setText(String.valueOf(pedido.getQtdItens()));
		

		// Spiner
		spinnerPlanos = (Spinner) findViewById(R.id.spinnerPlanos);
	

		// ocultando o teclado
		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(editTextObs.getWindowToken(), 0);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		// criando os adaptadores de spinner
		adaptador1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, logica.buscaPlanos(this));
		adaptador1
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerPlanos.setAdapter(adaptador1);

	

	}

	public void btn_atualizar(View v) {
		atualizaTela();
	}

	public void btn_salvar(View v) {
		// atualizaTela();

		mb = new ManipulaBanco(this);
		
		listaItens = mb.buscaPedidos(pedido.getNumero());
		
		double count =0;
		for (int i = 0; i < listaItens.size(); i++) {
			ManipulaBanco mb = new ManipulaBanco(this);
			double prOriginal = mb.buscaPreco(listaItens.get(i).getCodigo());
			double prNovo = logica.calculaTabela2(verificaTabela(), prOriginal);

			listaItens.get(i).setValorUnitario(prNovo);
			listaItens.get(i).setValorTotal(prNovo * listaItens.get(i).getQuantidade());
			count = listaItens.get(i).getValorTotal() + count;

		}
		
		
		// lista temporaria
		ArrayList<ItemPedido> listaItensPedido = new ArrayList<ItemPedido>();
		
		for (int i = 0; i < listaItens.size(); i++){
			
			ItemPedido it = new ItemPedido();
			it.setNumPedido(pedido.getNumero());
			it.setCodProduto(listaItens.get(i).getCodigo());
			it.setQuantidade(listaItens.get(i).getQuantidade());
			it.setVlUnitario(listaItens.get(i).getValorUnitario());
			it.setVlTotal(listaItens.get(i).getValorTotal());
		
			
			// adiciona o item na lista 
			listaItensPedido.add(it);
		}
		Log.i("Numero Pedido",""+ pedido.getNumero());
	
		
			///if (mb.excluiRegistro("FV_Pedido", "NumPed = " + pedido.getNumero())){
				Pedido p = new Pedido();
				p.setCodigoCliente(pedido.getCodigoCliente());
				p.setNumero(pedido.getNumero());
				p.setRepresentante(p.getRepresentante());
				
				mb.atualizaPedido(pedido.getNumero(), editTextTabela.getText().toString(), editTextPrazo.getText().toString(),editTextObs.getText().toString());
				
				Toast.makeText(this, "Pedido alterado com Sucesso !!!", Toast.LENGTH_SHORT).show();	
				onPause();
			
		
	
   
	}

	private void atualizaTela() {

		handler.post(new Runnable() {
			public void run() {

				editTextPrazo.setText((spinnerPlanos.getSelectedItem()
						.toString()));
				
			

			}
		});

	}

	public int verificaTabela() {
		if (editTextTabela.getText().equals("A Vista")) {
			return 1;
		} else if (editTextTabela.getText().equals("14 dias")) {
			return 2;
		} else if (editTextTabela.getText().equals("21 dias")) {
			return 3;
		} else if (editTextTabela.getText().equals("28 dias")) {
			return 4;
		} else if (editTextTabela.getText().equals("35 dias")) {
			return 5;
		} else if (editTextTabela.getText().equals("42 dias")) {
			return 6;
		}
		return 1;
	}

	// quando entrar em pause ..
	protected void onPause() {
		super.onPause();
		this.finish();

	}

}