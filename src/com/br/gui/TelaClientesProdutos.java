package com.br.gui;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.br.kelma.R;

public class TelaClientesProdutos extends TabActivity implements OnTabChangeListener,TabContentFactory{

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
     	TabHost tabhost = getTabHost();
		tabhost.setOnTabChangedListener(this);
		TabSpec tab1 = tabhost.newTabSpec("Tab1");
		tab1.setIndicator("Clientes",getResources().getDrawable(R.drawable.clientes));
		tab1.setContent(new Intent(this,TelaClientes2.class));
		tabhost.addTab(tab1);
		
		// tabhost produtos
		TabSpec tab2 = tabhost.newTabSpec("Tab2");
		tab2.setIndicator("Produtos",getResources().getDrawable(R.drawable.at_produtos));
	    tab2.setContent(new Intent(this,TelaProdutos.class));
		tabhost.addTab(tab2);

	}

	public View createTabContent(String tabId) {
		TextView tv = new TextView(this);
		tv.setText("tabela produtos");
		return tv;
	}

	public void onTabChanged(String tabId) {
		
	}


}
