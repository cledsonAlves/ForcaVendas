package com.br.gui;



import java.io.File;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.br.adaptadores.AdaptadorItens;
import com.br.adaptadores.EnviaPedido;
import com.br.kelma.Menu;
import com.br.kelma.R;
import com.br.logica.Logica;
import com.br.objetos.Pedido;

public class TelaOrcamento extends ListActivity {
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
		
		pedidos = logica.buscaPedido(this,"TpVenda  = 'Orçamento'");
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
			dialog.setContentView(R.layout.dialogo_orcamento);
			// define o título do Dialog
			dialog.setTitle("Operações com orçamento ?");
			this.setProgressBarVisibility(true);
			dialog.findViewById(R.id.btn_Confirmar);
			
			final Button excluir = (Button) dialog.findViewById(R.id.btnVoltar);
			final Button voltarPedido = (Button) dialog.findViewById(R.id.btnEditar);
			final Button cancela = (Button) dialog.findViewById(R.id.button_cancelar);
			
			excluir.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					
					
					AlertDialog.Builder alerta = new AlertDialog.Builder(TelaOrcamento.this);
					alerta.setTitle("Deseja excluir o orçamento do sistema ?");
					alerta.setIcon(R.drawable.bd);
					alerta.setMessage("");
					// método executado caso escolha sim
					alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							
							logica.excluiPedido(pedido.getNumero(), getApplicationContext());
							
							Toast.makeText(getApplicationContext(), " Orçamento Excluído com sucesso!", Toast.LENGTH_LONG).show();
							finish();

						}
					});
					// método executado caso escolha não ..
					alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();

						}
					});
					alerta.show();
					
				}
			});
			
			voltarPedido.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					
					AlertDialog.Builder alerta = new AlertDialog.Builder(TelaOrcamento.this);
					alerta.setTitle("Deseja montar pedido?");
					alerta.setIcon(R.drawable.bd);
					alerta.setMessage("");
					// método executado caso escolha sim
					alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							logica.atualizaTipoVenda(getApplicationContext(), pedido.getNumero());
							Toast.makeText(getApplicationContext(), " Pedido pronto para ser enviado!", Toast.LENGTH_LONG).show();
					
							finish();

						}
					});
					// método executado caso escolha não ..
					alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();

						}
					});
					alerta.show();
						

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
