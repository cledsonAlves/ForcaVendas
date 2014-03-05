package com.br.adaptadores;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.kelma.R;
import com.br.logica.Logica;
import com.br.objetos.Produto;

public class AdaptadorProduto extends BaseAdapter {
	Context ctx;
	List<Produto> listaProdutos;
	private Logica logica;
	public static int codigoTabela;
	//  construtor 
	public AdaptadorProduto(Context ctx, List<Produto>lista){
		this.ctx = ctx;
		this.listaProdutos = lista;
		logica = new Logica();
		
	}
	
	
	public int getCount() {
		// TODO Auto-generated method stub
		return listaProdutos.size() ;
	}

	public Object getItem(int posicao) {
		// TODO Auto-generated method stub
		return listaProdutos.get(posicao);
	}

	public long getItemId(int posicao) {
		// TODO Auto-generated method stub
		return posicao;
	}

	public View getView(int posicao, View convertView, ViewGroup parent) {
		// recupera a posição atual do produto
		Produto prod = listaProdutos.get(posicao);
		// infla a  tela produtos 
		
		
		View tela_produtos = convertView;    
	    
		if (tela_produtos == null) {    
		        LayoutInflater inflater = (LayoutInflater) ctx      
		                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);      
		        tela_produtos = inflater.inflate(R.layout.tela_produtos, parent, false);      
		}   
		
		//  desta forma o programa estava travando ...   caso eduardo  .. foi resolvido
		//  com o codigo acima ..  overflow de memoria
		/**LayoutInflater inflater = 
			(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View tela_produtos = inflater.inflate(R.layout.tela_produtos, null);**/
		
		// atualiza o text do xml
		TextView produto = (TextView)tela_produtos.findViewById(R.id.textState);
		produto.setText(prod.getDescricao());
		
		TextView codigo = (TextView)tela_produtos.findViewById(R.id.textCodProd);
		codigo.setText(String.valueOf(prod.getCodigo()));
		
	//	TextView embalagem = (TextView)view.findViewById(R.id.textCapital);
	//	embalagem.setText(prod.getEmbalagem());
		
		
		
		TextView preco = (TextView)tela_produtos.findViewById(R.id.textArea);
		
		//calcula o preço do produto  de acordo com a tabela escolhida ..;
		String pr = logica.calculaTabela(codigoTabela, prod.getPreco());
		
		preco.setText(pr);
		
		/**TextView estoque = (TextView)view.findViewById(R.id.textViewEstoque);
		
		int qtdEstoque = prod.getEstoque();
		
		if ( qtdEstoque <= 0){
			produto.setTextColor(Color.RED);
			produto.setLinkTextColor(Color.BLUE);
			estoque.setTextColor(Color.RED);
		}
		
	//	estoque.setText(String.valueOf(qtdEstoque));**/
		
		//carrega a foto do produto do cartão de memoria
		/** código critico, se for android.4.0 usar  /mnt/extsd   , inferior usar /sdcard/ **/
		
		//  busca o diretorio do produto no banco
		String[] diretorio = logica.buscaDiretorioFotos(ctx);
		
		  File f = new File(diretorio[0]+prod.getFoto()); 
		  

          //peguei minha imagem
         

          //transformei em bytes
          FileInputStream fileInputStream = null;
          byte[] bFile = new byte[(int) f.length()];

  try {
  fileInputStream = new FileInputStream(f);
  fileInputStream.read(bFile);
  fileInputStream.close();

  for (int i = 0; i < bFile.length; i++) {
     
  }

  }catch(Exception e){
      e.printStackTrace();
  }
		
  //Pegueis os bytes e converti em Bitmap
  Bitmap bmp = BitmapFactory.decodeByteArray(bFile,0,bFile.length); 
		//Bitmap bmp2 = BitmapFactory.decodeFile("/sdcard/fotoProdutos/default.png");

		ImageView img = (ImageView)tela_produtos.findViewById(R.id.imageState);
	
		  img.setImageBitmap(bmp);
		
		
		return tela_produtos;
	}

}
