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
		

		
		// tabhost atualiza clientes
		TabSpec tab2 = tabhost.newTabSpec("Tab2");
		tab2.setIndicator("Atualizar Clientes",getResources().getDrawable(R.drawable.carteira));
		tab2.setContent(new Intent(this,TelaAtualizaEstoque.class));
		tabhost.addTab(tab2);

	}
	
	

	public View createTabContent(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void onTabChanged(String arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
