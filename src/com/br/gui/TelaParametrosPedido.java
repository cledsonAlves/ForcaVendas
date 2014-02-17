package com.br.gui;

import com.br.kelma.R;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;

public class TelaParametrosPedido extends TabActivity implements OnTabChangeListener,TabContentFactory {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		TabHost tabhost = getTabHost();
		tabhost.setOnTabChangedListener(this);
		
		TabSpec tab1 = tabhost.newTabSpec("Tab1");
		tab1.setIndicator("Tabela de preço",getResources().getDrawable(R.drawable.carteira));
		tab1.setContent(new Intent(this,TelaTabelaPreco.class));
		tabhost.addTab(tab1);
		
		// tabhost atualiza estoque 
		TabSpec tab2 = tabhost.newTabSpec("Tab2");
		tab2.setIndicator("Atualizar estoque",getResources().getDrawable(R.drawable.at_produtos));
		tab2.setContent(new Intent(this,TelaAtualizaEstoque.class));
		tabhost.addTab(tab2);


		// tabhost produtos
		TabSpec tab3 = tabhost.newTabSpec("Tab3");
		tab3.setIndicator("Atualizar preços",getResources().getDrawable(R.drawable.at_preco));
		tab3.setContent(new Intent(this,TelaAtualizaEstoque.class));
		tabhost.addTab(tab3);


		// tabhost produtos
		TabSpec tab4 = tabhost.newTabSpec("Tab4");
		tab4.setIndicator("Planos de Pagamentos",getResources().getDrawable(R.drawable.alerta32));
		tab4.setContent(new Intent(this,TelaAtualizaEstoque.class));
		tabhost.addTab(tab4);
	}
	
	

	public View createTabContent(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void onTabChanged(String arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
