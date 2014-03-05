package com.br.adaptadores;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.br.kelma.R;
import com.br.logica.Logica;
import com.br.objetos.Pedido;

public class AdaptadorEstado extends BaseAdapter {
	Context ctx;
	List<Pedido> listaPedido;
	private Logica logica;
	//  construtor 
	public AdaptadorEstado(Context ctx, List<Pedido>lista){
		this.ctx = ctx;
		this.listaPedido = lista;
		logica = new Logica();
	}
	
	
	public int getCount() {
		// TODO Auto-generated method stub
		return listaPedido.size() ;
	}

	public Object getItem(int posicao) {
		// TODO Auto-generated method stub
		return listaPedido.get(posicao);
	}

	public long getItemId(int posicao) {
		// TODO Auto-generated method stub
		return posicao;
	}

	public View getView(int posicao, View convertView, ViewGroup parent) {
		// recupera a posição atual do estado
		Pedido pedido = listaPedido.get(posicao);
		// infla a  tela Estados 
		
		
		View tela_estados = convertView;    
	    
		if (tela_estados == null) {    
		        LayoutInflater inflater = (LayoutInflater) ctx      
		                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);      
		        tela_estados = inflater.inflate(R.layout.tela_estados, parent, false);      
		}
	
		
		
		
		// atualiza o text do xml
		TextView nome = (TextView)tela_estados.findViewById(R.id.textViewEstado);
		// busca o nome do cliente no banco 
		nome.setText(logica.buscaCliente(pedido.getCodigoCliente(), ctx));
		
		TextView status = (TextView)tela_estados.findViewById(R.id.textViewUf);
		status.setText(pedido.getStatus());
		
		
		return tela_estados;
	}

}
