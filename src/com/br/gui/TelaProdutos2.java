package com.br.gui;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.br.kelma.R;
import com.br.logica.Produtos1;
import com.br.logica.Produtos2;
import com.br.logica.Produtos3;
import com.br.logica.Produtos4;
import com.br.objetos.Produto;

public class TelaProdutos2 extends TabActivity implements OnTabChangeListener,TabContentFactory{
	private static final int sim = 1;
	public static int qtde = 0;
	public static double valor = 0;
	private Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	
		
	//  ocultando o teclado 
		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		
		
     	TabHost tabhost = getTabHost();
		tabhost.setOnTabChangedListener(this);
		TabSpec tab1 = tabhost.newTabSpec("Tab1");
		tab1.setIndicator("Shampoo",getResources().getDrawable(R.drawable.at_produtos));
		tab1.setContent(new Intent(this,Produtos1.class));
		tabhost.addTab(tab1);
		
		
		// tabhost produtos
		TabSpec tab2 = tabhost.newTabSpec("Tab2");
		tab2.setIndicator("Condicionador",getResources().getDrawable(R.drawable.at_produtos));
		tab2.setContent(new Intent(this,Produtos2.class));
		tabhost.addTab(tab2);
		
		// tabhost produtos
			TabSpec tab3 = tabhost.newTabSpec("Tab3");
			tab3.setIndicator("Sabonete",getResources().getDrawable(R.drawable.at_produtos));
		    tab3.setContent(new Intent(this,Produtos3.class));
			tabhost.addTab(tab3);
		
		
		
		// tabhost produtos
			TabSpec tab4 = tabhost.newTabSpec("Tab4");
			tab4.setIndicator("Outros",getResources().getDrawable(R.drawable.at_produtos));
		    tab4.setContent(new Intent(this,Produtos4.class));
			tabhost.addTab(tab4);

	}

	public View createTabContent(String tabId) {
		TextView tv = new TextView(this);
		tv.setText("tabela produtos");
		return tv;
	}

	public void onTabChanged(String tabId) {
	
		
	}
	 private void atualizaTela(){
	  		
	  		handler.post(new Runnable(){
	  			public void run(){
	  		
	  					
	  			
	  		}});
	  	
	       
	  	}
	 @Override
		// quando entrar em pause finaliza ..
		protected void onStart() {
			super.onStart();
			// Define o Adapter
			
			  atualizaTela();
			 

			}



}
