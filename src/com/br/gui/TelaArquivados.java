package com.br.gui;

import java.io.File;
import java.util.ArrayList;

import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.br.adaptadores.AdaptadorItens;
import com.br.adaptadores.EnviaPedido;
import com.br.kelma.R;
import com.br.logica.Logica;
import com.br.objetos.Pedido;

public class TelaArquivados extends ListActivity {
	private Logica logica;
	private int progresso;
	private ProgressDialog mprogressDialog;
	private Handler mhandler;
	Pedido pedido;
	private File file;
	 private boolean retorno;

	private ArrayList<Pedido> pedidos;
	

	// Construtor
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		logica = new Logica();
		mhandler = new Handler();
		pedidos = new ArrayList<Pedido>();
		
		pedidos = logica.buscaPedido(this,"STATUS  = 'ARQUIVADO'");
		//  adaptador da lista customizado
		setListAdapter(new AdaptadorItens(this, pedidos));		
		
	}

	
	@Override   
	   protected void onListItemClick(ListView l, View v, int position, long id) {
		  super.onListItemClick(l, v, position, id);
//	     //  pega o item clicado
		  
	     pedido = pedidos.get(position);
	     
		   
	     
	     
	      exibeDialogo();  
	     

	      
	      
	      
	   }
	
	
	 public void exibeDialogo(){
		 final Dialog dialog = new Dialog(this);
			dialog.requestWindowFeature(Window.FEATURE_PROGRESS);
			dialog.setContentView(R.layout.dialogo_arquiva);
			// define o título do Dialog
			dialog.setTitle("Operações com pedidos arquivados");
			this.setProgressBarVisibility(true);
			dialog.findViewById(R.id.btn_Confirmar);
			
			
			
			final Button excluir = (Button) dialog.findViewById(R.id.btnVoltar);
			final Button voltarPedido = (Button) dialog.findViewById(R.id.btnEditar);
			final Button cancela = (Button) dialog.findViewById(R.id.button_cancela);
			
			excluir.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					
						
						logica.excluiPedido(pedido.getNumero(), getApplicationContext());
						finish();
						
						Toast.makeText(getApplicationContext(), " Pedido Excluido com sucesso!", Toast.LENGTH_LONG).show();

				}
			});
			
			voltarPedido.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
						logica.atualizaStatus3(getApplicationContext(), pedido.getNumero());
						finish();

				}
			});
			 cancela.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						dialog.dismiss();

					}
				});
			
			dialog.show();

	 }


	
}
