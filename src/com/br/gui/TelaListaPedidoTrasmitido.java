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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;
import com.br.adaptadores.AdaptadorItens;
import com.br.adaptadores.EnviaPedido;
import com.br.kelma.R;
import com.br.logica.Logica;
import com.br.objetos.Pedido;

public class TelaListaPedidoTrasmitido extends ListActivity implements OnItemLongClickListener {
	private Logica logica;
	private int progresso;
	private ProgressDialog mprogressDialog;
	private Handler mhandler;
	private Handler handler = new Handler();
	private Pedido pedido;
	private File file;
	private boolean retorno;
	 AdaptadorItens adapter;
	 
	 ProgressDialog dialogs ;
	 Dialog dialog;
		

	private ArrayList<Pedido> pedidos;

	

	// Construtor
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		logica = new Logica();
		mhandler = new Handler();
		pedidos = new ArrayList<Pedido>();
		
		
	//  TEMPORARIO para fazer a inversao da lista, ou seja o utimo pedido deve ser o primeiro
		ArrayList<Pedido> temp = new ArrayList<Pedido>();
		
		temp  = logica.buscaPedido(getApplicationContext(),"TpVenda not like 'Orçamento' and Status = 'T'");
		
		for(int i= temp.size()-1; i >= 0; i--){
			pedidos.add(temp.get(i));
		}	
		//pedidos = logica.buscaPedido(this,"TpVenda not like 'Orçamento'");
		

		
		

		//  adaptador da lista customizado
		 
		adapter = new AdaptadorItens(getApplicationContext(),pedidos);
		
		
		setListAdapter(adapter);
		
		
		dialogs = new ProgressDialog(this);
		dialogs.setMessage("Atualizando Pedidos, aguarde ...");
		
		
			
		
	}

	
	
	@Override   
	   protected void  onListItemClick(ListView l, View v, int position, long id) {
		  super.onListItemClick(l, v, position, id);
//	     //  pega o item clicado
		  
	     pedido = pedidos.get(position);
  
	      exibeDialogo();       
	   }
	
	
	 public void exibeDialogo(){
		    dialog = new Dialog(this);
			dialog.requestWindowFeature(Window.FEATURE_PROGRESS);
			dialog.setContentView(R.layout.dialog_trasmite);
			// define o título do Dialog
			dialog.setTitle("Resumo do pedido");
			this.setProgressBarVisibility(true);
			dialog.findViewById(R.id.btn_Confirmar);
			 ArrayList<ItemListView> listaItens = new  ArrayList<ItemListView> ();

			 listaItens = logica.buscaItensPedido(this,pedido.getNumero());
			 
			 
			 
		
			// instancia os objetos que estão no layout customdialog.xml
			final TextView qtdItens = (TextView) dialog.findViewById(R.id.txtQuantidade);
			final TextView vlTotal = (TextView) dialog.findViewById(R.id.txtValorPedido);
			
			final Button trasmite = (Button) dialog.findViewById(R.id.btnTrasmitir);
			final Button arquiva = (Button) dialog.findViewById(R.id.btnVoltar);
			final Button editar = (Button) dialog.findViewById(R.id.btnEditar);
			final Button cancelar = (Button) dialog.findViewById(R.id.button1);
			
		
			final ListView itens = (ListView)dialog.findViewById(R.id.ListViewIt);
			final ArrayList<String> lista = new ArrayList<String> ();
			int qtd  = 0;
			double vl = 0;
			for(int i =0; i < listaItens.size(); i++){
				lista.add(listaItens.get(i).getDescricao()+ "  " + listaItens.get(i).getQuantidade() + " UN");
				// lembra de fazer um count(*) em sql 
				qtd = qtd + listaItens.get(i).getQuantidade();
				vl = vl + listaItens.get(i).getValorTotal();
			}
		
			  String vlPedido = logica.limitaCasa(vl);

			  qtdItens.setText(String.valueOf(qtd));
			  vlTotal.setText(vlPedido);
			  
			  pedido.setValorTotal(vl);
		      pedido.setQtdItens(qtd);
		      pedido.setNomeCliente(logica.buscaCliente(pedido.getCodigoCliente(), this));

			  ArrayAdapter<String>  adaptador5 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, lista);
			  itens.setAdapter(adaptador5);
			  
			  //  Editar Pedido 
			  editar.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
					//  tabela 
						if (pedido.getTabela().equalsIgnoreCase("A Vista")){
							pedido.setCodTabela(1);	
						}else if (pedido.getTabela().toString().equalsIgnoreCase("14 dias")){
							pedido.setCodTabela(2);
						}else if (pedido.getTabela().toString().equalsIgnoreCase("21 dias")){
							pedido.setCodTabela(3);
						}
			             else if (pedido.getTabela().toString().equalsIgnoreCase("28 dias")){
			            	 pedido.setCodTabela(4);
						}
			             else if (pedido.getTabela().toString().equalsIgnoreCase("35 dias")){
			            	 pedido.setCodTabela(5);
			 			}
			             else if (pedido.getTabela().toString().equalsIgnoreCase("42 dias")){
			            	 pedido.setCodTabela(6);
			 			}
						    
						 
						  alerta2();
						
							  TelaCabecalho.pedido = pedido;
							//TelaVenda2.editar = true;
							//startActivity(new Intent(getApplicationContext(), TelaVenda2.class));
						    dialog.dismiss();
					}
					
				});
		
			//transmite o pedido .....
			trasmite.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					//  se o pedido ja foi trasmitido , questiona o usuario
					if (pedido.getStatus().equals("T")){
						alerta();
					}
					//  senao , enviao o pedido ....
					else {
						trasmitir();
						dialog.dismiss();
					}
					
				}
				
			});
			
			//  fecha a tela de itens 
			arquiva.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					AlertDialog.Builder alerta = new AlertDialog.Builder(TelaListaPedidoTrasmitido.this);
					alerta.setTitle("Deseja arquivar o pedido?");
					alerta.setIcon(R.drawable.bd);
					alerta.setMessage("");
					// método executado caso escolha sim
					alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							
							logica.atualizaStatus2( getApplicationContext(), pedido.getNumero());
							dialog.dismiss();
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
			
			 cancelar.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						dialog.dismiss();

					}
				});
			
			dialog.show();

	 
	 }
			
		
	 
	 

		//  método trasmite pedido 
		 public void trasmitir(){
				mprogressDialog = new ProgressDialog(this);
				mprogressDialog.setCancelable(true);
				mprogressDialog.setMessage("Trasmitindo pedido ...");

				// define o estilo como horizontal que nesse caso signifca que terá
				// barra de progressão/contagem
				mprogressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

				// reseta o progress para zero e em seguida define o valor máximo
				mprogressDialog.setProgress(0);
				mprogressDialog.setMax(1); // esse valor deverá ser definido de acordo
											// com sua necessidade

				// apresnta o progressbar
				mprogressDialog.show();
				
				    //  referencia para pegar o caminho file
			        file = this.getFileStreamPath("pedido.xml");
			       
			      

				new Thread() {
					public void run() {
						// criando instância de classe para executar um método gerar xml e enviar o pedido via ftp
						EnviaPedido envia = new EnviaPedido(getApplicationContext());
						  String[] vetor=  logica.buscaServidor(getApplicationContext());
						  String  ip =  vetor[0];
						  String  usuario = vetor[1];
						  String senha = vetor[2];
						  String diretorio = vetor[3];
						
						try {
							while (mprogressDialog.getProgress() < mprogressDialog.getMax()) {
								// esse é apenas um método de teste que pode ser
								// demorado
								retorno  = envia.Esperar(file,pedido,ip, usuario,senha,diretorio);
								mprogressDialog.incrementProgressBy(1);
								progresso = mprogressDialog.getProgress();
								setProgress(progresso);		
								
							}
						} catch (Exception e) {
							Log.e("tag", e.getMessage());
						} catch (Throwable e) {
						Toast.makeText(getApplicationContext(), "Erro ao finalizar", Toast.LENGTH_SHORT).show();
							e.printStackTrace();
						}

						// Exibe mensagem apenas informando o fim da execução da thread
						mhandler.post(new Runnable() {
							public void run() {
							  if (retorno ){
								Toast.makeText(getApplicationContext(), "Pedido(s) Enviado(s).",
										Toast.LENGTH_LONG).show();
								dialogs.show();
								
								
								//  manda setar no banco status de pedido enviado ..
								int count = logica.atualizaStatus(getApplicationContext(), pedido.getNumero());
								
								if (count > 0){	
									pedidos.get(1).setNomeCliente("Cledson");
									atualizaTela();
									onPause();
									
										
								}
			
								
							  }else{
								  Toast.makeText(getApplicationContext(), "Erro ao enviar o pedido!!",
											Toast.LENGTH_LONG).show();
							  }
							}
						});

						// encerra progress dialog
						mprogressDialog.dismiss();
					}
				}.start();
				
			}

			
			//  avisa o usuario caso o pedido ja tenha sido transmitido
			private void alerta(){
				AlertDialog.Builder alerta = new AlertDialog.Builder(this);
				alerta.setTitle("Aviso!");
				alerta.setIcon(R.drawable.trasmite32);
				alerta.setMessage("Pedido já foi transmitido. Deseja transmitir novamente ?");
				
				// método executado caso escolha não ..
				alerta.setNegativeButton("Não",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
									
								

							}
						});
				// método executado caso escolha sim
				alerta.setPositiveButton("Sim",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								
								// startActivity(new Intent(Menu.this,Menu.class));
								trasmitir();
								onPause();
								
							
							}
						});
				alerta.show();
				
			}
		

			private void atualizaTela(){
				
				handler.post(new Runnable(){
					public void run(){
						
						adapter = new AdaptadorItens(getApplicationContext(),pedidos);
						adapter.notifyDataSetChanged();
						setListAdapter(adapter);
						
						
					    dialogs.dismiss();
	
				}});
		
			}
		
			//  alerta excluir produto ... 
			 private void alerta2(){
			 	AlertDialog.Builder alerta = new AlertDialog.Builder(this);
			 	alerta.setTitle("Editar Pedido!");
			 	alerta.setIcon(R.drawable.alerta32);
			 	alerta.setMessage("O que deseja alterar ?");
			 	String s;
			 	
			 	// método executado caso escolha não ..
			 	alerta.setNegativeButton("Cabeçalho ",
			 			new DialogInterface.OnClickListener() {
			 				public void onClick(DialogInterface dialog, int which) {		
			 		startActivity(new Intent(getApplicationContext(), TelaCabecalho.class));
			 		onPause();

			 				}
			 			});
			 	// método executado caso escolha sim
			 	alerta.setPositiveButton("Alterar Itens ",
			 			new DialogInterface.OnClickListener() {
			 				public void onClick(DialogInterface dialog, int which) {
			 					
			 					TelaDigitacao.pedido = pedido;
			 					TelaDigitacao.editar = true;
							    startActivity(new Intent(getApplicationContext(), TelaDigitacao.class));
			 					
			 					// startActivity(new Intent(Menu.this,Menu.class));
			 					///trasmitir();
			 					
			 				
			 				}
			 			});
			 	alerta.show();
			 	
			 }



			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Toast.makeText(this, "Okk",Toast.LENGTH_SHORT).show();
				return false;
			}
			 
			
		
			
		
			
		
			 
		
					
		 
			
			

}
