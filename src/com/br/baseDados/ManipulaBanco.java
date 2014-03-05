package com.br.baseDados;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.br.gui.ItemListView;
import com.br.logica.Logica;
import com.br.objetos.Cliente;
import com.br.objetos.ClienteJson;
import com.br.objetos.Estado;
import com.br.objetos.ItemPedido;
import com.br.objetos.Pedido;
import com.br.objetos.Produto;
import com.br.objetos.Vendedor;

public class ManipulaBanco {
	Banco bd;
	private Context ctx;

	// Construtor pedindo um contexto..
	public ManipulaBanco(Context ctx) {
		this.ctx = ctx;
		bd = new Banco(ctx);
	}

	// retorna coleção de objetos com todos os clientes do banco
	public ArrayList<Cliente> buscaCliente() {
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		Cursor c = bd.buscaDados("FV_CLIENTE");
		// se existe cliente
		if (c.getCount() > 0) {
			while (c.moveToNext()) {
				// monta a lista de clientes
				Cliente cliente = new Cliente();
				cliente.setCodigo(c.getInt(0));
				cliente.setNome(c.getString(1));
				cliente.setCnpj(c.getString(2));
				cliente.setEnd(c.getString(3));
				// cliente.setBairro(c.getString(4));
				cliente.setCidade(c.getString(5));
				// cliente.setEstado(c.getString(6));
				cliente.setTel1(c.getString(7));
				cliente.setTel2(c.getString(8));
				cliente.setEmail(c.getString(9));
				// adiciona o cliente a lista
				lista.add(cliente);

			}

		}
		// fecha conexões
		c.close();
		bd.fechaBanco();

		// retorna lista
		return lista;
	}

	// retorna coleção de objetos com todos os produtos do banco
	public ArrayList<Produto> buscaProduto() {
		ArrayList<Produto> lista = new ArrayList<Produto>();
		Cursor c = bd.buscaDados("FV_PRODUTO");
		// se existe cliente
		if (c.getCount() > 0) {
			while (c.moveToNext()) {
				// monta a lista de produtos
				Produto produto = new Produto();
				produto.setCodigo(c.getInt(0));
				produto.setDescricao(c.getString(1));
				produto.setEmbalagem("cx");
				produto.setPeso(c.getDouble(2));
				produto.setPreco(c.getDouble(3));
				produto.setEstoque(c.getInt(4));
				produto.setFoto(c.getInt(0)+".png");
				lista.add(produto);

			}

		}
		// fecha conexões
		c.close();
		bd.fechaBanco();

		// retorna lista
		return lista;
	}

	// busca todos os estados do banco de dados e retorna uma coleçaõ de objetos
	// estados
	public ArrayList<Estado> buscaEstados() {
		ArrayList<Estado> listaEstados = new ArrayList<Estado>();
		Cursor c = bd.buscaDados("FV_ESTADO");
		if (c.getCount() > 0) {
			while (c.moveToNext()) {
				Estado estado = new Estado();
				estado.setCodEstado(c.getInt(0));
				estado.setNome(c.getString(1));
				estado.setUf(c.getString(2));
				listaEstados.add(estado);
			}
		}
		// fecha conexões
		c.close();
		bd.fechaBanco();
		return listaEstados;
	}

	/**
	 * Busca pedido e retorna um arrayList preenchido
	 * 
	 * @param lista
	 *            para ser preechida
	 * @param dataDe
	 *            data inicio , dataAte da fim
	 */

	// busca um código do banco

	public int buscaCodigo(String tabela, String colunas, String where) {
		Cursor c = bd.buscaDados(tabela, new String[] { colunas }, where);
		int cod = 0;
		if (c.moveToLast())
			cod = c.getInt(0);
		c.close();
		bd.fechaBanco();
		return cod;
	}

	
	// cadastra cliente
	public boolean cadastraCliente(Cliente cliente) {
		String estado = cliente.getEndereco().getEstado();

		
		Cursor c = bd.buscaDados("FV_ESTADO", new String[] { "codEstado" },
				"nome = '" + estado + "'");
		c.moveToNext();
		int codEstado = c.getInt(0);
		c.close();

		ContentValues valores = new ContentValues();
		valores.put("CodCli", cliente.getCodigo());
		valores.put("Nome", cliente.getNome());
		valores.put("CNPJ_CPF", cliente.getCnpj());
		valores.put("Endereco", cliente.getEndereco().getEndereco());
		valores.put("Bairro", cliente.getEndereco().getBairro());
		valores.put("Cidade", cliente.getEndereco().getCidade());
		valores.put("CodEstado", codEstado);
		valores.put("TelComercial", cliente.getTel1());
		valores.put("Celular_Radio", cliente.getTel2());
		valores.put("Email", cliente.getEmail());

		// manda para classe banco fazer o cadastro ..
		return bd.cadastra("FV_CLIENTE", valores, ctx);
	}
	
	
	// cadastra cliente
		public boolean cadastraClienteJson(ClienteJson cliente) {


			ContentValues valores = new ContentValues();
			valores.put("CodCli", cliente.getCodCli());
			valores.put("Nome", cliente.getNome());
			valores.put("CNPJ_CPF", cliente.getCnpj());
			valores.put("Endereco", cliente.getEndereco());
			valores.put("Bairro", cliente.getBairro());
			valores.put("Cidade", cliente.getCidade());
			valores.put("CodEstado", cliente.getCodEstado());
			valores.put("TelComercial", cliente.getTelComercial());
			valores.put("Celular_Radio", cliente.getRadio());
			valores.put("Email", cliente.getEmail());

			// manda para classe banco fazer o cadastro ..
			return bd.cadastra("FV_CLIENTE", valores, ctx);
		}

	// Cadastra produto
	public boolean cadastraProduto(Produto produto) {
		ContentValues valores = new ContentValues();
		valores.put("CodProd", produto.getCodigo());
		valores.put("Descricao", produto.getDescricao());
		valores.put("Peso", produto.getPeso());
		valores.put("Preco", produto.getPreco());
		valores.put("Estoque", produto.getEstoque());
		// manda para classe banco fazer o cadastro ..
		return bd.cadastra("FV_PRODUTO", valores, ctx);

	}
	
	//  Busca preço produto
	public Double buscaPreco(int codigo){
		Cursor c =  bd.buscaDados("FV_PRODUTO", new String[]{"preco"},"codProd = " + codigo);
		if(c.getCount() > 0){
			c.moveToNext();
			return c.getDouble(0);
			
		}else {
			return 0.0;
		}
		 
	}
	
	//  Busca poduto , lembra depois  de fazer um metodo somente busca dados
	public Produto buscaProduto(int codigo){
		
		Cursor c =  bd.buscaDados("FV_PRODUTO", null,"codProd = " + codigo);
		Produto prod = new Produto();
		
		
		if(c.getCount() > 0){
			c.moveToNext();
			prod.setCodigo(c.getInt(0));
			prod.setDescricao(c.getString(1));
			prod.setPreco(c.getDouble(3));
			
			return prod;
			
		}else {
			return null;
		}
		 
	}
	
	
	// Cadastra Representante
		public boolean cadastraVendedor(Vendedor vendedor) {
			ContentValues valores = new ContentValues();
			valores.put("CodRepresentante", vendedor.getCodigo());
			valores.put("Nome", vendedor.getNome());
			valores.put("CNPJ", vendedor.getCpf());
			valores.put("Usuario",vendedor.getUsuario());
			valores.put("Senha", vendedor.getSenha());
			// manda para classe banco fazer o cadastro ..
			return bd.cadastra("FV_Representante", valores, ctx);

		}

	// verifica se um dado existe no banco
	public boolean verificaDado(String tabela, String[] colunas, String where) {
		Cursor c = bd.buscaDados(tabela, colunas, where);
		// se o dado existe retorna falso, senão retorna verdadeiro
		if (c.getCount() == 0) {
			// fecha conexões
			c.close();
			bd.fechaBanco();
			return false;
		} else {
			// fecha conexões
			c.close();
			bd.fechaBanco();
			return true;
		}

	}

	// exclui registro do banco
	public boolean excluiRegistro(String tabela, String where) {
		return bd.excluiRegistro(tabela, where, ctx);
	}
	
	//  atualiza produto 
	public int atualizaEstoque( int codigo ,int qtd){
		ContentValues valores = new ContentValues();
		valores.put("Estoque",qtd);
		int count =  bd.atualizaRegistro("FV_PRODUTO",valores,"codprod = " + codigo , null);
		bd.fechaBanco();
		return count;
	}
	
	//  atualiza pedido
	public int atualizaPedido( int numped ,String tabela, String prazo, String obs ){
		ContentValues valores = new ContentValues();
		valores.put("CondPagamento",prazo);
		valores.put("Prazo", tabela);
		valores.put("Obs", obs);
		int count =  bd.atualizaRegistro("FV_PEDIDO",valores,"numped = " + numped , null);
		bd.fechaBanco();
		return count;
	}


	

	// método grava pedido no banco
	public boolean gravaPedido(Pedido pedido, ArrayList<ItemPedido> itens) {
		ContentValues valores = new ContentValues();
		valores.put("NumPed", pedido.getNumero());
		valores.put("Representante", pedido.getRepresentante());
		valores.put("CodCli", pedido.getCodigoCliente());
		valores.put("CondPagamento", pedido.getCondPagamento());
		valores.put("TpVenda", pedido.getTipoVenda());
		valores.put("TpPagamento", pedido.getTipoPagamento());
		valores.put("DataVenda", pedido.getDataVenda());
		valores.put("Status", "P");
		valores.put("FilialVenda", pedido.getFilialVenda());
		valores.put("Obs", pedido.getObs());
		valores.put("Prazo", pedido.getTabela());
		if (bd.cadastra("FV_PEDIDO", valores, ctx) == true) {
			return gravaItensPedido(itens);
		}
		return false;

	}

	// método grava os itens do pedido no banco
	public boolean gravaItensPedido(ArrayList<ItemPedido> itens) {
		for (int i = 0; i < itens.size(); i++) {
			ContentValues valores = new ContentValues();
			valores.put("NumPed", itens.get(i).getNumPedido());
			valores.put("CodProd", itens.get(i).getCodProduto());
			valores.put("Quantidade", itens.get(i).getQuantidade());
			valores.put("VlUnitario", itens.get(i).getVlUnitario());
			valores.put("VlTotal", itens.get(i).getVlTotal());
			bd.cadastra("FV_ITEM", valores, ctx);
			//  atualizo itens no banco, retirando do estoque o que foi vendido 
			atualizaItens(itens.get(i).getCodProduto(), itens.get(i).getQuantidade());
			
		}
		bd.fechaBanco();
		return true;

	}
	
	//  atualiza itens estoque no banco
	public void atualizaItens(int codigo, int qtd ){
		// busco o estoque atual no banco do item 
		Cursor c = bd.buscaDados("FV_PRODUTO",new String []{"Estoque"},"codprod = "+ codigo );
		c.moveToNext();
		int estoque = c.getInt(0);
		estoque = estoque - qtd;
		//  atualizo o estoque novo no banco ...
		atualizaEstoque(codigo, estoque);
		
	}

	// busca itens do pedido no banco
	public ArrayList<ItemListView> buscaPedidos(int numPedido) {
		ArrayList<ItemListView> lista = new ArrayList<ItemListView>();
		Cursor c = bd
				.buscaDados(
						"FV_Item i",
						new String[] { " i.[NumPed],i.[CodProd],(select fv_produto.[Descricao] from fv_produto where fv_produto.[CodProd]=i.[codprod]) as Descricao, i.Quantidade,i.[VlUnitario],i.[VlTotal]" },
						" i.[NumPed]  in (select fv_pedido.[NumPed] from fv_pedido where fv_pedido.[NumPed]= i.[NumPed] and i.Numped = "+ numPedido + ")");
		if (c.getCount() > 0) {
			while (c.moveToNext()) {
				ItemListView item = new ItemListView();
				// item.setNumPedido(c.getInt(0));
				item.setCodigo(c.getInt(1));
				item.setDescricao(c.getString(2));
				item.setValorTotal(c.getDouble(5));
				item.setValorUnitario(c.getDouble(4));
				item.setQuantidade(c.getInt(3));
				lista.add(item);
			}
		}
		c.close();
		bd.fechaBanco();
		return lista;
	}
	


	// busca todos os pedidos do banco
	public ArrayList<Pedido> buscaPedido(String where) {
		ArrayList<Pedido> lista = new ArrayList<Pedido>();
		Cursor c = bd.buscaDados("FV_PEDIDO", null, where);
		if (c.getCount() > 0) {
			while (c.moveToNext()) {
				Pedido pedido = new Pedido();
				pedido.setNumero(c.getInt(0));
				pedido.setCodigoCliente(c.getInt(1));
				pedido.setRepresentante(c.getString(2));
				pedido.setCondPagamento(c.getString(3));
				pedido.setTipoVenda(c.getString(4));
				pedido.setTipoPagamento(c.getString(5));
				pedido.setDataVenda(c.getString(6));
				pedido.setStatus(c.getString(7));
				pedido.setFilialVenda(c.getString(8));
				pedido.setObs(c.getString(9));
				pedido.setTabela(c.getString(10));
				
				
				// adiciona o pedido na lista ..
				lista.add(pedido);
			}
		}	
		//  fechando conexões
		c.close();
		bd.fechaBanco();
		return lista;

	}
	
	//  busca count de tabela
	public int buscaCount(String tabela, String where){
		Cursor c =  bd.buscaCount(tabela,where);
	
		int total = c.getCount();
		
		
		c.close();
		//bd.fechaBanco();
		return total;
	}
	
	
//  busca count de tabela pedidos  --  
    public String buscaSumToTal (String tabela, String where){
		Cursor c =  bd.buscaTotal(tabela, new String[]{"sum(vltotal)"},where);
		Logica l = new Logica();
		
		double total = 0;
		c.moveToNext();
		total =  c.getDouble(0);
		c.close();
		//bd.fechaBanco();
		return  l.limitaCasa(total);
}
  
	//  busca ultima compra
	public String[] buscaUltimaCompra( int codcli){
		

		Cursor c = bd.buscaDados("FV_PEDIDO",new String[]{"dataVenda","(select sum (vltotal) from FV_ITEM where FV_Item.numped = FV_PEDIDO.numped)"},"numped in (select max(numped) from FV_PEDIDO where codcli = " + codcli +" and TpVenda = 'Venda Normal')" );
		if (c.getCount() > 0) {
		        c.moveToNext();
				String dataVenda =  c.getString(0);
				String vlTotal =  c.getString(1);
				String[] retorno = new String[]{dataVenda,vlTotal};
				
				// fecha as conexoes
				c.close();
				bd.fechaBanco();
				return retorno;
				
		}else{
			
			String[] retorno = new String[]{"Não tem venda ainda","0,00"};
			// fecha as conexoes
			c.close();
			bd.fechaBanco();
			return retorno;
		
		}
	
			
	}
	
	//  busca planos 
	public ArrayList<String> buscaPlanos(){
		ArrayList<String> planos = new ArrayList<String>();
		Cursor c = bd.buscaDados("FV_Planos");
		if (c.getCount() > 0){
			while(c.moveToNext()){
				planos.add(c.getString(1));
				
			}
		}
		return planos;
	}
	

	// busca o nome de um cliente no banco |
	public String buscaNomeCliente(String tabela, String[] colunas, String where) {
		Cursor c = bd.buscaDados(tabela, colunas, where);
		if (c.getCount() > 0) {
			while (c.moveToNext()) {
				return c.getString(0);
			}
		}
		c.close();
		bd.fechaBanco();
		return null;
	}
	//  busca e-mail cliente
	public String buscaEmail(String cliente){
		Cursor c = bd.buscaDados("FV_Cliente",new String[]{"Email"},"Nome  = '" + cliente + "'");
		if (c.getColumnCount()> 0){
			while(c.moveToNext()){
				return c.getString(0);
			}
		}
		c.close();
		bd.fechaBanco();
		return null;
	}

	// atualiza registro no banco
	public int atualizaRegistro(String tabela, ContentValues valores,
			String where) {
		int count = bd.atualizaRegistro(tabela, valores, where, null);
		bd.fechaBanco();
		return count;
	}

	// busca dados do servidor
	public String[] buscaServidor() {

		Cursor c = bd.buscaDados("FV_CONFIGURA");
		// cria vetor com ip , usuario e senha
		if (c.getCount() > 0) {
			c.moveToNext();
			String[] vetor = new String[4];
			vetor[0] = c.getString(2);
			vetor[1] = c.getString(3);
			vetor[2] = c.getString(4);
			vetor[3] = c.getString(5);
			c.close();
			bd.fechaBanco();
			return vetor;
		} else {
			c.close();
			bd.fechaBanco();
			return null;
		}

	}
	
	

//  busca diretorio de fotos
	public String[] buscaDiretorioFotos(){
		Cursor c = bd.buscaDados("FV_CONFIGURA");
		// cria um vetor com os dados 
		if (c.getCount() > 0){
			c.moveToNext();
			String[] vetor = new String[2];
			vetor[0]= c.getString(6);
			vetor[1]= c.getString(7);
			//  fecha as conexões;
			c.close();
			bd.fechaBanco();
			return vetor;
		}else {
			c.close();
			bd.fechaBanco();
			return null;
		}
		
	}
	//  busca todos  vendedores
	public ArrayList<Vendedor> buscaVendedor(){
		 
		ArrayList<Vendedor> lista = new ArrayList<Vendedor>();
		
		 Cursor c =  bd.buscaDados("FV_Representante");
		if (c.getCount() > 0){
			while(c.moveToNext()){
				Vendedor v = new Vendedor();
				v.setCodigo(c.getInt(0));
				v.setNome(c.getString(1));
				v.setCpf(c.getString(2));
				v.setUsuario(c.getString(3));
				v.setSenha(c.getString(4));
				lista.add(v);
				
			}
		}
		 
		 
		return lista;
		
	}
	// verifica se o  vendedor existe
	public boolean buscaVendedor(String usuario, String senha){
		String where = "Usuario = '"+ usuario +"'  and Senha = '"+ senha+"'";
		Cursor c = bd.buscaDados("FV_Representante", new String[]{"Usuario","Senha"},where);
		c.moveToFirst();
		if (c.getCount() > 0){
			return true;
		}else {
			return false;
		}
		
	}

}
