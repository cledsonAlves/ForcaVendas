package com.br.gui;
//  compilado 26/07/2013 15:57 

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.br.kelma.R;
import com.br.kelma.Sistema;
import com.br.logica.Logica;

public class Autentica extends Activity {
	public static final int novo = 1;
	public static final int senha = 3;
	public static final int cadastro = 4;
	private Logica logica;
	Button btn;
    EditText editTextUsuario,editTextSenha;
    public static String usuario;
   private final String NOME = "autenticacao";

	 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.autentica);
		
		
		
		
		editTextUsuario = (EditText)findViewById(R.id.editUsuario);
		editTextUsuario.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
		editTextSenha = (EditText)findViewById(R.id.editSenh);
		logica = new Logica();
		
		SharedPreferences pref = getSharedPreferences(NOME, 0);
		String usur = pref.getString("usuario", "");
		String sen  = pref.getString("senha","");
		editTextUsuario.setText(usur);
		editTextSenha.setText(sen);
		
			
	}

	
	public void btn_logar(View v){
		//  verifica se os campos est�o em brancos
		if (editTextUsuario.getText().toString().equals("")){
			Toast.makeText(this,"Informe o usu�rio",Toast.LENGTH_SHORT).show();
		}
	//	else if (editTextSenha.getText().toString().equals("")){
		//	Toast.makeText(this,"Informe a senha ",Toast.LENGTH_SHORT).show();
		//}
		///  
		else {
			
			String user = editTextUsuario.getText().toString();
			String senha = editTextSenha.getText().toString() ;
            
			//  verifica se usuario existe ..
	//	if (logica.verificaUsuario(user, senha, this) == true){
			
			usuario = editTextUsuario.getText().toString();
			//  guarda o nome do usuario para o proximo acesso
			SharedPreferences pref = getSharedPreferences(NOME, 0);
			SharedPreferences.Editor editor = pref.edit();
			editor.putString("usuario", user);
			editor.putString("senha", senha);
			editor.commit();
			
			
		     startActivity(new Intent(Autentica.this,Sistema.class));
		     this.finish();
		//	}else {
				// Toast.makeText(this,"Usu�rio ou Senha inv�lida!",Toast.LENGTH_SHORT).show();
		}
	//	}
	}
	//  cancela acesso ao sistema
	public void btn_cancelar(View v ){
		this.finish();
	}
	

}


