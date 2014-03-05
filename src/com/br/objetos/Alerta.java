package com.br.objetos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.br.kelma.R;
/**
 * 
 * @author c.santos
 * Classe que cria um dialogo de alerta  ...
 */

public class Alerta {
	public Alerta(Context ctx, String titulo, String mensagem){
	
			AlertDialog.Builder alerta = new AlertDialog.Builder(ctx);
			alerta.setTitle(titulo);
			alerta.setIcon(R.drawable.voltar32);
			alerta.setMessage(mensagem);
			
			// m�todo executado caso escolha sim
			alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					

				}
			});
			// m�todo executado caso escolha n�o ..
			alerta.setNegativeButton("N�o", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// startActivity(new Intent(Menu.this,Menu.class));
				}
			});
			alerta.show();
		}

}


