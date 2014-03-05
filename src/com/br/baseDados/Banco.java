package com.br.baseDados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Classe responsável por fazer todo o processo de manipulação do banco de
 * dados. toda a consulta , update, delete, insert deve ser feita nesta classe .
 */
public class Banco {
	private static SQLiteDatabase db;
	private final static String NOME_BANCO = "ForcaVendas";

	// private Context ctx;

	// Construtores da classe
	public Banco(Context ctx) {

		// this.ctx = ctx;
		// Abre o Banco de dados // NOME DO BANCO MODO DE ABERTURA NULLO
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}

	// Método retorna cursor com todos os dados da tabela
	public Cursor buscaDados(String tabela) {
		return db.query(tabela, null, null, null, null, null, null);
	}

	// Método retorna cursor com count(*) os dados da tabela pedido
	public Cursor buscaCount(String tabela, String where) {
		db.execSQL("");
		return db.query(tabela, null, where, null, null, null, null);

	}

	// Método retorna cursor com count(*) os dados da tabela pedido
	public Cursor buscaTotal(String tabela, String[] colunas, String where) {
		return db.query(tabela, colunas, where, null, null, null, null);

	}

	// Método retorna cursor com o join das tabela pedido x clientes
	public Cursor buscaDados(String tabela1, String tabela2, String colunas[],
			String where) {
		return db.query(tabela1 + " INNER JOIN " + tabela2, colunas, where,
				null, null, null, null);
	}

	// Método retorna um determinado dado do banco
	public Cursor buscaDados(String tabela, String colunas[], String where) {
		return db.query(tabela, colunas, where, null, null, null, null);

	}

	// Método cadastra no banco
	public boolean cadastra(String tabela, ContentValues valores, Context ctx) {
		
		
		try {
			// se cadastrar retorna verdadeiro, senão retorna falso
			db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE,
					null);
			Long resultado = db.insert(tabela, null, valores);
			if(resultado > 0){
				 return true;
			}else {
				  return false;
			}
	
		} catch (SQLException erro) {
			erro.printStackTrace();

			return false;
		}	 
	}

	// deleta um registro do banco
	public boolean excluiRegistro(String tabela, String where, Context ctx) {
		try {
			db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE,
					null);
			db.delete(tabela, where, null);
			return true;
		} catch (SQLException erro) {
			erro.printStackTrace();
			return false;
		}

	}

	// Atualiza um registro no banco
	public int atualizaRegistro(String tabela, ContentValues valores,
			String where, String[] whereArgs) {
		int count = db.update(tabela, valores, where, whereArgs);
		return count;

	}

	// fecha o banco
	public void fechaBanco() {
		db.close();
	}

}
