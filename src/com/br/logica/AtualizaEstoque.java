package com.br.logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * 
 * @author Cledson 
 * Classe que atualiza os produtos do banco de dados
 * recebe arquivo xml com os produtos para atualizar ....
 */

public class AtualizaEstoque {
	private Context ctx;
	private String ip;
	private String usuario;
	private String senha;
	private String diretorio;

	//  construtor 
	public AtualizaEstoque(Context ctx, String ip, String usuario, String senha, String diretorio) throws JDOMException {
		this.ctx = ctx;
		this.ip = ip;
		this.usuario = usuario;
		this.senha = senha;
		this.diretorio = diretorio;
		atualiza();
	}

	private Logica logica = new Logica();

	// faz o download da atualização  no site via ftp
	public boolean download() {
		FTPClient ftp = new FTPClient();
		try {
			ftp.connect(ip);
			if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				ftp.login(usuario, senha);
				ftp.enterLocalPassiveMode();
				ftp.changeWorkingDirectory(diretorio);
			}
			if (ftp.isConnected()) {
				// Cria arquivo para gravar a atualição na pasta Files
				// referencia para pegar o caminho file
				File file = ctx.getFileStreamPath("estoque.xml");

				// abre um out com o arquivo a ser recebido
				FileOutputStream out = new FileOutputStream(file);

				ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);
				ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
				ftp.enterLocalPassiveMode();
				// Faz o download do arquivo
				ftp.retrieveFile("estoque.xml", out);
				out.close();

				ftp.logout();
				ftp.disconnect();
				Log.i("download = ", "Arquivo baixado ..");
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

	// método atualiza os produtos
	public void atualiza() throws JDOMException {

		// se baixar arquivo ....então atualiza os itens
		if (download()== true) {

			File f = ctx.getFileStreamPath("estoque.xml");

			try {
				FileInputStream input = new FileInputStream(f);
				SAXBuilder builder = new SAXBuilder();

				Log.i("Arquivo ", "" + f.getAbsolutePath());

				Document doc = builder.build(input);
				Element root = (Element) doc.getRootElement();
				List<Element> itens = root.getChildren();

				Iterator<Element> i = itens.iterator();

				int codigo = 0;
				int estoque = 0;
				double vlUnitario = 0;
				// Iteramos com os elementos filhos, e filhos do dos filhos
				while (i.hasNext()) {
					Element element = (Element) i.next();

					codigo = Integer.parseInt(element.getChildText("codigo"));
					estoque = Integer.parseInt(element.getChildText("estoque"));
					vlUnitario = Double.parseDouble(element
							.getChildText("vlUnitario"));

					// / atualiza o estoque no banco
					logica.atualizaEstoque(codigo, estoque, ctx);

				}
				Toast.makeText(ctx,"Estoque Atualizado",Toast.LENGTH_LONG).show();

			} catch (IOException erro) {

			}
			finally {
				//  deleta o arquivo da pasta Files
				f.delete();
			}
		}
	}

}
