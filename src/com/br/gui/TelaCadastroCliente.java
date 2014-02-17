package com.br.gui;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.br.kelma.Menu;
import com.br.kelma.R;
import com.br.logica.Logica;
import com.br.objetos.Cliente;
import com.br.objetos.Endereco;
import com.br.objetos.Estado;
import com.br.objetos.Mask;

public class TelaCadastroCliente extends Activity implements OnClickListener {
	private ArrayAdapter<String> adapEstados;
	private Spinner edEstado;
	private EditText edCodCli, edNome, edCnpj_cpf, edEndereco, edBairro,
			edCidade, edUf, edTelComercial, edCelular, edEmail;
	private Button btnCadCliente, btnVoltarMenu;
	private String[] lista_estados, lista_uf;
	private Logica logica = new Logica();

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.cad_cliente);
		
		edCodCli = (EditText) findViewById(R.id.editCodRep);
		

		edNome = (EditText) findViewById(R.id.editNomeRep);
		edNome.requestFocus();

		edCnpj_cpf = (EditText) findViewById(R.id.editCpfRep);
		
		//edCnpj_cpf.addTextChangedListener(Mask.insert("###.###.###-##",edCnpj_cpf));
		
		edEndereco = (EditText) findViewById(R.id.editPeso);
		edBairro = (EditText) findViewById(R.id.editSenha);
		edCidade = (EditText) findViewById(R.id.editDepartamento);

		edEstado = (Spinner) findViewById(R.id.spEmbalagem);

		edUf = (EditText) findViewById(R.id.editEstoque);
		edUf.setEnabled(false);

		edTelComercial = (EditText) findViewById(R.id.editPreco);
		edTelComercial.addTextChangedListener(Mask.insert("(##)####-####",edTelComercial));
		
		edCelular = (EditText) findViewById(R.id.editTel2);
		edCelular.addTextChangedListener(Mask.insert("(##)#####-####",edCelular));
		
		edEmail = (EditText) findViewById(R.id.editEmail);

		btnCadCliente = (Button) findViewById(R.id.btnSalvar);
		btnCadCliente.setOnClickListener(this);

		btnVoltarMenu = (Button) findViewById(R.id.btnHome);
		btnVoltarMenu.setOnClickListener(this);
		// desabilita os campos da tela

		// carrega codigo do proximo cadastro ..
		edCodCli.setText(String.valueOf(logica.buscaCodigoCliente(this)));
		ArrayList<Estado> estados = logica.buscaEstados(this);
		lista_estados = new String[27];
		lista_uf = new String[27];
		for (int i=0; i < 27; i++) {
			lista_estados[i] = estados.get(i).getNome();
			lista_uf[i] = estados.get(i).getUf();
		}
		adapEstados = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lista_estados);
		adapEstados.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		edEstado.setAdapter(adapEstados);
		edEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				edUf.setText(lista_uf[edEstado.getSelectedItemPosition()]);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

	/*
	 * codigo,nome,endereco,cnpJ,tel1,tel2,email,bairro,codEstado,codCidade ação
	 * de varios botoes .. int id = (Integer)v.getId(); switch(id) case 1:....
	 */

	public void onClick(View v) {
		switch (v.getId()) {
		// salvar o cadastro
		case R.id.btnSalvar:
			// verifica se os campos chaves estão em branco
			if (edNome.getText().toString().equals("")) {
				dialogo("Obrigatório o preechimento do campo Nome!");
			} else if (edCidade.getText().toString().equals("")) {
				dialogo("Obrigatório o preechimento do campo Cidade!");
			} //else if (edEstado.getgetText().toString().equals("")) {
				//dialogo("Obrigatório o preechimento do campo Estado!");
			 else {

				// transforma o valor do campo para inteiro ..
				int codigo = 0;
				try {
					codigo = Integer.parseInt(edCodCli.getText().toString());
				} catch (NumberFormatException e) {
					Log.i("Erro de Formato", " erro " + e);
				}
				// cria um cliente com os dados dos campos
				Cliente cliente = new Cliente();
				Endereco endereco = new Endereco();
				cliente.setCodigo(codigo);
				cliente.setNome(edNome.getText().toString());
				cliente.setCnpj(edCnpj_cpf.getText().toString());
				endereco.setEndereco(edEndereco.getText().toString());
				endereco.setBairro(edBairro.getText().toString());
				endereco.setCidade(edCidade.getText().toString());
				endereco.setEstado(edEstado.getSelectedItem().toString());
				endereco.setUf(edUf.getText().toString());
				cliente.setEndereco(endereco);
				cliente.setTel1(edTelComercial.getText().toString());
				cliente.setTel2(edCelular.getText().toString());
				cliente.setEmail(edEmail.getText().toString());
				// Manda o cliente para classe banco tentar gravar na base de dados
				if (logica.cadastraCliente(cliente, this) == true) {
					Toast.makeText(this, "Cliente Cadastrado com Sucesso!!", Toast.LENGTH_SHORT).show();
					//volta ao menu.
					this.finish();
				}
				else {
					Toast.makeText(this, "Erro ao cadastrar o cliente!!", Toast.LENGTH_SHORT).show();
					this.finish();
				}

			}

			break;
		

		// voltar ao menu
		case R.id.btnHome:
			AlertDialog.Builder alerta = new AlertDialog.Builder(this);
			alerta.setTitle("Voltar");
			alerta.setIcon(R.drawable.voltar32);
			alerta.setMessage("Deseja cancelar cadastro ?");
			// método executado caso escolha sim
			alerta.setPositiveButton("Sim",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							startActivity(new Intent(TelaCadastroCliente.this,
									Menu.class));

						}
					});
			// método executado caso escolha não ..
			alerta.setNegativeButton("Não",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// startActivity(new Intent(Menu.this,Menu.class));
						}
					});
			alerta.show();
			break;
		}

	}

	// cria uma mensagem para o usuario
	public void alerta(String mensagem) {
		Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
		Log.i("Alerta", mensagem);
	}

	// cria um dialogo de aviso
	public void dialogo(String mensagem) {
		AlertDialog.Builder dialogo = new AlertDialog.Builder(
				TelaCadastroCliente.this);
		dialogo.setTitle("Aviso");
		dialogo.setMessage(mensagem);
		dialogo.setNeutralButton("Ok", null);
		dialogo.show();

	}

	// método excluir cliente
	public void excluir() {
		edCodCli.setEnabled(true);
		edCodCli.requestFocus();
		alerta("Informe o código do cliente");
		if (edCodCli.requestFocus() == false) {
			alerta("pereu o focus");

		}

	}

	// resultado da busca na tela estados -- Felipe este método é invocado
	// automaticamente
	@Override
	protected void onActivityResult(int codigo, int resultado, Intent it) {
		Bundle params = it != null ? it.getExtras() : null;
		if (params != null) {
			String nomeEstado = params.getString("nomeEstado");
			String ufEstado = params.getString("uf");
			//edEstado.setText(nomeEstado);
			//edUf.setText(ufEstado);
			edTelComercial.requestFocus();

		}

	}

}