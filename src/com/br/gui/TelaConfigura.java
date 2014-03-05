package com.br.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.br.kelma.R;
import com.br.logica.Logica;

public class TelaConfigura extends Activity {
	EditText dirFoto, dirFotoExtra;
	Logica logica;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.configuracao);

		dirFoto = (EditText) findViewById(R.id.editTextFoto);
		dirFotoExtra = (EditText) findViewById(R.id.editTextFotoExtra);
		
		logica = new Logica();
		
		//  busca os diretorios das fotos 
		String[] diretorio = logica.buscaDiretorioFotos(this);
		dirFoto.setText(diretorio[0].toString());
		dirFotoExtra.setText(diretorio[1].toString());

	}

	// salva as configurações de diretorio de fotos
	public void btn_salvaDiretorio(View v) {
		logica.atualizaDirFotos(this, dirFoto.getText().toString(), dirFotoExtra.getText().toString());
		dirFoto.setEnabled(false);
		dirFotoExtra.setEnabled(false);
		Toast.makeText(this,"Diretório Atualizado",Toast.LENGTH_SHORT).show();

	}

}
