package com.br.gui;
//  compilado 26/07/2013 15:57 

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.br.kelma.Menu;
import com.br.kelma.R;
import com.br.logica.Logica;

public class Autentica extends Activity {
	public static final int novo = 1;
	public static final int senha = 3;
	public static final int cadastro = 4;
	private Logica logica;
	Button btn;
    EditText editTextUsuario,editTextSenha;
    public static String usuario;
    private boolean useLogo = false;
    private boolean showHomeUp = false;

	 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.autentica);
		
		
		
		
		editTextUsuario = (EditText)findViewById(R.id.editUsuario);
		editTextUsuario.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
		editTextSenha = (EditText)findViewById(R.id.editSenh);
		logica = new Logica();
		
			
	}

	
	public void btn_logar(View v){
		//  verifica se os campos estão em brancos
		if (editTextUsuario.getText().toString().equals("")){
			Toast.makeText(this,"Informe o usuário",Toast.LENGTH_SHORT).show();
		}
	//	else if (editTextSenha.getText().toString().equals("")){
		//	Toast.makeText(this,"Informe a senha ",Toast.LENGTH_SHORT).show();
		//}
		///  
		else {
			
			String user = editTextUsuario.getText().toString();
		//	String senha = editTextSenha.getText().toString() ;
            
			//  verifica se usuario existe ..
	//	if (logica.verificaUsuario(user, senha, this) == true){
			
			usuario = editTextUsuario.getText().toString();	
			
		     startActivity(new Intent(Autentica.this,Menu.class));
		     this.finish();
		//	}else {
				// Toast.makeText(this,"Usuário ou Senha inválida!",Toast.LENGTH_SHORT).show();
		}
	//	}
	}
	//  cancela acesso ao sistema
	public void btn_cancelar(View v ){
		this.finish();
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		//  cadastra representante
		case novo:
			startActivity(new Intent(this, TelaCadastraRepresentante.class));
			return true;
	    //  altera senha 
		case senha:
		//	startActivity(new Intent(this, xxx.class));
			return true;
		// altera cadastro 	
		case cadastro:
			//startActivity(new Intent(this,yyyyy.class));
			return true;
		

		}
		
		return true;
	}



}


