package com.br.gui;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.br.kelma.Menu;
import com.br.kelma.R;

public class TelaPedido extends TabActivity implements OnTabChangeListener,TabContentFactory{

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
     	TabHost tabhost = getTabHost();
		tabhost.setOnTabChangedListener(this);
		TabSpec tab1 = tabhost.newTabSpec("Tab1");
		tab1.setIndicator("Agurdando Transmissão",getResources().getDrawable(R.drawable.lupa));
		tab1.setContent(new Intent(this,TelaListaPedido.class));
		tabhost.addTab(tab1);
		
		TabSpec tab4 = tabhost.newTabSpec("Tab4");
		tab4.setIndicator("Transmitidos",getResources().getDrawable(R.drawable.lupa));
		tab4.setContent(new Intent(this,TelaListaPedidoTrasmitido.class));
		tabhost.addTab(tab4);
		
		
		// tabhost orçamento
		TabSpec tab3 = tabhost.newTabSpec("Tab3");
		tab3.setIndicator("Orçamentos",getResources().getDrawable(R.drawable.at_preco));
		tab3.setContent(new Intent(this,TelaOrcamento.class));
		tabhost.addTab(tab3);
		
		// tabhost orçamento
		TabSpec tab2 = tabhost.newTabSpec("Tab2");
	    tab2.setIndicator("Arquivados",getResources().getDrawable(R.drawable.cadastro));
	    tab2.setContent(new Intent(this,TelaArquivados.class));
	    tabhost.addTab(tab2);

		
	}	

	public View createTabContent(String tabId) {
		TextView tv = new TextView(this);
		tv.setText("tabela produtos");
		return tv;
	}

	public void onTabChanged(String tabId) {
		if (tabId.equals("Pedidos")){
			Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
		}
	}
}