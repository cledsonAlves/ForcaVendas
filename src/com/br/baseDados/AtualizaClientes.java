package com.br.baseDados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.ArrayList;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.br.gui.TelaAtualizaEstoque;
import com.br.objetos.ClienteJson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AtualizaClientes {
	private final String ip ;
	private final String usuario ;
	private final String senha ;
	private String diretorio;


	//  construtor
	public  AtualizaClientes(String ip, String usuario, String senha, String diretorio){
		this.ip = ip;
		this.usuario = usuario;
		this.senha = senha;
		this.diretorio = diretorio;
		
	}

	//  Método faz a leitura do arquivo json ( Clientes)
	public ArrayList<ClienteJson> buscaClientes(Context ctx) {
		File file = ctx.getFilesDir();
		File textfile = new File(file + "/Clientes.txt");

		FileInputStream input;
		try {
			input = ctx.openFileInput("Clientes.txt");
			byte[] buffer = new byte[(int) textfile.length()];

			input.read(buffer);

			String s = new String(buffer);
            
			// após a leitura ,  monta a lista de objetos clientes 
			ArrayList<ClienteJson> lista = new Gson().fromJson(s, new TypeToken<ArrayList<ClienteJson>>() {}.getType());
			
			if (textfile.exists()){
				//deleta o arquivo 
				boolean deleta = textfile.delete();	
				Log.i("Arquivo deletado", "deletou = " + deleta );
			}
			return lista;
		
		} catch (FileNotFoundException e) {		// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// Método carrega clientes do ftp ...
	public boolean baixaClientes(final Context ctx) {
		FTPClient ftp = new FTPClient();
		try {
			ftp.connect(ip);
			if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				ftp.login(usuario, senha);
				ftp.enterLocalPassiveMode();
				ftp.changeWorkingDirectory(diretorio+"/Clientes/");
			}
			if (ftp.isConnected()) {
				// abre um stream com o arquivo a ser enviado

				File file = ctx.getFileStreamPath("Clientes.txt");
				file.createNewFile();

				OutputStream os = new FileOutputStream(file.getAbsolutePath());
				ftp.retrieveFile("Clientes.txt", os);

				ftp.logout();
				ftp.disconnect();
				return true;
			} else {
				return false;
			}
			// Lembrar de tratar as exception
		} catch (SocketException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
