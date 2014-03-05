package com.br.gui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.br.adaptadores.AdaptadorPedido;
import com.br.adaptadores.AdaptadorProduto;
import com.br.baseDados.ManipulaBanco;
import com.br.kelma.R;
import com.br.logica.Logica;
import com.br.objetos.ItemPedido;
import com.br.objetos.Pedido;
import com.br.objetos.Produto;

public class TelaVenda2 extends Activity {
	private Logica logica = new Logica();
	private EditText edNomeProd, edPreco, edQtd, edTotalPedido, edTotalItens,edDescIten;
	private static final int sim = 1;
	public static ArrayList<ItemListView> itens  = new ArrayList<ItemListView>();;
	public static List<ItemListView> itensSelecionados ;
	private ListView listaItens;
	private int codigoProduto;
	private double totalPedido = 0;
	private int totalItens = 0;
	private AdaptadorPedido adaptadorPedido;
	private ItemListView item;
	private int posicao;
	public static Pedido pedido;
	private int qtde ;
	public static boolean editar = false;
	private String titulo = "Confirma Venda? ";
	public static String nomeprod = "";
	public static String preco = "";
	public static int codigo = 0;
	ProgressDialog dialog ;
	private Handler handler = new Handler();
	private   double  percent = 0.021;
	private Button btnVista, btn14, btn21,btn28,btn35,btn42;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// esconder o teclado ...
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.teste2);

		// Referencia para o layout de itens
		listaItens = (ListView) findViewById(R.id.listViewItens);
		listaItens.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		

		
		
		dialog = new ProgressDialog(this);
		dialog.setMessage("Recalculando o pedido, aguarde ...");
		
	
		// faz o listview andar (scrool)
	/**	listaItens.setOnTouchListener(new ListView.OnTouchListener() 
		{ 
			public boolean onTouch(View v, MotionEvent event)
		{ 
				int action = event.getAction(); switch (action) 
		{ 
				case MotionEvent.ACTION_DOWN: // Disallow ScrollView to intercept touch events. 
			v.getParent().requestDisallowInterceptTouchEvent(true); break; case MotionEvent.ACTION_UP: // Allow ScrollView to intercept touch events. 
				v.getParent().requestDisallowInterceptTouchEvent(false); break; } // Handle ListView touch events. 
		v.onTouchEvent(event); return true; } }); **/
		
		
		

		edNomeProd = (EditText) findViewById(R.id.editTextNomeProd);
		edNomeProd.setEnabled(false);
		edNomeProd.setText(nomeprod);
		
		btnVista = (Button)findViewById(R.id.ButtonVista);
		btnVista.setEnabled(false);
		btn14 = (Button)findViewById(R.id.button14);
        btn14.setEnabled(false);
		btn21 = (Button)findViewById(R.id.button21);
		btn21.setEnabled(false);
		btn28 = (Button)findViewById(R.id.button28);
		btn28.setEnabled(false);
		btn35 = (Button)findViewById(R.id.button35);
		btn35.setEnabled(false);
		btn42 = (Button)findViewById(R.id.button42);
		btn42.setEnabled(false);
		
	   // verificaTabela();
		
		
		
		
		edQtd = (EditText) findViewById(R.id.editTextQtd);
		edQtd.setText("");
	
		
		edPreco = (EditText) findViewById(R.id.editTextPreco);
		edPreco.setText(preco);
		edPreco.setEnabled(false);
		
		 Processo processo= new Processo();  
		//mando executar o processo  
		processo.execute("Executando"); 
		
		
	    

	
		//  verifica se entrou em modo ediçao ou e pedido novo para limpar a lista de itens ....
		if (editar == true){
			titulo = "Confirmar Ediçao do Pedido ?";
			itens = logica.buscaItensPedido(this, pedido.getNumero());
			somaItens(itens);
		}else {
			// limpa o cache da lista de itens , caso ficou algo anteriormente ...
			itens.clear();
		}
		edTotalPedido = (EditText) findViewById(R.id.editTextTotalPedido);
		edTotalPedido.setText(logica.limitaCasa(totalPedido));
		edTotalPedido.setEnabled(false);
		
		edTotalItens = (EditText) findViewById(R.id.editTextTotalItens);
		edTotalItens.setText(String.valueOf(totalItens));
		edTotalItens.setEnabled(false);

		itensSelecionados = new ArrayList<ItemListView>();
		// Cria o adapter
		adaptadorPedido = new AdaptadorPedido(this, itens);

		// Define o Adapter
		listaItens.setAdapter(adaptadorPedido);

		editar = false;

	}
	// Metodo chamado automaticamente para ao rotacionar a tela não chamar o oncreate, pois estava
	// apagando os itens do listview de itens..
	@Override  
    public void onConfigurationChanged(Configuration newConfig) {  
        super.onConfigurationChanged(newConfig);  
    } 

	

	/**
	 * Ações de Botoes
	 * 
	 * @param v
	 */
	//  incrementa quantidade
	public void btn_mais(View v){
		qtde ++;
		edQtd.setText(String.valueOf(qtde));
	}
	//decrementa quantidae itens
	public void btn_menos(View v){
		if (qtde > 1){
		
			qtde --;
			edQtd.setText(String.valueOf(qtde));
		}
	}
	
	

	// adiciona item ao pedido
	public void btn_adciona(View v) {
		// se o campo quantidade , preço , ou item vazio
		if (edQtd.getText().toString().equals("")|| edNomeProd.getText().toString().equals("")
				|| edPreco.getText().toString().equals("")) {
			// tratar açao
			Toast.makeText(this, "Existe campos vazios", Toast.LENGTH_SHORT)
					.show();
		} else {
			// manda adicionar o item ao pedido
			adcionaItem();
		//  faz a lista crescer de acordo com a quantidade de itens ( scroll)
			//calculeHeightListView();
			listaItens.requestFocus();

			

		}

	}
	
	

	// adciona item ao pedido ....
	private void adcionaItem() {
		String descricao = edNomeProd.getText().toString();
		int qtd = Integer.parseInt(edQtd.getText().toString());
         
		String vlUni =edPreco.getText().toString();
		
		 // String pr = logica.calculaTabela(pedido.getCodTabela(), preco);
		
		//edPreco.setText(pr.replace(",", "."));
		
		double valorUnitario = Double.parseDouble(edPreco.getText().toString());
        
	
		// soma o valor total do item
		double valorTotal = (valorUnitario * qtd);


		// Cria o item ;
		item = new ItemListView();
		item.setDescricao(descricao);
		item.setQuantidade(qtd);
		item.setValorUnitario(valorUnitario);
		item.setValorTotal(valorTotal);
		item.setCodigo(codigoProduto);

		// verifica se o item já esta no pedido
		if (verificaLista(itens, item) == false) {
			// adiciona na array lista
            
			//  adiciona o item novo na lista de itens.
			itens.add(item);
			
			listaItens.requestFocus();
			// ............................
			// percorre a lista toda de pedido somando os itens
			somaItens(itens);

			// atualiza o campo total pedido
			String valor = logica.limitaCasa(totalPedido);
			edTotalPedido.setText(valor);
			edTotalItens.setText(String.valueOf(totalItens));
			
			

			// notifica a lista de itens do item novo
			adaptadorPedido.notifyDataSetChanged();
			// limpa a lista
			itensSelecionados.clear();
			// limpa os campos
			edNomeProd.setText("");
			qtde = 1;
			edQtd.setText("");
			
			edPreco.setText("");
			nomeprod = "";
			preco = null;
		
			
			
			
		//	edDescIten.setText("");
		//	edNomeProd.requestFocus();
		} else {
			// senão, o item já esta no pedido e abre dialogo para confirmar incremento 
			confirmaIncremento();

		}
	}
	
	// Verifica se um determinado item esta na lista .....
	private boolean verificaLista(ArrayList<ItemListView> lista,ItemListView item) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getCodigo() == item.getCodigo()) {
				posicao = i ;
				return true;
			}
		}
		return false;

	}
	
	//dialogo confirma incremento no pedido 
	   private void confirmaIncremento() {

			AlertDialog.Builder alerta = new AlertDialog.Builder(this);
			alerta.setTitle("Item já incluso no pedido! Deseja somar ao pedido?");
			alerta.setIcon(R.drawable.informativo);
			alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// incrementa o item do  pedido.
					incrementaIten(itens, posicao);
					qtde = 1;
					edQtd.setText("");
					nomeprod = "";
					preco = "";

				}
			});
			// método executado caso escolha não ..
			alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// limpa os campos
					edNomeProd.setText("");
					
					qtde = 1;
					edQtd.setText("");
					edPreco.setText("");
				//	edDescIten.setText("");
					edNomeProd.requestFocus();
					nomeprod = "";
					preco = "";
				}
			});
			alerta.show();
		}
	   
	   
		// método incrementa item do pedido 
		private void incrementaIten(ArrayList<ItemListView> lista,int posicao){
			
			int qtdNova = lista.get(posicao).getQuantidade()+ item.getQuantidade();
			double vlNovo = lista.get(posicao).getValorTotal()+ item.getValorTotal();

			item.setQuantidade(qtdNova);
			item.setValorTotal(vlNovo);

			// limpa os campos
			edNomeProd.setText("");
			qtde = 1;
			edQtd.setText("");
			edPreco.setText("");
		//	edDescIten.setText("");
			edNomeProd.requestFocus();

			// atualiza a lista
			lista.set(posicao, item);

			// notifica a lista de itens do item novo
			adaptadorPedido.notifyDataSetChanged();

			// percorre a lista somando os itens
			somaItens(itens);

			// atualiza os campos da tela
			edTotalItens.setText(String.valueOf(totalItens));
			// atualiza o campo total pedido
			String valor = logica.limitaCasa(totalPedido);
			edTotalPedido.setText(valor);
			listaItens.requestFocus();
			
		}

	// remove item da lista de pedido
	public void btn_excluiItem(View v) {
		removeItem();
	//  faz a lista crescer de acordo com a quantidade de itens ( scroll)
		//calculeHeightListView();
		listaItens.requestFocus();
	}
	
	// cancela venda 
	public void btn_cancela(View v){
	if (editar == true){
		alertaCancela("Cancelar Ediçao do Pedido ?");
	}else {
		alertaCancela("Cancelar Venda ?");	
	}

	}

	
	// confirma venda
	public void btn_confirmaVenda(View v) {
      
		alertaConfirma(titulo);
		
	}
	
	
	public void alertaCancela(String titulo){

		AlertDialog.Builder alerta = new AlertDialog.Builder(this);
		alerta.setTitle(titulo);
		alerta.setIcon(R.drawable.exclui64);
		// alerta.setMessage("Deseja s?");
		// método executado caso escolha sim
		alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// chama método salvar pedido ..
				finish();
			}
		});
		// método executado caso escolha não ..
		alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// startActivity(new Intent(Menu.this,Menu.class));
			}
		});
		alerta.show();
	}
	
	//  alerta confirma ,,,,,
	public void alertaConfirma(String titulo){
		AlertDialog.Builder alerta = new AlertDialog.Builder(this);
		alerta.setTitle(titulo);
		alerta.setIcon(R.drawable.salvar64);
		// alerta.setMessage("Deseja salvar o pedido ?");
		// método executado caso escolha sim
		alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// chama método salvar pedido ..
				salvaPedido();
				listaItens.requestFocus();
			}
		});
		// método executado caso escolha não ..
		alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// startActivity(new Intent(Menu.this,Menu.class));
				listaItens.requestFocus();
			}
		});
		alerta.show();
	}
	
	
	

	// Salva pedido
	private void salvaPedido() {

	
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
					logica.gravaPedido(pedido,listaItensPedido, this);
					Toast.makeText(this, "Pedido alterado com Sucesso !!!", Toast.LENGTH_SHORT).show();
				}else {
					Toast.makeText(this, "Pedido nao pode ser alterado informe o T.I de sua empresa : erro 4002!!", Toast.LENGTH_SHORT).show();
				}
				
			}else {
				//  caindo aqui sera pedido novo 
				logica.gravaPedido(pedido,listaItensPedido, this);	
				Toast.makeText(this, "Venda Finalizada!!", Toast.LENGTH_SHORT)
				.show();
			}
			
			// dorme 1 segundo ....
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.finish();
		
	}

	// Remove o item da lista
	private void removeItem() {
		// percorre a lista de itens para remoção .
		for (int i = 0; i < itensSelecionados.size(); i++) {
			itens.remove(itensSelecionados.get(i));
			adaptadorPedido.notifyDataSetChanged();
		}
		// limpa a lista
		itensSelecionados.clear();
		listaItens.requestFocus();

		// ............................
		// percorre a lista toda de pedido somando os itens
		somaItens(itens);

		// atualiza o campo total pedido
		String valor = logica.limitaCasa(totalPedido);
		edTotalPedido.setText(valor);
		edTotalItens.setText(String.valueOf(totalItens));
	}

	// Método percorre a lista de itens somando os valores
	private void somaItens(ArrayList<ItemListView> lista) {
		int qtdItens = 0;
		double vlTotal = 0;

		for (int i = 0; i < lista.size(); i++) {
			qtdItens = qtdItens + lista.get(i).getQuantidade();
			vlTotal = vlTotal + lista.get(i).getValorTotal();
		}
		
		edTotalPedido.setText((logica.limitaCasa(vlTotal)));
		edTotalItens.setText(String.valueOf(qtdItens));

		totalPedido = vlTotal;
		totalItens = qtdItens;
		
		

	}
	//  soma a lista , alterando os preços de acordo com a tabela ..

	// método incrementa item do pedido 
	private void alteraTabela(ArrayList<ItemListView> lista,int tabela){
		
		 ArrayList<ItemListView> itensNovo = new ArrayList<ItemListView>();
		// ArrayList<ItemListView> itensTemp = itens;
		 ManipulaBanco mb = new ManipulaBanco(this);
		 
			for (int i = 0; i < itens.size(); i++) {
				
				ItemListView item = new ItemListView();
				
				item.setDescricao(lista.get(i).getDescricao());
				item.setQuantidade(lista.get(i).getQuantidade());
				item.setCodigo(itens.get(i).getCodigo());
				
				//  busca o preço original do produto no banco...  lembrar de mandar isso pra logica depois
				double vlOriginal = mb.buscaPreco(itens.get(i).getCodigo());
	
				double precoNovo =   vlOriginal *  Math.pow(1  + percent, (tabela -1));
				
				double vlTotal =  precoNovo * itens.get(i).getQuantidade();
				
				item.setValorUnitario(precoNovo);
				item.setValorTotal(vlTotal);
						
				itensNovo.add(item);
			}
			
			itens = itensNovo;
			
			// Cria o adapter
			adaptadorPedido = new AdaptadorPedido(this, itens);

			// Define o Adapter
	    	listaItens.setAdapter(adaptadorPedido);
			
	    	
			//itens.clear();
			
			
		// limpa os campos
		edNomeProd.setText("");
		qtde = 1;
		edQtd.setText("");
		edPreco.setText("");
	//	edDescIten.setText("");
		edQtd.requestFocus();

		// atualiza a lista
	//	lista.set(posicao, item);

		// notifica a lista de itens do item novo
		adaptadorPedido.notifyDataSetChanged();

		// percorre a lista somando os itens
		somaItens(itens);

		// atualiza os campos da tela
		edTotalItens.setText(String.valueOf(totalItens));
		// atualiza o campo total pedido
		String valor = logica.limitaCasa(totalPedido);
		edTotalPedido.setText(valor);
		
	}
	
	
	//  fazendo o listview scroll (andar)
    private void calculeHeightListView() {  
        int totalHeight = 0;  
  
        ListAdapter adapter = listaItens.getAdapter();  
        int lenght = adapter.getCount();  
  
        for (int i = 0; i < lenght; i++) {  
            View listItem = adapter.getView(i, null, listaItens);  
            listItem.measure(0, 0);  
            totalHeight += listItem.getMeasuredHeight();  
        }  
  
        ViewGroup.LayoutParams params = listaItens.getLayoutParams();  
        params.height = totalHeight  
                + (listaItens.getDividerHeight() * (adapter.getCount() - 1));  
        listaItens.setLayoutParams(params);  
        listaItens.requestLayout();  
    } 
    


	
	//  desabilitando o botão voltar ..... 
	@Override
    public void onBackPressed() {
     Toast.makeText(this,"Para sair, pressione o botão cancelar abaixo",Toast.LENGTH_SHORT).show();
    }
	  //Você pode cria uma classe privada dentro da sua classe principal, no caso a sua Activity  
    private class Processo extends AsyncTask<String, String, String>{  
    //Método que é responsável por executar a sua tarefa que vai demorar um pouco  
    @Override  
    protected String doInBackground(String... params) {  
              //aqui eu faço um while so para demonstração, mais você retira esse codigo e coloca o seu.  
              int i=0;  
              while(i== 0){  
                 //aqui ele vai "falar" para  metodo onProgressUpdate para atualizar a tela com a sua string  
                 publishProgress("Processo em: "+i);  
                 try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
                   
              }  
      
             return null;  
    } 
    
      
                    @Override  
            protected void onProgressUpdate(String... values) {  
                          //Faz o setText no seu textView da tela   
                    	edNomeProd.setText(nomeprod); 
                    	edPreco.setText(preco);
                    	codigoProduto = codigo;
                    	if (!edNomeProd.getText().toString().equals("")){
                    		edQtd.requestFocus();
                    		
                    	}
                  
                  
                    	
                    } 
       
    
    
    }
    
    
    //  açoes dos botoes de tabela  
      public void  btn_vista(View v) throws InterruptedException{
    	  dialog.show();
    	 
    	  AdaptadorProduto.codigoTabela = 1;
    	  pedido.setCodTabela(1);
    	  alteraTabela(itens,1);
    	  pedido.setCodTabela(1);
    	  atualizaTela();
    	  
 	    
 	 
 	
 }
      //  açoes dos botoes de tabela  
      public void  btn_14d(View v){
    	  dialog.show();
    	  AdaptadorProduto.codigoTabela = 2;
    	  pedido.setCodTabela(2);
    	  pedido.setCodTabela(2);
    	  alteraTabela(itens,2);
    	  atualizaTela();
    	  
 	    
 	 
 	
 }
      public void  btn_21d(View v){
    	  dialog.show();
    	  AdaptadorProduto.codigoTabela = 3;
    	  pedido.setCodTabela(3);
    	  alteraTabela(itens,3);
    	  atualizaTela();
    	  
 	    
 	 
 	
 }
      public void  btn_28d(View v){
    	  dialog.show();
    	  AdaptadorProduto.codigoTabela = 4;
    	  pedido.setCodTabela(4);
    	  alteraTabela(itens,4);
    	  atualizaTela();
	 
 	
 }
      public void  btn_35d(View v){
    	  dialog.show();
    	  AdaptadorProduto.codigoTabela = 5;
    	  pedido.setCodTabela(5);
    	  alteraTabela(itens,5);
    	  atualizaTela();
    	  
 	    
 	 
 	
 }
      public void  btn_42d(View v){
    	  dialog.show();
    	  AdaptadorProduto.codigoTabela = 6;
    	  pedido.setCodTabela(6);
    	  alteraTabela(itens,6);
    	  atualizaTela();
    	  
 	    
 	 
 	
 }
      
      private void atualizaTela(){
  		
  		handler.post(new Runnable(){
  			public void run(){
  			verificaTabela();
  			dialog.dismiss();
  	     listaItens.requestFocus();
  	     //  Atualiza a lista mesmo ....
  	   itens.set(posicao, item);
  				
  				
  					
  			
  		}});
  	
       
  	}
      
      //  verifica qual tabela modificando os campos ....
      public void verificaTabela(){
    		switch(pedido.getCodTabela()){
    		case 1 : btnVista.setEnabled(false);
    		btn14.setEnabled(true);
    		btn21.setEnabled(true);
    		btn28.setEnabled(true);
    		btn35.setEnabled(true);
    		btn42.setEnabled(true);
    		break;
    		case 2 : btn14.setEnabled(false);
    		btnVista.setEnabled(true);
    		btn21.setEnabled(true);
    		btn28.setEnabled(true);
    		btn35.setEnabled(true);
    		btn42.setEnabled(true);
    		break;
    		case 3 : btn21.setEnabled(false);
    		btnVista.setEnabled(true);
    		btn14.setEnabled(true);
    		btn28.setEnabled(true);
    		btn35.setEnabled(true);
    		btn42.setEnabled(true);
    		break;
    		case 4 : btn28.setEnabled(false);
    		btnVista.setEnabled(true);
    		btn14.setEnabled(true);
    		btn21.setEnabled(true);
    		btn35.setEnabled(true);
    		btn42.setEnabled(true);
    		break;
    		case 5 : btn35.setEnabled(false);
    		btnVista.setEnabled(true);
    		btn14.setEnabled(true);
    		btn21.setEnabled(true);
    		btn28.setEnabled(true);
    		btn42.setEnabled(true);
    		break;
    		case 6 : btn42.setEnabled(false);
    		btnVista.setEnabled(true);
    		btn14.setEnabled(true);
    		btn21.setEnabled(true);
    		btn28.setEnabled(true);
    		btn35.setEnabled(true);
    		break;
    		
    		
    		}
      }
      

      
   // busca o produto pelo codigo de QRCODE com o app barcode scanner
  	public void btn_scanner(View v) {
  		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
  		intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
  		startActivityForResult(intent, 0);
  	}

  	// busca produto na tela produtos
  	public void btn_buscaProduto(View v) {
  		//  manda o codigo da tabela para calcular preço dos produtos  ..... 
  		AdaptadorProduto.codigoTabela = pedido.getCodTabela();
  		
  		startActivity(new Intent(this, TelaProdutos2.class));
  		
  		

  		
  		
  	}
  		
  	

  	// resultado da busca na tela produtos
  	@Override
  	protected void onActivityResult(int cod, int resultado, Intent it) {
  		Bundle params = it != null ? it.getExtras() : null;
  		if (params != null) {
  		
  	
  			String descricao = params.getString("descricao");
  			
  	
  			
  			//double preco = params.getDouble("preco");
  		
  			
  			//  código critico teste, calcular a tabela de preço, voltar aqui depois ...
  			
  			if(pedido.getCodTabela() == 1){
  			
  			}
  			else {
  		 //  String pr = logica.calculaTabela(pedido.getCodTabela(), preco);
  			
  			//edPreco.setText(pr.replace(",", "."));
  			}
  		//	edDescIten.setText("");

  		}
  		// pega o resultado do barcode scanner
  		if (cod == 0) {
  			if (resultado == RESULT_OK) {
  				String contents = it.getStringExtra("SCAN_RESULT");
  				String format = it.getStringExtra("SCAN_RESULT_FORMAT");
  				String texto[] = contents.split(";");
  				edNomeProd.setText(texto[0]);
  				
  				codigo = Integer.parseInt(texto[0]);
  				
  				//  busca o produto no banco ...
  				ManipulaBanco mb = new ManipulaBanco(this);
  				Produto p = mb.buscaProduto(codigo);
  				
  			     
  			     if (pedido.getCodTabela() == 1){
  			    	String pr = String.valueOf(p.getPreco());
  			    	preco = pr.replace(",", ".");
  			     }else{
  			    	 //  senao calcula a tabela de preço...
  			    	String pr = logica.calculaTabela(AdaptadorProduto.codigoTabela, p.getPreco());
  			    	preco = pr.replace(",", ".");
  			    	
  			    //
  	  				 
  			     }
  			     
  			     
  			     
  				
  				
  				nomeprod = p.getDescricao();
  				//  código critico teste, calcular a tabela de preço, voltar aqui depois ...
  				//double valor = Double.parseDouble(texto[6]);
  				//String pr = logica.calculaTabela(pedido.getCodTabela(), valor);
  				
  			///	edPreco.setText(pr.replace(",", "."));
  			//	codigoProduto = Integer.parseInt(texto[0]);
  				// Handle successful scan
  			} else if (resultado == RESULT_CANCELED) {
  				// Handle cancel
  			}
  		}

  	}
    
    @Override
	// quando entrar em pause finaliza ..
	protected void onStart() {
		super.onStart();
		somaItens(itens);
		//calculeHeightListView();
		edQtd.requestFocus();
		// atualiza a lista
		// atualiza a lista
		
		
		// notifica a lista de itens do item novo
		adaptadorPedido.notifyDataSetChanged();
		//dialog.dismiss();
	   // finish();	
		}
	


}

