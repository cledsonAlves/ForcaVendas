package com.br.logica;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.br.adaptadores.AdaptadorProduto;
import com.br.baseDados.ManipulaBanco;
import com.br.gui.ItemListView;
import com.br.gui.TelaDigitacao;
import com.br.kelma.Menu;
import com.br.kelma.R;
import com.br.objetos.Mask;
import com.br.objetos.Produto;

public class Produtos4 extends Activity {

	public static EditText etPesquisa;
	public TextView textViewQtde, textViewVlTotal;
	public static String filtro = "";
	public static int codigo = 0;
	private int qtde;
	private int posicao;
	private int count = 1;
    private ArrayList<Produto> novaLista = new ArrayList<Produto>();
// //////////////////////////////////////////////////////////////////////////////////////
	AdaptadorProduto adapter;
	private List<Produto> listaProdutos;
	AdaptadorProduto adaptadorProduto;
	ListView listaItens;
	private Logica logica;
	private ItemListView item;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.consulta2);
		logica = new Logica();

		listaItens = (ListView) findViewById(R.id.lvEstados);
		etPesquisa = (EditText) findViewById(R.id.etProcurar);
		textViewQtde = (TextView) findViewById(R.id.textViewQtde);
		textViewQtde.setText("0");

		textViewVlTotal = (TextView) findViewById(R.id.textViewVlTotal);
		textViewVlTotal.setText("0.0");

		etPesquisa.addTextChangedListener(filterTextWatcher);

		listaProdutos = new ArrayList<Produto>();
		
		ManipulaBanco mb = new ManipulaBanco(this);

	
		   List<Produto> listaTemp = Menu.produtos;

			
			for(int x = 0; x < listaTemp.size(); x++){
				if(listaTemp.get(x).getDescricao().contains("XAMPU")||listaTemp.get(x).getDescricao().contains("CONDICIONADOR")||listaTemp.get(x).getDescricao().contains("SABONETE")){		
				}else {
					 listaProdutos.add(listaTemp.get(x));	
				}
			}

	

		// Array temporario
		// listaProdutos = mb.buscaProduto();

		adapter = new AdaptadorProduto(this, listaProdutos);

		listaItens.setAdapter(adapter);

		// //////////////////////////////////////////////////////
		etPesquisa.setText(filtro);
		etPesquisa.selectAll();
		etPesquisa.setFilters(new InputFilter[] { new InputFilter.AllCaps() });

		// ocultando o teclado
		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(etPesquisa.getWindowToken(), 0);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		listaItens.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView arg0, View view, int position,
					long index) {
				filtro = etPesquisa.getText().toString();

				// se for feito o filtro pega na nova lista ...
				if (novaLista.size() > 0) {
					Produto p = novaLista.get(position);

					item = new ItemListView();
					item.setCodigo(p.getCodigo());
					item.setDescricao(p.getDescricao());
					item.setValorUnitario(p.getPreco());
					Log.i("Preço", "" + p.getPreco());

					ExibeDialog();

					// senão, pega na lista normal
				} else {
					Produto p = listaProdutos.get(position);

					item = new ItemListView();
					item.setCodigo(p.getCodigo());
					item.setDescricao(p.getDescricao());
					item.setValorUnitario(p.getPreco());
					Log.i("Preço", "" + p.getPreco());

				}

			}
		});

	}

	private TextWatcher filterTextWatcher = new TextWatcher() {

		public void afterTextChanged(Editable s) {
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			CarregarEncontrados();

			adapter = new AdaptadorProduto(Produtos4.this, novaLista);
			listaItens.setAdapter(adapter);

		}

	};

	public void CarregarEncontrados() {
		int textlength = etPesquisa.getText().length();
		novaLista.clear();
		try {
			String pesquisa = etPesquisa.getText().toString();

			for (int i = 0; i < listaProdutos.size(); i++) {
				if (listaProdutos.get(i).getDescricao().contains(pesquisa)) {
					novaLista.add(listaProdutos.get(i));

				} else if (String.valueOf(listaProdutos.get(i).getCodigo())
						.contains(etPesquisa.getText().toString())) {
					novaLista.add(listaProdutos.get(i));

				}

			}
		} catch (NumberFormatException e) {
			this.finish();
		}

	}

	// telinha para adcionar itens ...
	private void ExibeDialog() {

		final Dialog dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_PROGRESS);
		dialog.setContentView(R.layout.adciona_itens);

		// instancia os objetos que estão no layout customdialog.xml
		final ImageButton mais = (ImageButton) dialog
				.findViewById(R.id.imageButtonAdd);
		final ImageButton menos = (ImageButton) dialog
				.findViewById(R.id.imageButtonRem);
		final Button inclui = (Button) dialog.findViewById(R.id.buttonInclui);
	

		final EditText descricao = (EditText) dialog
				.findViewById(R.id.editTextDescricao);
		descricao.setText(item.getDescricao());
		descricao.setEnabled(false);
		
		final EditText quantidade = (EditText) dialog.findViewById(R.id.editTextQuantidade);
		quantidade.addTextChangedListener(Mask.insert("####",quantidade));
		quantidade.requestFocus();

		final ImageView img = (ImageView) dialog.findViewById(R.id.imageView1);

	//  busca os diretorios das fotos 
		String[] diretorio = logica.buscaDiretorioFotos(this);
		
		// carrega a foto do produto do cartão de memoria
		Bitmap bmp = BitmapFactory.decodeFile(diretorio[1]+ item.getCodigo() + ".jpg");

		img.setImageBitmap(bmp);

		// fecha a tela de itens
		mais.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				qtde++;
				quantidade.setText(String.valueOf(qtde));

			}

		});
		// fecha a tela de itens
		menos.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (qtde > 1) {
					qtde--;
					quantidade.setText(String.valueOf(qtde));
				}

			}

		});

		// fecha a tela de itens
		inclui.setOnClickListener(new View.OnClickListener() {
public void onClick(View v) {
                
				if (verificaLista(TelaDigitacao.itens, item)) { //textViewQtde.addTextChangedListener(Mask.insert("(##)#####-####",textViewQtde));
					
					//  erro cruelllll
					if (!quantidade.getText().toString().equals("")){
						qtde = Integer.parseInt(quantidade.getText().toString());
						confirmaIncremento();
						dialog.dismiss();
						
					}
					
				
				}

				else if (quantidade.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(),
							"Informe a quantidade!", Toast.LENGTH_LONG).show();
				} else {
                      
					int qtdade = Integer.parseInt(quantidade.getText().toString());
					// calcula o preço do produto de acordo com a tabela
					// escolhida ..;
					double pr = logica.calculaTabela2(AdaptadorProduto.codigoTabela,	item.getValorUnitario());
		
						item.setValorUnitario(pr);
						item.setQuantidade(qtdade);
						item.setValorTotal(item.getValorUnitario() * qtdade);

						TelaDigitacao.itens.add(item);
						Toast.makeText(getApplicationContext(), "Item Adicionado!",
								Toast.LENGTH_LONG).show();
						calculaPedido();

						qtde = 0;
						dialog.dismiss();

					
				}

			}

		});

		// exibe na tela o dialog
		dialog.show();
	}

	// Verifica se um determinado item esta na lista .....
	private boolean verificaLista(ArrayList<ItemListView> lista,
			ItemListView item) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getCodigo() == item.getCodigo()) {
				posicao = i;

				return true;
			}
		}
		return false;

	}

	// dialogo confirma incremento no pedido
	private void confirmaIncremento() {

		AlertDialog.Builder alerta = new AlertDialog.Builder(this);
		alerta.setTitle("Item já incluso no pedido! Deseja somar ao pedido?");
		alerta.setIcon(R.drawable.informativo);
		alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// incrementa o item do pedido.
				Toast.makeText(getApplicationContext(), "Item Adicionado!",
						Toast.LENGTH_LONG).show();
				TelaDigitacao.itens.get(posicao)
						.setQuantidade(
								TelaDigitacao.itens.get(posicao)
										.getQuantidade() + qtde);
				TelaDigitacao.itens.get(posicao).setValorTotal(
						TelaDigitacao.itens.get(posicao).getQuantidade()
								* TelaDigitacao.itens.get(posicao)
										.getValorUnitario());

				qtde = 0;
				calculaPedido();

			}
		});
		// método executado caso escolha não ..
		alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				qtde = 0;

			}
		});
		alerta.show();

	}

	// verifica status para mandar alertas ...
	public void verificaStatus(int qtd) {
		switch (qtd) {
		case 1:
			alerta("Pedido acima de 'R$ 1.000',é aconselhavel salvar o pedido agora");
			count++;
			break;
		case 2:
			alerta("Pedido acima 'R$ 2.000',é aconselhavel salvar o pedido agora");
			count++;
			break;
		case 3:
			alerta("Pedido acima 'R$ 4.000',é aconselhavel salvar o pedido agora");
			count++;
			break;
		}

	}

	private void alerta(String titulo) {
		AlertDialog.Builder alerta = new AlertDialog.Builder(this);
		alerta.setTitle(titulo);
		alerta.setIcon(R.drawable.informativo);
		alerta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

			}
		});

		alerta.show();
	}

	// método calcula o pedido
	private void calculaPedido() {
		double valor = 0;
		int qtd = 0;
		for (int i = 0; i < TelaDigitacao.itens.size(); i++) {

			valor = TelaDigitacao.itens.get(i).getValorTotal() + valor;
			qtd = TelaDigitacao.itens.get(i).getQuantidade() + qtd;
		}

		textViewQtde.setText("" + qtd);
		textViewVlTotal.setText(logica.limitaCasa(valor));

		// alerta ao usuario ...
		if (valor >= 1000 && valor <= 2000) {
			if (count == 1) {
				verificaStatus(count);

			}
		} else if (valor > 2000 && valor <= 4000) {
			if (count == 2) {
				verificaStatus(count);
			} else if (valor > 4000 && valor <= 6000) {
				if (count == 3) {
					verificaStatus(count);
				}
			}
		}

		qtd = 0;

	}

	@Override
	// quando entrar em pausa ..
	protected void onStart() {
		super.onStart();
		calculaPedido();
	}

}
