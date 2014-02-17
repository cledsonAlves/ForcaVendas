package com.br.gui;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.br.adaptadores.AdaptadorProduto;
import com.br.kelma.R;
import com.br.logica.Logica;
import com.br.objetos.ItemPedido;
import com.br.objetos.Mask;
import com.br.objetos.Pedido;

public class TelaDigitacao extends Activity implements OnItemLongClickListener, OnItemClickListener{
	final int salvar = 1;
	final int salvarEditando= 3;
	final int sair = 2;
	private Handler handler = new Handler();
	
	public static EditText etPesquisa;
	public TextView textViewQtde,textViewVlTotal,textViewStatu;
	public static String filtro = "";
	public static int codigo = 0;
	private int qtde ;
	private int qtde2 = 0;
	private int posicao;
	private static boolean aviso = true;
	public static ArrayList<ItemListView> itens  = new ArrayList<ItemListView>();;


	public static Pedido pedido;

	public static boolean editar = false;
	private String titulo = "Confirma Venda? ";
	public static String nomeprod = "";
	public static String preco = "";
	private ArrayAdapter<String>  adaptador;
	private ItemListView itemAltera;
	
	ArrayList<String> listaItens;

	ProgressDialog dialog ;
      int count = 0;
	


	// //////////////////////////////////////////////////////////////////////////////////////
	AdaptadorProduto adapter;
	
	private ArrayList<String> listView;
	AdaptadorProduto adaptadorProduto;
	ListView lista;
	private Logica logica = new Logica();
	private  ItemListView item;
	

	@SuppressLint("ResourceAsColor")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// esconder o teclado ...
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.digitacao);
		
		textViewQtde = (TextView)findViewById(R.id.textViewQtde);
		textViewQtde.setText("0");
		textViewVlTotal = (TextView)findViewById(R.id.textViewVlTotal);
		textViewVlTotal.setText("0.0");
		
	
		item = new ItemListView();
        
		
		// Referencia para o layout de itens
		lista = (ListView) findViewById(R.id.listViewDigitacao);
		lista.setOnItemLongClickListener(this);
		lista.setOnItemClickListener(this);
		lista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		
	

		
		
	//  verifica se entrou em modo ediçao ou e pedido novo para limpar a lista de itens ....
		if (editar == true){
			titulo = "Confirmar Ediçao do Pedido ?";
			itens = logica.buscaItensPedido(this, pedido.getNumero());
			aviso = false;
		    calculaPedido();
		}else {
			// limpa o cache da lista de itens , caso ficou algo anteriormente ...
			itens.clear();
		
			
		}
		if (aviso){
			alerta2("Para começar a digitação , pressione a Lupa ou Scanner.");
			}

		
	
	}
	// Metodo chamado automaticamente para ao rotacionar a tela não chamar o oncreate, pois estava
	// apagando os itens do listview de itens..
	@Override  
    public void onConfigurationChanged(Configuration newConfig) {  
        super.onConfigurationChanged(newConfig);  
    } 

	public void onItemClick(AdapterView<?> arg0, View view, int pos,
			long arg3) {
		
		 itemAltera = itens.get(pos);
      
		
		ExibeDialog(item.getQuantidade());
		
	}
	
	   // busca o produto pelo codigo de QRCODE com o app barcode scanner
	  	public void btn_scanner(View v) {
	  		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
	  		intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
	  		startActivityForResult(intent, 0);
	  	}
		
		//  busca produto na tela produtos 

	  	// busca produto na tela produtos
	  	public void busca_Produto(View v) {
	  		//  manda o codigo da tabela para calcular preço dos produtos  ..... kmjn
	  		AdaptadorProduto.codigoTabela = pedido.getCodTabela();
          startActivity(new Intent(this, TelaProdutos2.class));
          
	  		
	  	}

		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
				int posicao, long arg3) {
			
			itemAltera = itens.get(posicao);
		
			alerta();
			return false;
		}
		
		private void alerta( ){
			AlertDialog.Builder alerta = new AlertDialog.Builder(this);
			alerta.setIcon(R.drawable.alerta16);
			alerta.setMessage("Deseja remover o item do pedido? ");
			String s;
			
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
							
							itens.remove(itemAltera);
							atualizaTela();
							
							// startActivity(new Intent(Menu.this,Menu.class));
							///trasmitir();
							
						
						}
					});
			alerta.show();
			
		}
	
	    
	      private void atualizaTela(){
	  		
	  		handler.post(new Runnable(){
	  			public void run(){
	  	
	  				listaItens = new ArrayList<String>();
	  				for(int i= 0; i < itens.size(); i++){
	  					listaItens.add(itens.get(i).getDescricao());
	  				}
	  		
	  				// Define o Adapter
	  				  adaptador = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, listaItens);
	  				  lista.setAdapter(adaptador);
	  				  
	  				  Log.i("salvando pedido ", "pedido salvo");
	  				
	  	     //  Atualiza a lista mesmo ....
	  	  /// itens.set(posicao, item);
	  			
	  	 calculaPedido();		
	  					
	  			
	  		}});
	  	
	       
	  	}
	      
	   // Salva pedido
	  	private boolean salvaPedido() {
                boolean salvou = false;
	  	
	  			//  cria os itens do pedido
	  			ArrayList<ItemPedido> listaItensPedido = new ArrayList<ItemPedido>();
	  			
	  			for (int i = 0; i < itens.size(); i++){
	  				
	  				ItemPedido it = new ItemPedido();
	  				it.setNumPedido(pedido.getNumero());
	  				it.setCodProduto(itens.get(i).getCodigo());
	  				it.setQuantidade(itens.get(i).getQuantidade());
	  				it.setVlUnitario(itens.get(i).getValorUnitario());
	  				it.setVlTotal(itens.get(i).getValorTotal());
	  			
	  				
	  				// adiciona o item na lista 
	  				listaItensPedido.add(it);
	  			}	
	  			
	  			//  acerta a tabela 
	  			 pedido.setCodTabela(AdaptadorProduto.codigoTabela);
	  			
	  			//  verifica se o pedido ja existe no banco  ---  correto seria fazer um update nos itens caso o pedido ja exista, voltar neste codigo depois
	  			if (logica.verificaPedido(this, pedido.getNumero())){
	                  //  exclui itens do pedido para gravar ele de novo com novos itens no banco ...  correto update ...
	  				if (logica.excluiItensPedido(this, pedido.getNumero())){
	  					salvou = logica.gravaPedido(pedido,listaItensPedido, this);
	  					Toast.makeText(this, "Pedido alterado com Sucesso !!!", Toast.LENGTH_SHORT).show();
	  				}else {
	  					Toast.makeText(this, "Pedido nao pode ser alterado informe o T.I de sua empresa : erro 4002!!", Toast.LENGTH_SHORT).show();
	  				}
	  				
	  			}else {
	  				//  caindo aqui sera pedido novo 
	  				salvou = logica.gravaPedido(pedido,listaItensPedido, this);
	  			
	  			}
	  			
	  			// dorme 1 segundo ....
	  			try {
	  				Thread.sleep(1000);
	  			} catch (InterruptedException e) {
	  				e.printStackTrace();
	  			}
	  			this.finish();
	  			return salvou;

	  	}
	  	
	/**	 private void atualizaTela(){
		  		
		  		handler2.post(new Runnable(){
		  			public void run(){
		  		
			
		  		}});
		  	
		       
		  	}**/

	
		// método  calcula o pedido 
		private void calculaPedido(){
		  double valor = 0;
		  int qtd = 0;
	
			for (int i = 0; i < itens.size(); i++) {
			   
					valor =  itens.get(i).getValorTotal() +  valor  ;
					qtd =  itens.get(i).getQuantidade() + qtd;
					verificaStatus();
				}
			
			textViewQtde.setText(""+ qtd);
			textViewVlTotal.setText(logica.limitaCasa(valor));
			qtd = 0;
			
								
			
		} 
		
	//  telinha para adcionar itens ...
		private void ExibeDialog(final int i) {
			
			final Dialog dialog = new Dialog(this);
			dialog.requestWindowFeature(Window.FEATURE_PROGRESS);
			dialog.setContentView(R.layout.itens);
			
			
			
			final EditText quantidade = (EditText) dialog.findViewById(R.id.editTextQtdIt);
			quantidade.addTextChangedListener(Mask.insert("####",quantidade));
			quantidade.setText(String.valueOf(itemAltera.getQuantidade()));
			// instancia os objetos que estão no layout customdialog.xml
		     final ImageButton mais = (ImageButton) dialog.findViewById(R.id.imageButtonAdd);
			 final ImageButton menos = (ImageButton) dialog.findViewById(R.id.imageButtonRem);
			 final Button volta = (Button) dialog.findViewById(R.id.buttonVolta);
			 final Button altera = (Button) dialog.findViewById(R.id.buttonAtera);
			 
				
				//  fecha a tela de itens 
					mais.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							qtde2 = itemAltera.getQuantidade();
							qtde2 ++;
							quantidade.setText(String.valueOf(qtde2));
							

						}
									
					});
				//  fecha a tela de itens 
					menos.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							int in = i;
							qtde2 = itemAltera.getQuantidade();
							if (qtde2 >1){
							qtde2 --;
							quantidade.setText(String.valueOf(qtde2));
								
							
							}

						}
									
					});
					
					 // fecha a tela de itens 
						volta.setOnClickListener(new View.OnClickListener() {
							public void onClick(View v) {
								
								dialog.dismiss();

							}
										
						});
						
						// 
						altera.setOnClickListener(new View.OnClickListener() {
							public void onClick(View v) {
							//  erro cruelllll
								if (!quantidade.getText().toString().equals("")){
								
								
								itemAltera.setQuantidade(Integer.parseInt(quantidade.getText().toString()));
								itemAltera.setValorTotal(itemAltera.getValorUnitario()* itemAltera.getQuantidade());
							   calculaPedido();
							   verificaStatus();
								dialog.dismiss();
								} else {
									Toast.makeText(getBaseContext(), "Informe uma quantidade !",Toast.LENGTH_SHORT).show();
								}

							}
										
						});
		
			

	 


			// exibe na tela o dialog
			dialog.show();
		}
		///  menu suspenso ...
		@Override
		public boolean onCreateOptionsMenu(android.view.Menu menu) {
			super.onCreateOptionsMenu(menu);
			MenuItem item;

			// sub menu cadastro
			SubMenu subCad = menu.addSubMenu("Salvar");
			subCad.setIcon(R.drawable.salvar64);
			item = subCad.add(0, salvarEditando, 0, "Salvar Pedido");
			item = subCad.add(0, salvar, 0, "Finalizar Venda ");
			
			SubMenu subCad3 = menu.addSubMenu("Alterar Pedido");
			subCad3.setIcon(R.drawable.altera64);
			
			
			
			
			SubMenu subCad2 = menu.addSubMenu("Cancelar");
			subCad2.setIcon(R.drawable.exclui64);
			item = subCad2.add(0, sair, 0, "Cancelar a Venda ");
			

		
		

			return true;
		}
		
		
		///  Menu suspenso da tela  
		@Override
		public boolean onMenuItemSelected(int featureId, MenuItem item) {
			switch (item.getItemId()) {
			//  cadastra cliente
			case salvar:
				
				if (salvaPedido()){
				//  salva o pedido e o abre em seguida .....
					Toast.makeText(getApplicationContext(), "Pedido salvo com sucesso!!", Toast.LENGTH_SHORT)
	  				.show();
				}else{
				//  salva o pedido e o abre em seguida .....
					Toast.makeText(getApplicationContext(), "Erro ao salvar o pedido!!", Toast.LENGTH_SHORT)
	  				.show();
				}
				return true;
			case sair:
				
				this.finish();
				return true;
			case salvarEditando:
				
				AlertDialog.Builder alerta = new AlertDialog.Builder(this);
				alerta.setTitle("Seu pedido será salvo, e você deve clicar na tela pedidos, em editar pedido para continuar");
				alerta.setIcon(R.drawable.informativo);
				// alerta.setMessage("Deseja s?");
				// método executado caso escolha sim
				alerta.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						 if (salvaPedido()){
							//  salva o pedido e o abre em seguida .....
								Toast.makeText(getApplicationContext(), "Pedido salvo com sucesso!!", Toast.LENGTH_SHORT)
				  				.show(); 
						 }else{
							//  salva o pedido e o abre em seguida .....
								Toast.makeText(getApplicationContext(), "Erro ao salvar o pedido!!", Toast.LENGTH_SHORT)
				  				.show();
						 }
						
					
						  
						   
						   
						  	
					
					
					}
				});
				
				alerta.setNegativeButton("cancela", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						aviso = false;
					
					
					}
				});
				alerta.show();
			 
				
				
		
		}
			
			return true;
		}
		
		
	//  desabilitando o botão voltar ..... 
		@Override
	    public void onBackPressed() {
	     Toast.makeText(this,"Para sair, pressione o menu e depois cancelar ",Toast.LENGTH_SHORT).show();
	    }

		 @Override
			// quando entrar em pause finaliza ..
			protected void onStart() {
				super.onStart();
				// Define o Adapter
				
				  atualizaTela();
				 
	
				}
		 @Override
			// quando entrar em pause finaliza ..
			protected void onDestroy() {
				super.onDestroy();
			
					itens.clear();

		 }
		 
		 //  verifica status para mandar alertas ...
		 public void verificaStatus(){
	
		 }
		 
		 private void alerta2(String titulo){
			AlertDialog.Builder alerta = new AlertDialog.Builder(this);
			alerta.setTitle(titulo);
			alerta.setIcon(R.drawable.informativo);
			// alerta.setMessage("Deseja s?");
			// método executado caso escolha sim
			alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					
				
				
				}
			});
			
			alerta.setNegativeButton("Ok! já aprendi", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					aviso = false;
				
				
				}
			});
			alerta.show();
		 }

			
	
}
