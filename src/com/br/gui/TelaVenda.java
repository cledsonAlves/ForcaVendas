package com.br.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.br.kelma.R;
import com.br.logica.Logica;
import com.br.objetos.Pedido;

/**
 * 
 * @author Tela Inicial de venda do pedido
 * 
 */
public class TelaVenda extends Activity {
	private Spinner spCondicao, spOperacao, spFilial, spTabela, spPagamento;
	private ArrayAdapter<String> adaptador1, adaptador2, adaptador3,
			adaptador4, adaptador5;
	private EditText edDataVenda, edNumPed, edCliente, edtObs;
	private Logica logica = new Logica();
    private String[] lista_pagamento = new String[] { "Boleto Bancário","Dinheiro", "Cheque" };
	private String[] lista_tabela = new String[] { "A Vista", "14 dias","21 dias", "28 dias", "35 dias","42 dias" };
	private String[] lista_condicoes = new String[] {"A Vista","7","10","14","21","28", "7/14/21","7/14 (TAB 14)","14","14/21","14/21(TAB 14)","14/21/28","21/28 (TAB 21)","21/28 (TAB 14)","21 (A Vista)","21/28/35 (TAB 21)","21/28/35 (TAB14)","28/35 (TAB 28)","35/42(TAB 35)","42/60(TAB 42)","42" };
	private String[] lista_filiais = new String[] { "Kelma Cosmeticos"};
	private String[] lista_operacoes = new String[] { "Venda Normal","Bonificação" , "Orçamento","Troca"};
	

	// Construtor inicial da tela
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		
		setContentView(R.layout.teste);
		// esconder o teclado ...
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
	    iniciaComponentes();
	

	}

	// inicia os componentes da tela
	public void iniciaComponentes() {
	      
			//  Edit Text
		edCliente = (EditText) findViewById(R.id.editCodRep);
		edCliente.setEnabled(false);
		
		edNumPed = (EditText) findViewById(R.id.editNomeRep);
		edNumPed.setEnabled(false);
		
		
		edtObs = (EditText) findViewById(R.id.editObser);
		edtObs.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
		
		edDataVenda = (EditText) findViewById(R.id.editCpfRep);
		edDataVenda.setEnabled(false);
		

		// Spiner
		spOperacao = (Spinner) findViewById(R.id.spOperacao);
		spPagamento = (Spinner) findViewById(R.id.spPagamento);
		spCondicao = (Spinner) findViewById(R.id.spPrazo);
		spFilial = (Spinner) findViewById(R.id.spEmbalagem);
		spTabela = (Spinner) findViewById(R.id.spTabela);
		
		


		// criando os adaptadores de spinner
		adaptador1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,logica.buscaPlanos(this));
		adaptador1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spCondicao.setAdapter(adaptador1);

		adaptador2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lista_operacoes);
		adaptador2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spOperacao.setAdapter(adaptador2);

		adaptador3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lista_filiais);
		adaptador3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spFilial.setAdapter(adaptador3);

		adaptador4 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lista_tabela);
		adaptador4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spTabela.setAdapter(adaptador4);

		adaptador5 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lista_pagamento);
		adaptador5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spPagamento.setAdapter(adaptador5);
	
		carregaCamposDefault();
		

	}

	// carrega os campos defaul ao iniciar a aplicação
	private void carregaCamposDefault() {
		edDataVenda.setText(Logica.DataAtual());
		// carrega o numero do proximo pedido
		edNumPed.setText(String.valueOf(logica.buscaCodigoPedido(this)));
	}
	
	//  desabilita os widgts 
	private void desabilitaCampos(){
		spOperacao.setEnabled(false);
		edNumPed.setEnabled(false);
		spFilial.setEnabled(false);
		spCondicao.setEnabled(false);
		spTabela.setEnabled(false);
		spPagamento.setEnabled(false);
		edDataVenda.setEnabled(false);
		edCliente.setEnabled(false);
	}
	
	
	//  habilita os widgts 
	private void habilitaCampos(){
		spOperacao.setEnabled(true);
		spFilial.setEnabled(true);
		spCondicao.setEnabled(true);
		spTabela.setEnabled(true);
		spPagamento.setEnabled(true);
		edNumPed.setEnabled(true);
		edCliente.setEnabled(true);
	}

	// Ações dos botões
	// Confirma e inicia a venda
	public void btn_confirma(View v) {
		if (edCliente.getText().toString().equals("")){
			Toast.makeText(this,"Informe o Cliente!!", Toast.LENGTH_SHORT).show();
		} 
		else {

			Pedido pedido = new Pedido();
			pedido.setNumero(Integer.parseInt(edNumPed.getText().toString()));
			
			// busca codigo do cliente no banco passando o nome dele
			pedido.setRepresentante(Autentica.usuario);
			pedido.setCodigoCliente(logica.buscaCliente(edCliente.getText().toString(), this));
			pedido.setCondPagamento(spCondicao.getSelectedItem().toString());
			pedido.setTipoPagamento(spPagamento.getSelectedItem().toString());
			pedido.setTipoVenda(spOperacao.getSelectedItem().toString());
			pedido.setFilialVenda(spFilial.getSelectedItem().toString());
			pedido.setObs(edtObs.getText().toString());
			
			//  tabela 
			if (spTabela.getSelectedItem().toString().equalsIgnoreCase("A Vista")){
				pedido.setCodTabela(1);
				
			}else if (spTabela.getSelectedItem().toString().equalsIgnoreCase("14 dias")){
				pedido.setCodTabela(2);
				
			}else if (spTabela.getSelectedItem().toString().equalsIgnoreCase("21 dias")){
				pedido.setCodTabela(3);
			}
             else if (spTabela.getSelectedItem().toString().equalsIgnoreCase("28 dias")){
            	 pedido.setCodTabela(4);
			}
             else if (spTabela.getSelectedItem().toString().equalsIgnoreCase("35 dias")){
            	 pedido.setCodTabela(5);
 			}
             else if (spTabela.getSelectedItem().toString().equalsIgnoreCase("42 dias")){
            	 pedido.setCodTabela(6);
 			}
			
			pedido.setTabela(spTabela.getSelectedItem().toString());
			pedido.setDataVenda(edDataVenda.getText().toString());
			
			//  passa o cabeçalho para tela de digitação do pedido
			TelaDigitacao.pedido = pedido;
			//logica.buscaPedido(this);
	
		this.finish();
		startActivity(new Intent(this, TelaDigitacao.class));
		
		}

	}
	
	// cancela digitação ...
	public void btnCancela(View v){
		this.finish();
	}

	// Busca cliente
	public void btn_buscaCliente(View v) {
		startActivityForResult(new Intent(this, TelaClientes.class),2);
	}
	@Override
	protected void onActivityResult(int codigo, int resultado, Intent it) {
		Bundle params = it != null? it.getExtras():null;
		if (params != null){
			String msg = params.getString("id");
			edCliente.setText(msg);
			
		}
	}
	
	public void btn_novo(View v){
		habilitaCampos();
		carregaCamposDefault();
	}
//  desabilitando o botão voltar ..... 
	@Override
    public void onBackPressed() {
     Toast.makeText(this,"Para sair, pressione o botão cancelar ",Toast.LENGTH_SHORT).show();
    }

}
