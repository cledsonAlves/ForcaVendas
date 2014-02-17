package com.br.kelma;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.br.baseDados.CriaTabelas;
import com.br.baseDados.ManipulaBanco;
import com.br.gui.Autentica;
import com.br.gui.TelaCadastraFornecedor;
import com.br.gui.TelaCadastraProduto;
import com.br.gui.TelaCadastraRepresentante;
import com.br.gui.TelaCadastroCliente;
import com.br.gui.TelaClientes;
import com.br.gui.TelaClientes2;
import com.br.gui.TelaConfigura;
import com.br.gui.TelaRelatorios;
import com.br.gui.TelaParametrosPedido;
import com.br.gui.TelaPedido;
import com.br.gui.TelaProdutos;
import com.br.gui.TelaProdutos2;
import com.br.gui.TelaVenda;
import com.br.gui.TelaVendedores;
import com.br.logica.Logica;
import com.br.objetos.Produto;

public class Menu extends Activity implements OnClickListener {
	final int cliente = 1;
	final int produto = 2;
	final int fornecedor = 3;
	final int configuracao = 4;
	final int conProd = 5;
	final int conCli = 6;
	final int conRep = 7;

	final int servidor = 8;
	final int parametros = 9;
	final int restaura = 10;
	final int cad_representante = 11;
	final int parametros_sis = 12; 
	private Logica logica = new Logica();
	private int progresso;
	private  TextView textRepresentante;
	Button btn;
	public static ArrayList<Produto> produtos;


	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		progresso = 0;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.kelma);
		setProgressBarVisibility(true);
		
		textRepresentante = (TextView)findViewById(R.id.textRepresentante);
	    textRepresentante.setText(Autentica.usuario);
	    
	    TextView edit = (TextView) findViewById(R.id.editVersao);

	    edit.setText(Html.fromHtml(
	            "Força de Vendas - versão 1.2 :::: Acesse nossa revista digital " +
	            "<a href=\"http://issuu.com/revistakelma/docs/kelma/\"> :: Acessar ::</a> "));
	    edit.setMovementMethod(LinkMovementMethod.getInstance());
		
		// método cria as tabelas do banco se não existir ou mudar a versão do
		// banco
	    //  
	
	    	new CriaTabelas(this);
	    	
	    	//  carrega em memoria todos os produtos ...
	    	ManipulaBanco mb = new ManipulaBanco(this);
			produtos = mb.buscaProduto();
			

			
	  

	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuItem item;

		// sub menu cadastro
		SubMenu subCad = menu.addSubMenu("Cadastro");
		subCad.setIcon(R.drawable.cad);
		item = subCad.add(0, cad_representante, 0, "Cadastrar Representante");
		item = subCad.add(0, cliente, 0, "Cadastrar cliente");
		item = subCad.add(0, produto, 0, "Cadastrar produto");
		item = subCad.add(0, fornecedor, 0, "Cadastrar Fornecedor");
		

		// SubMenu consulta
		SubMenu subConsulta = menu.addSubMenu("Pesquisar");
		subConsulta.setIcon(R.drawable.pesquisa64);
		item = subConsulta.add(0, conCli, 0, "Consultar Cliente");
		item = subConsulta.add(0, conProd, 0, "Consultar Produtos");
		item = subConsulta.add(0, conRep, 0, "Consultar Representantes");

		// cria um submenu configuraçoes ..
		SubMenu subConf = menu.addSubMenu("Configurações");
		subConf.setIcon(R.drawable.conf64);
		// item = subConf.add(0, criaBanco, 0, "Criar Banco");
		item.setIcon(R.drawable.bd);
		item = subConf.add(0, servidor, 0, "Servidor de pedidos");
		item = subConf.add(0, parametros, 0, "Parâmentros de pedidos");
		item = subConf.add(0, parametros_sis, 0, "Parâmentros de sistema");
		item = subConf.add(0, restaura, 0, "Restaurar configuração original");

		return true;
	}

	// Menus da Tela Inicial
	// btn_AgendaVisita
	public void btn_Venda(View v) {

		startActivity(new Intent(this, TelaVenda.class));

	}

	// Agendar Visita
	public void btn_Visita(View v) {

		 Intent calendar= new Intent(Intent.ACTION_VIEW);
		 calendar.setClassName
		 ("com.android.calendar","com.android.calendar.LaunchActivity");
		 startActivity(calendar);
		
	}

	// Clientes e Produtos
	public void btn_Consulta(View v) {
		startActivity(new Intent(this, TelaClientes2.class));
	}

	// Pedidos
	public void btn_Pedidos(View v) {
		startActivity(new Intent(this, TelaPedido.class));
			
	}

	//mensagens
	public void btn_mensagem(View v) {
		TelaProdutos.listaProdutos =  produtos;
    	startActivity(new Intent(this, TelaProdutos.class));
	}

	// relatorios ....
	public void btn_relatorio(View v) {
   	startActivity(new Intent(this, TelaRelatorios.class));
	}
	
	
 ///  Menu suspenso da tela inicial 
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		//  cadastra cliente
		case cliente:
			startActivity(new Intent(this, TelaCadastroCliente.class));
			return true;
	    //  cadastra produto
		case produto:
			startActivity(new Intent(this, TelaCadastraProduto.class));
			return true;
		// cadastra fornecedor ... 	
		case fornecedor:
			startActivity(new Intent(this, TelaCadastraFornecedor.class));
			return true;
		// cadastra representante ..	
		case cad_representante:
			startActivity(new Intent(this, TelaCadastraRepresentante.class));
			return true;	
		//  consulta clientes ...	
		case conCli:
			startActivity(new Intent(this, TelaClientes.class));
			return true;
		// consulta produtos	
		case conProd:
			startActivity(new Intent(this, TelaProdutos2.class));
			return true;
		//  consulta representantes	
		case conRep:
			startActivity(new Intent(this, TelaVendedores.class));
			return true;	
		case servidor:
			ExibeDialog();
			return true;
		case parametros:
			startActivity(new Intent(this, TelaParametrosPedido.class));
			return true;
		
	   case parametros_sis:
		startActivity(new Intent(this, TelaConfigura.class));
		return true;
	}
		
		return true;
	}

	public void onClick(View v) {
		Toast.makeText(this, "Pressionado", Toast.LENGTH_LONG).show();

	}

	// cria um alerta de confirmação
	private boolean alerta(String titulo, String msg) {
		AlertDialog.Builder alerta = new AlertDialog.Builder(Menu.this);
		alerta.setTitle(titulo);
		alerta.setIcon(R.drawable.bd);
		alerta.setMessage(msg);
		// método executado caso escolha sim
		alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				logica.deletaArquivo("numped.txt");

			}
		});
		// método executado caso escolha não ..
		alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				startActivity(new Intent(Menu.this, Menu.class));

			}
		});
		alerta.show();
		return true;
	}

	// Saindo do aplicativo pressionando o botão voltar
	@Override
	public void onBackPressed() {
		this.showYesNoDialog("Sair",
				"Deseja Realmente Sair do Força de Vendas?", this,
				new DialogInterface.OnClickListener() {

					// escolha sim
					public void onClick(DialogInterface dialog, int which) {
						System.exit(0);

					}
				}, new DialogInterface.OnClickListener() {

					// escolha não
					public void onClick(DialogInterface dialog, int which) {
						// Nothing

					}
				});
	}

	public void showYesNoDialog(String title, String message, Context context,
			android.content.DialogInterface.OnClickListener onClickListener,
			android.content.DialogInterface.OnClickListener onClickListener2) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
		alertDialog.setTitle(title);
		alertDialog.setMessage(message);
		alertDialog.setPositiveButton("Sim", onClickListener);
		alertDialog.setNegativeButton("Não", onClickListener2);
		alertDialog.show();
	}

	// Chamar Dialog
	public void ChamarDialog_click(View v) {
		ExibeDialog();
	}

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