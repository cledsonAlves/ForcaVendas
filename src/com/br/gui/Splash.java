package com.br.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.br.kelma.Menu;
import com.br.kelma.R;

public class Splash extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
	

		Thread t = new Thread() {
			@Override
			public void run() {

				try {
					
					sleep(1000);

				} catch (InterruptedException e) {

				} finally {
					startActivity(new Intent(Splash.this,Autentica.class));
				}
			}

		};
		t.start();
	}

	@Override
	// quando entrar em pause finaliza ..
	protected void onPause() {
		super.onPause();
		finish();
	}
	
	
}
