package com.br.logica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.br.baseDados.ManipulaBanco;
import com.br.gui.ItemListView;
import com.br.objetos.Cliente;
import com.br.objetos.ClienteJson;
import com.br.objetos.Estado;
import com.br.objetos.ItemPedido;
import com.br.objetos.Pedido;
import com.br.objetos.Produto;
import com.br.objetos.Vendedor;
import com.thoughtworks.xstream.XStream;

/**0
 * 
 * @author Notebook Classe Lógica
 */

public class Logica {
	public static int contadorPedidos;
	private static String cliente;
	private   double  percent = 0.021;

	public Logica() {

	}

	// pega a data atual do mobile
	@SuppressLint("SimpleDateFormat")
	public static String DataAtual() {
		SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
		String data = new String(simpleFormat.format(new Date(System.currentTimeMillis())));
		return data;

	}

	// pega a data atual do mobile
	@SuppressLint("SimpleDateFormat")
	public static String DataHoraAtual() {
		SimpleDateFormat simpleFormat = new SimpleDateFormat(
				"dd/MM/yyyy HH:mm:ss");
		String data = new String(simpleFormat.format(new Date(System
				.currentTimeMillis())));
		return data;

	}

	// deleta arquivo txt
	public void deletaArquivo(String urlArquivo) {
		File file = new File(urlArquivo);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// gerar o arquivo pedido escrevendo o cabeçalho

	public void geraPedido(Context ctx, String cabecalho, String cliente) {
		this.cliente = cliente;
		String url = cliente + "_" + contadorPedidos + ".txt";

		try {
			File SDCard = Environment.getExternalStorageDirectory(); // esse é o
																		// dir
																		// /mnt/sdcard

			//if (SDCard.canWrite()) {
				//File file = new File(SDCard, url);
				// teste, pois esta negando a escrita no mnt, lembrar de alterar esta parte
			  File file = ctx.getFileStreamPath(url);
				FileWriter out = new FileWriter(file);
		
				
				out.write(cabecalho + "\n");
				out.close();
				Log.i("Arquivo Gerado - Cabeçalho escrito:", "" + url);

			//}
		} catch (IOException e) {
			Log.i("Não foi gerar o cabeçalho do pedido:", "" + e);
		}
	}

	// abre arquivo pedido escrevendo os itens
	public boolean geraPedido(Context ctx, ArrayList<ItemListView> itens) {
		String url = cliente + "_" + contadorPedidos + ".txt";
		// escreve no arquivo.txt
		File file = new File(url);

		try {
			// cria o arquivo

			File SDCard = Environment.getExternalStorageDirectory();// pega
																	// caminho
																	// /mnt/sdcard
			
			// teste, pois esta negando a escrita no mnt, lembrar de alterar esta parte
			  File f = ctx.getFileStreamPath(url);
		
			// abre o arquivo pedido
			FileWriter out = new FileWriter(f);

			for (int i = 0; i < itens.size(); i++) {
				String codigo = String.valueOf(itens.get(i).getCodigo());
				String descricao = itens.get(i).getDescricao();
				String qtd = String.valueOf(itens.get(i).getQuantidade());
				// chama metodo limita casa decimal
				String vlUnit = limitaCasa(itens.get(i).getValorUnitario());
				// cria string com o item
				String vlTotal = limitaCasa(itens.get(i).getValorTotal());
				String item = codigo + ";" + descricao + " ; " + qtd + ";"
						+ vlUnit + ";" + vlTotal + "\n";
				// escreve o item no pedido
				out.append(item);
				
			}
			// fecha o arquivo ...
			out.close();
           return true;
		} catch (IOException e) {
			Log.i("Erro:", "Não foi possivel criar o arquivo :" + e);
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * carrega um arquivo txt
	 * 
	 * @param ctx
	 * @param urlArquivo
	 */
	public String abrir(Context ctx, String urlArquivo) {

		try {
			File f = ctx.getFileStreamPath(urlArquivo);
			if (f.exists()) {
				FileInputStream in = ctx.openFileInput(urlArquivo);
				int tamanho = in.available();
				byte bytes[] = new byte[tamanho];
				in.read(bytes);
				String s = new String(bytes);
				contadorPedidos = Integer.parseInt(s);
				Log.i("Arquivo:", s);
				return s;
			} else {
				Log.i("Arquivo:", "Arquivo não existe");
				return null;
			}
		} catch (FileNotFoundException e) {
			Log.i("Arquivo não encontrado:", "" + e);
			return null;
		} catch (IOException e) {
			Log.i("Não foi possivel ler:", "" + e);
			return null;
		}

	}
 

	// Limita casa decimal
	public String limitaCasa(double valor) {
		// Limitar a duas casas decimais
		DecimalFormat df = new DecimalFormat("0.00");
		String str = df.format(valor);
		return str;
	}
	
	//  calcula tabela de preço 
	public String calculaTabela(int tabela, double preco){
		preco  = preco *  Math.pow(1  +  percent, (tabela -1));
		return limitaCasa(preco);
	}
//  calcula tabela de preço 
	public double calculaTabela2(int tabela, double preco){
		preco  = preco *  Math.pow(1  +  percent, (tabela -1));
		return preco;
	}

	// limpa os ';' da string
	public String[] scaneia(String s) {
		String texto[] = s.split(";");
		return texto;
	}
	
	
	
	//  gera pedido xml e retorna a referencia deles
	public ArrayList<String> geraXml(ArrayList<Pedido> pedido, Context ctx){
		ArrayList<String> lista = new ArrayList<String>();
		XStream xStream = new XStream();
		List<Object> objetos = new ArrayList<Object>();
		for(int i =0; i < pedido.size(); i++){
		try {
			String nomePed = pedido.get(i).getNumero()+  ".xml";
			  File file = ctx.getFileStreamPath(nomePed);
			  
			  PrintWriter arquivo = new PrintWriter(new BufferedWriter(new FileWriter(file))); 
			  arquivo.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			  
			  
			  //
			  ArrayList<ItemListView> listaItens= new ArrayList<ItemListView>();
			  // busca todos os itens do pedido ..
			  listaItens = buscaItensPedido(ctx, pedido.get(i).getNumero());
			  xStream.aliasAttribute("Pedido", "Pedido nome ");
			  xStream.alias("Cabeçalho", Pedido.class); 
			  xStream.alias("Item", ItemPedido.class);
			  xStream.alias("Pedido", List.class);
			  objetos.add(pedido);
			  for(int y =0; y< listaItens.size();y++){
				  objetos.add(listaItens.get(y));
			  }
			 //  pedido.setListaItens(listaItens);
			   	
			   
			  // objetos.add(listaItens);
			  
			     
			    arquivo.append(xStream.toXML(objetos)); 
			 
			    
			    arquivo.close();
			    lista.add(nomePed);
		}catch(IOException e){
			
		}}
		return lista;
	}


	/**
	 * Para baixo, métodos que fazem conexão com manipula banco !!! Necessário
	 * passar o contexto da classe que pede requisição ao banco
	 */
	
	
	
	//  verifica se vendedor existe no banco 
	public boolean verificaUsuario(String usuario, String senha, Context ctx){
		ManipulaBanco mb = new ManipulaBanco(ctx);
		return mb.buscaVendedor(usuario, senha);
	}
	
	// retorna uma coleção com estados brasileiros
	public ArrayList<Estado> buscaEstados(Context ctx) {
		ManipulaBanco mb = new ManipulaBanco(ctx);
		return mb.buscaEstados();

	}

	// retorna o numero do proximo cliente para cadastro
	public int buscaCodigoCliente(Context ctx) {
		ManipulaBanco mb = new ManipulaBanco(ctx);
		return mb.buscaCodigo("FV_CLIENTE","CodCli",null) + 1;
	}
 	// retorna o numero do proximo pedido
	public int buscaCodigoPedido(Context ctx) {
		ManipulaBanco mb = new ManipulaBanco(ctx);
		return mb.buscaCodigo("FV_PEDIDO","NumPed",null) + 1;
	}
	 // cadastra cliente ...
	 public boolean cadastraCliente(Cliente cliente, Context ctx) {
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 return mb.cadastraCliente(cliente);
	 }
	 
	 // cadastra cliente ...
	 public boolean cadastraClienteJson(ClienteJson cliente, Context ctx) {
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 return mb.cadastraClienteJson(cliente);
	 }
	 
	 //  cadastra produto 
	 public boolean cadastraProduto(Produto produto,Context ctx){
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 return mb.cadastraProduto(produto);
	 }
	 
	 //  cadastra representante
	 public boolean cadastraVendedor(Vendedor vendedor,Context ctx){
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 return mb.cadastraVendedor(vendedor);
	 }
	 
	 //  cadastra fornecedor
	/** public boolean cadastraFornecedor(Fornecedor fornecedor,Context ctx){
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 return mb.cadastraVendedor(vendedor);
	 }**/

	 // verifica se um produto existe 
	 public boolean verificaCodigo(int codigo, Context ctx){
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 return mb.verificaDado("FV_PRODUTO",null,"codProd = " + codigo);
		 
	 }		 
	 //  exclui um produto do banco
	 public boolean excluiRegistro(int codigo, Context ctx){
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 return mb.excluiRegistro("FV_PRODUTO","codProd = " + codigo);
		
	 }
	 
	 //  exclui um produto do banco
	 public boolean excluiPedido(int codigo, Context ctx){
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 return mb.excluiRegistro("FV_PEDIDO","NumPED = " + codigo);
		
	 }
	 
	 //  atualiza estoque do  produto no banco 
	 public int atualizaEstoque(int codigo, int qtd, Context ctx){
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 return mb.atualizaEstoque(codigo, qtd);
	 }
	 
	 // grava pedido no banco 
	 public boolean gravaPedido(Pedido pedido, ArrayList<ItemPedido> itens,Context ctx){
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 return mb.gravaPedido(pedido,itens);
	 }
	 // busca código de um cliente no banco
	 public int buscaCliente(String cliente,Context ctx){
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 return mb.buscaCodigo("FV_CLIENTE","CodCli","Nome = '"+ cliente + "'");
	 }
	 // Busca nome cliente no banco
	 public String buscaCliente(int codigo, Context ctx){
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 return mb.buscaNomeCliente("FV_CLIENTE",new String[]{"Nome"},"CodCli = " + codigo);
	 }
	 //  Busca email do cliente 
	 public String buscaEmail(String cliente, Context ctx){
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 return mb.buscaEmail(cliente);
	 }
	 
	 // busca itens do pedido  do banco 
	 public  ArrayList<ItemListView> buscaItensPedido(Context ctx, int numPedido){
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		return mb.buscaPedidos(numPedido);
		 
	 }
	 // busca todos os pedido do banco 
	 public ArrayList<Pedido> buscaPedido(Context ctx, String where ){
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 return mb.buscaPedido(where);
	 }
	//  verifica se um pedido existe no banco 
	 public boolean verificaPedido(Context ctx, int numPed ) { 
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 return mb.verificaDado("FV_Pedido",new String[]{},"NumPed = "+ numPed); 
	 }
	 
	 //  busca ultimo pedido do cliente
	 public String[] buscaUltimaCompra(Context ctx, int codCli){
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 return mb.buscaUltimaCompra(codCli);
	 }
	 	
	//  exclui itens do pedido  no banco ...		
	 public boolean excluiItensPedido(Context ctx, int numPed ) { 
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 return mb.excluiRegistro("FV_ITEM","NumPed = "+ numPed); 
	 } 
	 
	 //  Atualiza status de pedido enviado
	 public int atualizaStatus(Context ctx, int numPed){
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 ContentValues valores = new ContentValues();
		 valores.put("Status", "T");
		return  mb.atualizaRegistro("FV_PEDIDO", valores,"NumPed = "+ numPed);
	 }
	 //  Atualiza status de pedido enviado
	 public int atualizaTipoVenda(Context ctx, int numPed){
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 ContentValues valores = new ContentValues();
		 valores.put("TpVenda","Venda Normal");
		return  mb.atualizaRegistro("FV_PEDIDO", valores,"NumPed = "+ numPed);
	 }
	 
	 //  Atualiza status de pedido enviado
	 public int atualizaStatus2(Context ctx, int numPed){
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 ContentValues valores = new ContentValues();
		 valores.put("Status", "ARQUIVADO");
		return  mb.atualizaRegistro("FV_PEDIDO", valores,"NumPed = "+ numPed);
	 }
	 
	 //  Atualiza status de pedido enviado
	 public int atualizaStatus3(Context ctx, int numPed){
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 ContentValues valores = new ContentValues();
		 valores.put("Status", "Aguardando Trasmissao");
		return  mb.atualizaRegistro("FV_PEDIDO", valores,"NumPed = "+ numPed);
	 }
	 
	//  atualiza servidor 
	 public int atualizaServidor(Context ctx, String ip, String usuario, String senha, String diretorio){
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 ContentValues valores = new ContentValues();
		 valores.put("ipFTP", ip);
		 valores.put("userFtp", usuario);
		 valores.put("senhaFtp", senha);
		 valores.put("dirFtp", diretorio);
		 return mb.atualizaRegistro("FV_CONFIGURA", valores,"representante = '1'");
		 
	 }
	 public int atualizaDirFotos(Context ctx,String dirFoto, String dirFotoExtra){
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 ContentValues valores = new ContentValues();
		 valores.put("dirFotoProdutos", dirFoto);
		 valores.put("dirFotoExtra", dirFotoExtra);
		 return mb.atualizaRegistro("FV_CONFIGURA", valores, "representante = '1'");
		 
	 }
	 //  busca endereço do diretorio de foto dos produtos
		public String[] buscaDiretorioFotos(Context ctx){
			ManipulaBanco mb = new ManipulaBanco(ctx);
			return mb.buscaDiretorioFotos();
		}

	 // busca endereço de servidor de envio de pedidos
	 public String[] buscaServidor(Context ctx){
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 return  mb.buscaServidor();
		
	 }
	 
	 //  busca todos os  vendedores ... 
	 public ArrayList<Vendedor> buscaVendedor(Context ctx){
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 return mb.buscaVendedor();
	 }
	 
	 
	 //  busca todos os  planos de pagamento ... 
	 public ArrayList<String> buscaPlanos(Context ctx){
		 ManipulaBanco mb = new ManipulaBanco(ctx);
		 return mb.buscaPlanos();
	 }
	
	 
	
}
