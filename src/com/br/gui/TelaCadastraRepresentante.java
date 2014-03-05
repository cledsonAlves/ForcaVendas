package com.br.gui;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.br.kelma.R;
import com.br.logica.Logica;
import com.br.objetos.Mask;
import com.br.objetos.Vendedor;

public class TelaCadastraRepresentante extends Activity {
	private Logica logica;
	private EditText editCodRep,editNomeRep,editCpfRep,editUsuarioRep,editSenhaRep; 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.cad_representante);
		
		editCodRep = (EditText)findViewById(R.id.editCodRep);
		editCodRep.setFocusable(true);
		
		editNomeRep = (EditText)findViewById(R.id.editNomeRep);
		editNomeRep.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
		
		editCpfRep = (EditText)findViewById(R.id.editCpfRep);
		editCpfRep.addTextChangedListener(Mask.insert("###.###.###-##",editCpfRep));
		
	
		
		
		editUsuarioRep = (EditText)findViewById(R.id.editUsuarioRep);
		editUsuarioRep.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
		editSenhaRep = (EditText)findViewById(R.id.editSenhaRep);
		
		
		
		logica = new Logica();
		
		
	}
	
	//  salva o cadastro
	public void  btn_salvar(View v){
		if (editCodRep.getText().toString().equals("")){
		  Toast.makeText(this,"Informe o código !!!",Toast.LENGTH_SHORT).show();	
		} 
		else if (editNomeRep.getText().toString().equals("")){
			Toast.makeText(this,"Informe o nome !!!",Toast.LENGTH_SHORT).show();
		}
		else if (editUsuarioRep.getText().toString().equals("")){
			Toast.makeText(this,"Informe o usuário !!!",Toast.LENGTH_SHORT).show();
		}
		else if (editSenhaRep.getText().toString().equals("")){
			Toast.makeText(this,"Informe a senha !!!",Toast.LENGTH_SHORT).show();
		}
		else if (editCpfRep.getText().toString().equals("")){
			Toast.makeText(this,"Informe o CPF ou CNPJ !!!",Toast.LENGTH_SHORT).show();
		}
		else {
		  cadastra();	
		}
	}
	
	//  cancela o cadastro
	public void btn_cancelar(View v){
		this.finish();
	}
	
	
	//  método cadastra representante ..
	private void cadastra(){
	  	Vendedor vendedor = new Vendedor();
	  	vendedor.setCodigo(Integer.parseInt(editCodRep.getText().toString()));
	  	vendedor.setNome(editNomeRep.getText().toString());
	  	vendedor.setCpf(editCpfRep.getText().toString());
	  	vendedor.setUsuario(editUsuarioRep.getText().toString());
	  	vendedor.setSenha(editSenhaRep.getText().toString());
	  	
	  	if(logica.cadastraVendedor(vendedor, this)== true){
	  		Toast.makeText(this,"Representante Cadastrado com sucesso!!!",Toast.LENGTH_SHORT).show();
	  		this.finish();
	  	}else {
	  		Toast.makeText(this,"Erro ao cadastrar!!!",Toast.LENGTH_SHORT).show();
	  		this.finish();
	  	}
	  	
	  	
	}

}
