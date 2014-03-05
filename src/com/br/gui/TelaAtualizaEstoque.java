package com.br.gui;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.br.adaptadores.EnviaPedido;
import com.br.baseDados.AtualizaClientes;
import com.br.baseDados.ManipulaBanco;
import com.br.kelma.R;
import com.br.logica.Logica;
import com.br.objetos.ClienteJson;

public class TelaAtualizaEstoque extends Activity {
	ManipulaBanco mb;
	private Logica logica = new Logica();
	private int count = 0, count2 = 0;
     ProgressDialog dialog;
	TextView txtClientes, txtAtualizados;
	ProgressBar progressBar;
	AtualizaClientes atualiza = new AtualizaClientes();
	private ArrayList<ClienteJson> lista;
	private int progresso;
	private Handler handler = new Handler();
	boolean baixou;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.baixa_clientes);

		txtClientes = (TextView) findViewById(R.id.textViewClientes);
		txtAtualizados = (TextView) findViewById(R.id.textViewAtualizados);

		txtClientes.setText("Total de clientes baixados : " + count);
		txtAtualizados.setText("Total de clientes atualizados : " + count2);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);

	}

	public void btn_baixar(View v) {
		txtClientes.setText("Total de clientes baixados : " + 0);
		txtAtualizados.setText("Total de clientes atualizados : " + 0);	
		
		final ProgressDialog dialog = new ProgressDialog(TelaAtualizaEstoque.this);
		dialog.setCancelable(true);
		dialog.setMessage("Buscando clientes no servidor , aguarde ...");
		dialog.show();
		new Thread() {
			public void run() {	
		   // baixa os clientes do ftp
			 baixou =  atualiza.enviaPedido(getApplicationContext());
				handler.post(new Runnable() {
					public void run() {
					  if (baixou){
						  dialog.dismiss();
						lista = atualiza.buscaClientes(getApplicationContext());
						if (lista.size()> 0){
						txtClientes.setText("Total de clientes baixados : " + lista.size());
						txtAtualizados.setText("Total de clientes atualizados : " + count);	
							new TarefaWS().execute();	
						}
					  }else{
						  dialog.dismiss();
						  Toast.makeText(getApplicationContext(), "não foi possível conectar ao servidor" +
						  		", verifique suas conexões de Internet",Toast.LENGTH_LONG).show();
					  }
					}
				});
			}
		}.start();
		
		

	}

	// classe ansicrona interna
	class TarefaWS extends AsyncTask<Void, Void, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(TelaAtualizaEstoque.this);
			dialog.setCancelable(true);
			dialog.setMessage("Atualizando Base de dados de clientes ...");
			dialog.setMax(lista.size());

			// define o estilo como horizontal que nesse caso signifca que terá
			// barra de progressão/contagem
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.show();

		}

		@Override
		protected String doInBackground(Void... params) {
			try {
				while (dialog.getProgress() < dialog.getMax()) {
					// esse é apenas um método de teste que pode ser
					// demorado
					if (logica.cadastraClienteJson(lista.get(dialog.getProgress()),
							getApplicationContext())) {
						count++;
					}
					dialog.incrementProgressBy(1);
					progresso = dialog.getProgress();
					//setProgress(progresso);		
					
				}
			} catch (Exception e) {
				Log.e("tag", e.getMessage());
			} catch (Throwable e) {
			Toast.makeText(getApplicationContext(), "Erro ao finalizar", Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}			
			count2 = lista.size();
			return "";
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			txtClientes.setText("Total de clientes baixados : " + count2);
			txtAtualizados.setText("Total de clientes atualizados : " + count);			
			dialog.dismiss();

		}

	}
	public void atualiza(){
		Log.i("atualiza", "teste");
	}
}
