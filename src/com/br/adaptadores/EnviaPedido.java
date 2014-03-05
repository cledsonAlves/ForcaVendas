package com.br.adaptadores;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import android.content.Context;
import android.util.Log;

import com.br.gui.ItemListView;
import com.br.logica.Logica;
import com.br.objetos.Pedido;
import com.thoughtworks.xstream.XStream;

public class EnviaPedido extends Thread {
	private Logica logica;
	private Context ctx;

	public EnviaPedido(Context ctx) {
		this.ctx = ctx;
	}

	public boolean Esperar(File file, Pedido pedido, String ip, String usuario,
			String senha, String diretorio) {

		XStream xStream = new XStream();
		logica = new Logica();
		// String cliente = logica.buscaCliente(pedido.getCodigoCliente(), ctx);
		String arquivo = "0000"+pedido.getNumero() + " - " +pedido.getNomeCliente() + "-" 
				+ ".xml";

		// / gerando arquivo xml

		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(file)));
			out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			ArrayList<ItemListView> listaItens = new ArrayList<ItemListView>();
			listaItens = logica.buscaItensPedido(ctx, pedido.getNumero());
			List<Object> objetos = new ArrayList<Object>();

			// estrura do xml
			xStream.alias("Cabeçalho", Pedido.class);
			xStream.alias("Item", ItemListView.class);
			xStream.alias("Pedido", List.class);
			objetos.add(pedido);
			for (int y = 0; y < listaItens.size(); y++) {
				objetos.add(listaItens.get(y));
			}
			out.append(xStream.toXML(objetos));

			out.close();
			// envia o pedido
			return enviaPedido(arquivo, file, ip, usuario, senha, diretorio);

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			 // deleta o pedido.xml do aparelho 
			boolean ok = file.delete();
			//Log.i("Arquivo = ", "Deletou = " + ok);
		}
	}

	// Método envia pedido via ftp ...
	public boolean enviaPedido(String arquivo, File file, String ip,
			String usuario, String senha, String diretorio) {
		FTPClient ftp = new FTPClient();

		try {
			ftp.connect(ip);
			if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {

				ftp.login(usuario, senha);
				ftp.enterLocalPassiveMode();
				ftp.changeWorkingDirectory(diretorio);
			}
			if (ftp.isConnected()) {
				// abre um stream com o arquivo a ser enviado
				FileInputStream is = new FileInputStream(file);

				ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);
				ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
				ftp.storeFile(arquivo, is);
				ftp.logout();
				ftp.disconnect();
				Log.i("Executar = ", "Executei");
			//  envia o e-mail do pedido...
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
	//  método envia o e-mail do pedido ao cliente 
	

}