package com.br.gui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.br.kelma.R;
import com.br.logica.Logica;

public class TelaFoto extends Activity {
	 Logica logica;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.produto);
		logica = new Logica();
		 
		ImageView img = (ImageView)findViewById(R.id.imageView);
		
		//  busca os diretorios das fotos 
		String[] diretorio = logica.buscaDiretorioFotos(this);
		
		
		//carrega a foto do produto do cartão de memoria
		Bitmap bmp = BitmapFactory.decodeFile(diretorio[1]+TelaProdutos.codigo+".jpg");
		//Bitmap bmp2 = BitmapFactory.decodeFile("/sdcard/fotoProdutos/default.png");
	
		  img.setImageBitmap(bmp);
		 
		
	}
	


}
