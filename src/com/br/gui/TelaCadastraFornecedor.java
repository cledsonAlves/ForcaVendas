package com.br.gui;

import com.br.kelma.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class TelaCadastraFornecedor extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.cad_fornecedor);
		
		
	}

}
