package com.br.kelma;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.br.baseDados.CriaTabelas;
import com.br.baseDados.ManipulaBanco;
import com.br.gui.TelaAtualizaEstoque;
import com.br.gui.TelaCadastraProduto;
import com.br.gui.TelaCadastroCliente;
import com.br.gui.TelaConfigura;
import com.br.gui.TelaConsultaClientes;
import com.br.gui.TelaPedido;
import com.br.gui.TelaProdutos;
import com.br.gui.TelaRelatorios;
import com.br.gui.TelaVenda;
import com.br.logica.Logica;
import com.br.objetos.Produto;

public class Sistema extends Activity {
	private  ListView listMenu;
	private ArrayAdapter<String> adapter;
	public static ArrayList<Produto> produtos;
	private boolean visible = true;
	private Logica logica = new Logica();
	private int progresso;

	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

		listMenu = (ListView) findViewById(R.id.listViewMenu);
		
		ImageView img = (ImageView) findViewById(R.id.ImageViewSlide);
		img.setBackgroundColor(R.color.white);
		

		//carrega a foto do produto do cartão de memoria
		Bitmap bmp = BitmapFactory.decodeFile("/sdcard/fotoExtra/100102.jpg");
		//Bitmap bmp2 = BitmapFactory.decodeFile("/sdcard/fotoProdutos/default.png");
	
		  img.setImageBitmap(bmp);
		
		new CriaTabelas(this);
    	
    	//  carrega em memoria todos os produtos ...
    	ManipulaBanco mb = new ManipulaBanco(this);
		produtos = mb.buscaProduto();
		

		// opções do menu
		String[] opcoes = new String[]{"Digitar Pedido","Agendar Visitas","Consulta Pedidos","Consulta Clientes","Consulta Produtos","Relatórios"};
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,opcoes);
		listMenu.setAdapter(adapter);
		listMenu.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long obj) {
				// listMenu.setVisibility(View.GONE);
				switch (position) {

				// inicia a digitação do pedido
				case 0:
					startActivity(new Intent(getApplicationContext(),
							TelaVenda.class));
					break;
				// agendar visitas
				case 1:
					Intent calendar = new Intent(Intent.ACTION_VIEW);
					calendar.setClassName("com.android.calendar",
							"com.android.calendar.LaunchActivity");
					startActivity(calendar);
					break;
					
				// consulta pedidos
				case 2:
					startActivity(new Intent(getApplicationContext(),
							TelaPedido.class));
					break;
				// consulta clientes
				case 3:
					startActivity(new Intent(getApplicationContext(),
							TelaConsultaClientes.class));
					break;
				// consulta produtos
				case 4:
					TelaProdutos.listaProdutos =  produtos;
					startActivity(new Intent(getApplicationContext(),
							TelaProdutos.class));
					break;

				// relatorios
				case 5:
					startActivity(new Intent(getApplicationContext(),
							TelaRelatorios.class));
					break;
				}

			}
		});

	}
	
	//  esconde ou habilita o listMenu
	public void muda(View v){		
		if (visible){
			listMenu.setVisibility(View.GONE);
			visible = false;
		}else {
			listMenu.setVisibility(View.VISIBLE);
			visible = true;
		}
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.at_clientes:
			startActivity(new Intent(this, TelaAtualizaEstoque.class));
			return true;

		case R.id.at_produtos:
			return true;

		case R.id.cad_produto:
			startActivity(new Intent(this, TelaCadastraProduto.class));
			return true;

		case R.id.cad_cliente:
			startActivity(new Intent(this, TelaCadastroCliente.class));
			return true;
		case R.id.servidor:
			ExibeDialog();
			return true;

		case R.id.fotos:
			startActivity(new Intent(this, TelaConfigura.class));
			return true;

		default:
			return super.onOptionsItemSelected(item);
	    }
	}
	
	// sujeira 
	private void ExibeDialog() {
		progresso = 0;
		final Dialog dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_PROGRESS);
		dialog.setContentView(R.layout.servidor);
		// define o título do Dialog
		dialog.setTitle("Servidor Pedidos");
		this.setProgress(progresso);
		this.setProgressBarVisibility(true);
		dialog.findViewById(R.id.btn_Confirmar);

		// instancia os objetos que estão no layout customdialog.xml
		final Button testa = (Button) dialog.findViewById(R.id.btnTestaConexao);
		final Button salva = (Button) dialog.findViewById(R.id.btnSalva);

		final EditText ip = (EditText) dialog.findViewById(R.id.editCodRep);
		final EditText usuario = (EditText) dialog.findViewById(R.id.editCpfRep);
		final EditText senha = (EditText) dialog.findViewById(R.id.editSenha);
		
		
		final EditText diretorio = (EditText) dialog.findViewById(R.id.editDiretorio);

		String[] vetor = logica.buscaServidor(getApplicationContext());
		ip.setText(vetor[0]);
		usuario.setText(vetor[1]);
		//senha.setText(vetor[2]);
		diretorio.setText(vetor[3]);

		// testando a conexão com o servidor
		testa.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				FTPClient ftp = new FTPClient();
				// busca os dados no banco
				String[] vetor = logica.buscaServidor(getApplicationContext());
				String ip = vetor[0];
				try {
					ftp.connect(ip);
					// simula uma barra de progresso..
					fazerAlgoDemorado(1);
					// verifica se conectou com sucesso!
					if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
						ftp.disconnect();
						Toast.makeText(getApplicationContext(),"Conexão estabelecida com o servidor : " + ip,Toast.LENGTH_SHORT).show();
					} else {
						// erro ao se conectar
						ftp.disconnect();
						Toast.makeText(getApplicationContext(),"Conexão recusada", Toast.LENGTH_SHORT).show();
					}
				} catch (SocketException e) {
					Toast.makeText(getApplicationContext(),"Erro no servidor : " + e, Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		// salvando as configurações no banco ...
		salva.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// verifica se os campos estão vazios...
				if (ip.getText().equals("")) {
					Toast.makeText(getApplicationContext(),
							"Informe o ip do servidor", Toast.LENGTH_SHORT)
							.show();
					ip.requestFocus();
				} else if (usuario.getText().equals("")) {
					Toast.makeText(getApplicationContext(),
							"Informe o usuario do servidor", Toast.LENGTH_SHORT)
							.show();
					usuario.requestFocus();
				} else if (senha.getText().equals("")) {
					Toast.makeText(getApplicationContext(),
							"Informe a senha do servidor", Toast.LENGTH_SHORT)
							.show();
					ip.requestFocus();
				} else if (diretorio.getText().equals("")) {
					Toast.makeText(getApplicationContext(),
							"Informe o diretorio do servidor",
							Toast.LENGTH_SHORT).show();
					diretorio.requestFocus();
				} else {
					int retorno = logica
							.atualizaServidor(getApplicationContext(), ip
									.getText().toString(), usuario.getText()
									.toString(), senha.getText().toString(),
									diretorio.getText().toString());
					if (retorno > 0) {
						Toast.makeText(getApplicationContext(),
								"Servidor Atualizado", Toast.LENGTH_SHORT)
								.show();

						// finaliza o
						dialog.dismiss();
					} else {
						Toast.makeText(
								getApplicationContext(),
								"Erro, não foi possivel salvar o servidor no banco",
								Toast.LENGTH_SHORT).show();
					}
				}

				// finaliza o
				// dialog.dismiss();
			}
		});

		// exibe na tela o dialog
		dialog.show();
	}
	
	//  controlando barra de progresso
	private void fazerAlgoDemorado(final int incremento) {
		runOnUiThread(new Runnable() {
			public void run() {
				progresso += incremento;
				setProgress(progresso);
			}
		});
		
		SystemClock.sleep(250); // Pode ser algo mais útil!
	}
	
	public Runnable tarefaLonga = new Runnable() {
		public void run() {
			for (int i = 0; i < 20; i++) {
				fazerAlgoDemorado(500);
			}
			
			runOnUiThread(new Runnable() {				
				public void run() {
					//setProgressBarVisibility(false);					
				}
			});
		}
	};


}
