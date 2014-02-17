package com.br.baseDados;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.widget.Toast;

public class CriaTabelas {
	
	// Nome do Banco de Dados
	public static final String NOME_BANCO = "ForcaVendas";
	
	// Controle da versão do banco de dados
	private static final int VERSAO_BD = 2;
	
	// classe utilitaria para abrir, criar e ultilizar o banco de dados..
	private CriaBanco bd;
	
	// Script para fazer drop na tabela
	private static String[] DELETA_TABELA = new String[] {
			"DROP TABLE IF EXISTS FV_CIDADE", "DROP TABLE IF EXISTS FV_ESTADO",
			"DROP TABLE IF EXISTS FV_CLIENTE",
			"DROP TABLE IF EXISTS FV_FORNECEDOR",
			"DROP TABLE IF EXISTS FV_PRODUTO",
			"DROP TABLE IF EXISTS FV_PEDIDO",
			"DROP TABLE IF EXISTS FV_FORNECEDORxPRODUTO" ,
			"DROP TABLE IF EXISTS FV_ITEM",
			"DROP TABLE IF EXISTS FV_PEDIDOxPRODUTO",
			"DROP TABLE IF EXISTS FV_REPRESENTANTE",
			"DROP TABLE IF EXISTS FV_Planos",
			"DROP TABLE IF EXISTS FV_CONFIGURA"};
	
	// Script para criar as tabelas
	private static final String[] CRIA_TABELA = new String[] {
		//Tabela Estado
		"CREATE TABLE [FV_ESTADO] (" +
			"[CodEstado] INTEGER NOT NULL ON CONFLICT FAIL PRIMARY KEY AUTOINCREMENT, " +
			"[Nome] TEXT, " +
			"[UF] TEXT NOT NULL ON CONFLICT FAIL);",
			
		//Tabela Cliente
		"CREATE TABLE [FV_CLIENTE] (" +
			"[CodCli] INTEGER NOT NULL ON CONFLICT FAIL PRIMARY KEY, " +
			"[Nome] TEXT NOT NULL ON CONFLICT FAIL, " +
			"[CNPJ_CPF] TEXT NOT NULL ON CONFLICT FAIL, " +
			"[Endereco] TEXT NOT NULL, " +
			"[Bairro] TEXT NOT NULL, " +
			"[Cidade] TEXT NOT NULL, " +
			"[CodEstado] INT NOT NULL REFERENCES [FV_ESTADO]([CodEstado]), " +
			"[TelComercial] TEXT, " +
			"[Celular_Radio] TEXT, " +
			"[Email] TEXT);",


		//Tabela Fornecedor
		"CREATE TABLE [FV_FORNECEDOR] (" +
			"[CodFornece] INTEGER NOT NULL ON CONFLICT FAIL PRIMARY KEY, " +
			"[Nome] TEXT NOT NULL ON CONFLICT FAIL, " +
			"[CNPJ] TEXT NOT NULL ON CONFLICT FAIL, " +
			"[Endereco] TEXT NOT NULL, " +
			"[Bairro] TEXT NOT NULL, " +
			"[Cidade] TEXT NOT NULL, " +
			"[CodEstado] INT NOT NULL ON CONFLICT FAIL REFERENCES [FV_ESTADO]([CodEstado]) ON DELETE SET NULL, " +
			"[Telefone] TEXT);",
			
			

			//Tabela Representante
			"CREATE TABLE [FV_REPRESENTANTE] (" +
				"[CodRepresentante] INTEGER NOT NULL ON CONFLICT FAIL PRIMARY KEY, " +
				"[Nome] TEXT NOT NULL ON CONFLICT FAIL, " +
				"[CNPJ] TEXT NOT NULL ON CONFLICT FAIL, " +
				"[Usuario] TEXT NOT NULL, " +
				"[Senha] TEXT);",


		//Tabela Produto
		"CREATE TABLE [FV_PRODUTO] (" +
			"[CodProd] INTEGER NOT NULL ON CONFLICT FAIL PRIMARY KEY, " +
			"[Descricao] TEXT NOT NULL ON CONFLICT FAIL, " +
			"[Peso] REAL,  " +
			"[Preco] REAL," +
			"[Estoque] INTEGER);",


		//Tabela Fornecedor x Protudo
		"CREATE TABLE [FV_FORNECEDORxPRODUTO] (" +
			"[CodFornece] INT NOT NULL ON CONFLICT FAIL REFERENCES [FV_FORNECEDOR]([CodFornece]) ON DELETE SET NULL, " +
			"[CodProd] INT NOT NULL ON CONFLICT FAIL REFERENCES [FV_PRODUTO]([CodProd]) ON DELETE SET NULL);",

		//Tabela Pedido
		"CREATE TABLE [FV_PEDIDO] ("+
					 " [NumPed] INTEGER NOT NULL ON CONFLICT FAIL PRIMARY KEY AUTOINCREMENT, "+
					 " [CodCli] INTEGER NOT NULL ON CONFLICT FAIL REFERENCES [FV_CLIENTE]([CodCli]) ON DELETE SET NULL, "+
					 " [Representante] TEXT , "+
					 " [CondPagamento] TEXT NOT NULL, "+
					 " [TpVenda] TEXT NOT NULL, "+
					 " [TpPagamento] TEXT NOT NULL, "+
					  "[DataVenda] TEXT NOT NULL, "+
					  "[Status] CHAR NOT NULL, "+
					 " [FilialVenda] TEXT, "+
					 " [Obs] TEXT, "+
					 " [Prazo] TEXT);",
			
			
		

		//Tabela Pedido x Produtos
		"CREATE TABLE [FV_PEDIDOxPRODUTO] (" +
			"[CodPedido] INTEGER NOT NULL ON CONFLICT FAIL REFERENCES [FV_PEDIDO]([NumPed]) ON DELETE RESTRICT ON UPDATE RESTRICT, " +
			"[CodProduto] INTEGER NOT NULL ON CONFLICT FAIL REFERENCES [FV_PRODUTO]([CodProd]) ON DELETE RESTRICT ON UPDATE RESTRICT);",
			
			
	    //Tabela Itens 
	    "CREATE TABLE [FV_ITEM] ( " +
	    " [NumPed] INT REFERENCES [FV_PEDIDO]([NumPed]) ON DELETE SET NULL, " +                                             
	    " [CodProd] INT REFERENCES [FV_PRODUTO]([CodProd]) ON DELETE SET NULL,  " +
	    " [Quantidade] INT NOT NULL ON CONFLICT FAIL,  [VlUnitario] CURRENCY," +
	    " [VlTotal] CURRENCY);" ,
	    
	  //Tabela Planos 
	    "CREATE TABLE [FV_Planos] ( " +
	    " [codigo] INTEGER NOT NULL ON CONFLICT FAIL PRIMARY KEY AUTOINCREMENT, " +                                             
	    " [Descricao]  VARCHAR );  " ,
	  
				

	// Tabela Configurações do sistema
		"CREATE TABLE [FV_CONFIGURA] ("+
				  "[Representante] VARCHAR, "+
				  "[senha] VARCHAR, "+            
				  "[IpFtp] VARCHAR, "+
				  "[UserFtp] VARCHAR, "+ 
				  "[SenhaFtp] VARCHAR,"+
				  "[dirFtp] VARCHAR,"+
				  "[dirFotoProdutos] VARCHAR,"+
				  "[dirFotoExtra] VARCHAR);"
				  };
	
	

	// Construtor da Classe.
	public CriaTabelas(Context ctx) {
		// passa os parametros para a construtor da classe criaBanco
		  try {
				bd = new CriaBanco(ctx, CriaTabelas.NOME_BANCO, CriaTabelas.VERSAO_BD,
						CriaTabelas.CRIA_TABELA, CriaTabelas.DELETA_TABELA);
				// abre o banco no modo escrita para alterar também
				bd.getWritableDatabase();
				
		    }catch(SQLiteConstraintException  e){
		    	Toast.makeText(null, "Erro, não foi possivel criar o banco, informe o erro ao T.I de sua empresa : " + e,Toast.LENGTH_SHORT).show();
		    } finally{
		        bd.close();
		    }
	
		
  
	}

}
