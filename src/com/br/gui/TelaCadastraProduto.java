package com.br.gui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.br.kelma.R;
import com.br.logica.Logica;
import com.br.objetos.Mask;
import com.br.objetos.Produto;

public class TelaCadastraProduto extends Activity implements OnFocusChangeListener{
	private Spinner spEmbalagem, spFornec;
	private EditText edCodProd, edPeso, edCategoria, edDepartamento, edPreco,edDescricao, edEstoque;
	private ArrayAdapter<String> adaptadorEmbalagen, adaptadorFornec;
	private Logica logica;

	// Construtor da classe
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.cad_produto);
		iniciaComponentes();
		logica = new Logica();

	}

	// método inicia componentes ..
	private void iniciaComponentes() {
		// Edit Text
		edCodProd = (EditText) findViewById(R.id.editCodRep);
		edCodProd.setOnFocusChangeListener(this);
		edCodProd.requestFocus();
		edDescricao = (EditText) findViewById(R.id.editNomeRep);
		edDescricao.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
		
		edPeso = (EditText) findViewById(R.id.editPeso);
		edPeso.setOnFocusChangeListener(this);
		edCategoria = (EditText) findViewById(R.id.editSenha);
		edCategoria.setText("desabilitado");
		edCategoria.setEnabled(false);
		
		edDepartamento = (EditText) findViewById(R.id.editDepartamento);
		edDepartamento.setText("desabilitado");
		edDepartamento.setEnabled(false);
		
		
		edPreco = (EditText) findViewById(R.id.editPreco);
		//edPreco.addTextChangedListener(Mask.insert("##.###.###-##",edPreco));
		
		edEstoque = (EditText)findViewById(R.id.editEstoque);
		edEstoque.setText("900");
		edEstoque.setEnabled(false);

		// spinner .. valores default
		String[] embalagens = new String[] { "Caixa", "Unidade", "Pacote",
				"Pote", "Galão" };
		String[] fornec = new String[] { "Kelma Cosméticos" };
		adaptadorEmbalagen = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, embalagens);
		adaptadorFornec = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, fornec);

		spEmbalagem = (Spinner) findViewById(R.id.spEmbalagem);
		spEmbalagem.setAdapter(adaptadorEmbalagen);

		spFornec = (Spinner) findViewById(R.id.spFornecedor);
		spFornec.setAdapter(adaptadorFornec);

	}
	// encerra a activity
	public void btn_voltar(View v){
		this.finish();
	}

	// botão scanner QrCode
	// chama o barcode scanner
	public void btn_Scanner(View v) {
		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
		startActivityForResult(intent, 0);
	}

	// resultado da busca do app barcode scanner 
	@Override
	protected void onActivityResult(int codigo, int resultado, Intent it) {
	// pega o resultado do barcode scanner
		if (codigo == 0) {
			if (resultado == RESULT_OK) {
				String contents = it.getStringExtra("SCAN_RESULT");
				String texto[] = contents.split(";");
				String format = it.getStringExtra("SCAN_RESULT_FORMAT");
				//  preenche os campos
				edCodProd.setText(texto[0]);
				edDescricao.setText(texto[1]);
				//edEmbalagem.setText(texto[2]);
				edPeso.setText(texto[3]);
				edCategoria.setText(texto[4]);
				edDepartamento.setText(texto[5]);
				edPreco.setText(texto[6]);
				//edFornecedor.setText(texto[7]);		
				// Handle successful scan
			} else if (resultado == RESULT_CANCELED) {
				// Handle cancel
			}
		   }
		}

	// botão cadastra produto
	public void btn_Cadastra(View v) {
	   // verifica se usuário informou os campos obrigatórios
		verificaCamposNulos();
		
	

	}
	

	// método executado automaticamente quando o objeto perder o foco
	public void onFocusChange(View v, boolean hasFocus) {
		if ((v.getId() == R.id.editPeso) && (hasFocus == false)) {
		if (!edPeso.getText().toString().equals("")){
		edPeso.setText(edPeso.getText().toString().replace(",","."));
		}
		}
		
		if ((v.getId() == R.id.editCodRep) && (hasFocus == false)) {
			// verifica se o campo código não esta em branco
			if (!edCodProd.getText().toString().equals("")) {
				String codigo = edCodProd.getText().toString();
				// verifica se o código ja esta cadastrado no banco de dados
				boolean retorno = logica.verificaCodigo(
						Integer.parseInt(codigo), this);
				if (retorno == true) {
					dialogo("Codigo Inválido","Este código já está em uso. Informe outro código !!");
					edCodProd.setText(null);
				}
			}
		}

	}

	// verifica se usuário informou os campos obrigatórios
	private void verificaCamposNulos() {
		if (edCodProd.getText().toString().equals("")) {
			dialogo("Código em Branco","Você deve informar o código do produto !!!");
		} else if (edDescricao.getText().toString().equals("")) {
			dialogo("Descrição em Branco","Você deve informar a Descrição do Produto!!!");
		} else {
		// Captura os dados da tela 
			capturaDados();
			
		}
	
	}
	
	//  Pega os valores da tela
	private void capturaDados(){
		// estou tratando o erro !! Converte os valores
		int codigo ;
		double  peso ;
		double preco ;
		try {
		   codigo = Integer.parseInt(edCodProd.getText().toString());
		   peso = Double.parseDouble(edPeso.getText().toString().replace(",","."));
		   preco = Double.parseDouble(edPreco.getText().toString().replace(",","."));
		
			
		} catch (NumberFormatException e) {
			//  se o usuario deixar vazio os campos, preenche para não dar erro ao converter os valores 
			edPreco.setText("0.0");
			edPeso.setText("0.0");
			
			 codigo = Integer.parseInt(edCodProd.getText().toString());
			 peso = Double.parseDouble(edPeso.getText().toString());
			 
			 
			 preco = Double.parseDouble(edPreco.getText().toString());
			
		}	
        
		   
		//  cria um produto para enviar para o banco 
			Produto produto = new Produto();
			
			produto.setCodigo(codigo);
			produto.setDescricao(edDescricao.getText().toString());
			produto.setEmbalagem(spEmbalagem.getSelectedItem().toString());
			produto.setPeso(peso);
			produto.setCategoria(edCategoria.getText().toString());
			produto.setDepartamento(edDepartamento.getText().toString());
			produto.setPreco(preco);
			produto.setFornecedor(spFornec.getSelectedItem().toString());
			
			//  manda tentar fazer o cadastro do produto
			if (logica.cadastraProduto(produto, this)){
				dialogo("Cadastro","Produto cadastrado com sucesso!!");
				limpaCampos();
				edCodProd.requestFocus();
			}
	
		
	
		
	}
	// cria um dialogo de aviso
	private void dialogo(String titulo,String mensagem) {
		AlertDialog.Builder dialogo = new AlertDialog.Builder(
				TelaCadastraProduto.this);
		dialogo.setTitle(titulo);
		dialogo.setMessage(mensagem);
		dialogo.setNeutralButton("Ok", null);
		dialogo.show();

	}
	// Limpa os campos
	private void limpaCampos(){
		edCodProd.setText(null);
		edDescricao.setText(null);
		edPeso.setText(null);
		edCategoria.setText(null);
		edDepartamento.setText(null);
		edPreco.setText(null);
	}

}