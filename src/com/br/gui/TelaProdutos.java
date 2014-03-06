package com.br.gui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.br.adaptadores.AdaptadorProduto;
import com.br.baseDados.ManipulaBanco;
import com.br.kelma.Menu;
import com.br.kelma.R;
import com.br.logica.Logica;
import com.br.objetos.Produto;

public class TelaProdutos extends Activity  implements OnItemLongClickListener{
	public static EditText etPesquisa;
	public static String filtro = "";
	public static int codigo = 0;
	int count = 0;
	private boolean on = false;
	private   double  percent = 0.021;
	private int codigoExcluir;
	private Produto produtoRemove;

	private ArrayList<Produto> novaLista = new ArrayList<Produto>();

	// //////////////////////////////////////////////////////////////////////////////////////
	AdaptadorProduto adapter;
	public  static List<Produto> listaProdutos;
	private  List<Produto> listaTemp;
	AdaptadorProduto adaptadorProduto;
	ListView listaItens;
	private Logica logica;
	private Button btnVista,btn14d, btn21d,btn28d,btn35d,btn42d;
	private Handler handler = new Handler();
	ProgressBar progressBar1;
	ProgressDialog dialog ;

	private ArrayAdapter<String> adaptador1;
	private double preco [];
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.consulta);
		
		dialog = new ProgressDialog(this);
		dialog.setMessage("Atualizando Preços, aguarde ...");
		
		
		//  
		logica = new Logica();
		
		
		progressBar1 = (ProgressBar)findViewById(R.id.progressBar1);
		
		
		listaItens = (ListView) findViewById(R.id.lvEstados);
		etPesquisa = (EditText) findViewById(R.id.etProcurar);
		etPesquisa.addTextChangedListener(filterTextWatcher);
		
		
		
		btnVista = (Button)findViewById(R.id.btnVista);
		btnVista.setEnabled(false);
		btn14d = (Button)findViewById(R.id.btn14d);
		btn21d = (Button)findViewById(R.id.btn21d);
		btn28d = (Button)findViewById(R.id.btn28d);
		btn35d = (Button)findViewById(R.id.btn35d);
		btn42d = (Button)findViewById(R.id.btn42d);
		
	
		
		
	     //  lista temporaria para guardar os valores originais ..
		 preco  = new double[ listaProdutos.size()];
		for(int i = 0; i < listaProdutos.size(); i++){
			 preco[i] = listaProdutos.get(i).getPreco();
		}
		
		
		listaTemp = new ArrayList<Produto>();
		
		for(int i = 0; i < listaProdutos.size(); i++){
			listaTemp.add(listaProdutos.get(i));
		}
		
	       //  ajuste o preço para a vista 
	       AdaptadorProduto.codigoTabela = 1;

		adapter = new AdaptadorProduto(this,listaTemp);
		listaItens.setAdapter(adapter);
		
	

		// //////////////////////////////////////////////////////
		etPesquisa.setText(filtro);
		etPesquisa.selectAll();
		etPesquisa.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
		
		//  ocultando o teclado 
		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(etPesquisa.getWindowToken(), 0);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		
	
		listaItens.setOnItemLongClickListener(this);

		listaItens.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView arg0, View view, int position,
					long index) {
				filtro = etPesquisa.getText().toString();
				

				// se for feito o filtro pega na nova lista ...
				if (novaLista.size() > 0) {
					Produto p = novaLista.get(position);
					String nome = p.getDescricao();
					 codigo = p.getCodigo();

		
					
					Intent it = new Intent(TelaProdutos.this,TelaFoto.class);
					
					Bundle b = new Bundle();  
				      
				    b.putInt("codigo", p.getCodigo());  
				      
				    it.putExtras(b);  
					
					startActivity(it);
					on = true;
					
				

					// senão, pega na lista normal
				} else {
					Produto p = listaTemp.get(position);
					String nome = p.getDescricao();
					 codigo = p.getCodigo();

		           Intent it = new Intent(TelaProdutos.this,TelaFoto.class);
					
					Bundle b = new Bundle();  
				      
				    b.putInt("codigo", p.getCodigo());  
				      
				    it.putExtras(b);  
					
					startActivity(it);
					on = true;
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

			adapter = new AdaptadorProduto(TelaProdutos.this, novaLista);
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
		
			
		for (int i = 0; i < listaTemp.size(); i++) {
			if (listaTemp.get(i).getDescricao().contains(pesquisa)) {
				novaLista.add(listaTemp.get(i));

			} else if (String.valueOf(listaTemp.get(i).getCodigo())
					.contains(etPesquisa.getText().toString())) {
				novaLista.add(listaTemp.get(i));

			} 

		}
           }catch(NumberFormatException e){
			this.finish();
		}

	}
	//  açoes dos botoes de plano/ preço nos produtos
	public void btn_vista(View v){
		btnVista.setEnabled(false);
		btn14d.setEnabled(true);
		btn21d.setEnabled(true);
		btn28d.setEnabled(true);
		btn35d.setEnabled(true);
		btn42d.setEnabled(true);
	
		for(int i = 0; i < listaTemp.size(); i++){
		listaTemp.get(i).setPreco(preco[i]);
		}
		
		
		
		
	//  recalculando os preços dos produtos  .....
		
		atualizaTela();
	
				
	}
	public void btn_14d(View v){		
		btn14d.setEnabled(false);
		btnVista.setEnabled(true);
		btn21d.setEnabled(true);
		btn28d.setEnabled(true);
		btn35d.setEnabled(true);
		btn42d.setEnabled(true);
		
		
		final int tabela = 2;
		
		
		//  calcula o preço da tabela 1  ---  critico voltar aqui depos
		for(int i = 0; i < listaTemp.size(); i++){
		double pr =   preco[i] *  Math.pow(1  + percent, (tabela -1));
		listaTemp.get(i).setPreco(pr);
		}
		
		atualizaTela();
		
	
		
	}
	public void btn_21d(View v){
		btn21d.setEnabled(false);
		btnVista.setEnabled(true);
		btn14d.setEnabled(true);
		btn28d.setEnabled(true);
		btn35d.setEnabled(true);
		btn42d.setEnabled(true);
		
		int tabela = 3;
		//  calcula o preço da tabela 1  ---  critico voltar aqui depos
		for(int i = 0; i < listaTemp.size(); i++){
			double pr =   preco[i] *  Math.pow(1  + percent, (tabela -1));
			listaTemp.get(i).setPreco(pr);
			}
		
		
		
		atualizaTela();
		
	}
	public void btn_28d(View v){
	
		btn28d.setEnabled(false);
		btnVista.setEnabled(true);
		btn14d.setEnabled(true);
		btn21d.setEnabled(true);
		btn35d.setEnabled(true);
		btn42d.setEnabled(true);
		
		final int tabela = 4;
		//  calcula o preço da tabela 1  ---  critico voltar aqui depos
		for(int i = 0; i < listaTemp.size(); i++){
			double pr =   preco[i] *  Math.pow(1  + percent, (tabela -1));
			listaTemp.get(i).setPreco(pr);
			}
	 
		
		atualizaTela();
	}
	public void btn_35d(View v){
		btn35d.setEnabled(false);
		btnVista.setEnabled(true);
		btn14d.setEnabled(true);
		btn21d.setEnabled(true);
		btn28d.setEnabled(true);
		btn42d.setEnabled(true);
		
		final int tabela = 5;
		//  calcula o preço da tabela 1  ---  critico voltar aqui depos
		for(int i = 0; i < listaTemp.size(); i++){
			double pr =   preco[i] *  Math.pow(1  + percent, (tabela -1));
			listaTemp.get(i).setPreco(pr);
			}
		
		atualizaTela();
		
		
		
	}
	public void btn_42d(View v){
		btn42d.setEnabled(false);
		btnVista.setEnabled(true);
		btn14d.setEnabled(true);
		btn21d.setEnabled(true);
		btn28d.setEnabled(true);
		btn35d.setEnabled(true);
		
		final int tabela = 6;
		//  calcula o preço da tabela 1  ---  critico voltar aqui depos
		for(int i = 0; i < listaTemp.size(); i++){
			double pr =   preco[i] *  Math.pow(1  + percent, (tabela -1));
			listaTemp.get(i).setPreco(pr);
			}
		
		atualizaTela();
	
	}
	
	private void atualizaTela(){
		dialog.show();
		handler.post(new Runnable(){
			public void run(){	
			listaItens.setAdapter(adapter);
			dialog.dismiss();
				
				
					
			
		}});
	
     
	}

   public void btn_sair(View v){
	   this.finish();
   }
  
public boolean onLongClick(View v) {
	Toast.makeText(this, "Pressionado", Toast.LENGTH_LONG).show();
	return false;
}
public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position,
		long arg3) {
	
	produtoRemove = listaTemp.get(position);
	
	String nome = produtoRemove.getDescricao();
	codigoExcluir = produtoRemove.getCodigo();
	
	//Toast.makeText(this, "" + nome, Toast.LENGTH_LONG).show();
    alerta(codigoExcluir);
	return false;
}


//  alerta excluir produto ... 
private void alerta(int codigo ){
	AlertDialog.Builder alerta = new AlertDialog.Builder(this);
	alerta.setTitle("Aviso!");
	alerta.setIcon(R.drawable.trasmite32);
	alerta.setMessage("Deseja Excluir o produto  " + codigo +"?");
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
					excluiProduto(codigoExcluir);
					
					
					// startActivity(new Intent(Menu.this,Menu.class));
					///trasmitir();
					
				
				}
			});
	alerta.show();
	
}

public void excluiProduto(int codigo){
	ManipulaBanco mb = new ManipulaBanco(this);
	mb.excluiRegistro("FV_PRODUTO", "codprod = "+ codigo);

	listaTemp.remove(produtoRemove);
	listaProdutos.remove(produtoRemove);
	
	adapter = new AdaptadorProduto(this,listaTemp);
	listaItens.setAdapter(adapter);
	
	adapter.notifyDataSetChanged();
   atualizaTela();
}
@Override
// quando entrar em pause finaliza ..
protected void onPause() {
	super.onPause();
   ManipulaBanco mb = new ManipulaBanco(this);
   Menu.produtos = mb.buscaProduto();
		   //  seta os valores originais novamente ..logica fraca, voltar aqui depois
	/**	for(int i = 0; i < listaProdutos.size(); i++){
			listaProdutos.get(i).setPreco(preco[i]);
		}**/
		
	}
	

		

}
