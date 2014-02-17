package com.br.adaptadores;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.br.gui.ItemListView;
import com.br.gui.TelaVenda2;
import com.br.kelma.R;
import com.br.logica.Logica;
import com.br.objetos.Pedido;

public class AdaptadorItens extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<Pedido> pedido;
	private Logica logica = new Logica();
	private Context ctx;


	public AdaptadorItens(Context context, ArrayList<Pedido> pedidos) {
		// Itens que preencheram o listview
		this.pedido = pedidos;
		this.ctx = context;
		// responsavel por pegar o Layout do item.
		mInflater = LayoutInflater.from(context);

	}

	public int getCount() {
		return pedido.size();
	}

	public Pedido getItem(int position) {
		return pedido.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		// Pega o item de acordo com a posição.
		Pedido item = pedido.get(position);
		// infla o layout para podermos preencher os dados

		View lista_pedido = convertView;    
	    
		if (lista_pedido == null) {    
		        LayoutInflater inflater = (LayoutInflater) ctx      
		                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);      
		        lista_pedido = inflater.inflate(R.layout.lista_pedido, parent, false);      
		}  
		




		
		//  busca o nome do cliente no banco , passando o codigo do cliente
		String nome = logica.buscaCliente(item.getCodigoCliente(),ctx);
		
		
		// ATENÇÂO   ATENÇÂO    ATENÇAO   ATENÇÂO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//  lembrar de tirar este código e pegar o calculo do pedido na tabela pedido 
		 ArrayList<ItemListView> listaItens = new  ArrayList<ItemListView> ();
         listaItens = logica.buscaItensPedido(ctx,item.getNumero());
         
         double vl = 0;
         int qtdItens = 0;
         int qtdVolume = 0;
         double peso = 0;
         for (int i = 0; i < listaItens.size(); i++){
        	 vl = listaItens.get(i).getValorTotal() + vl;
        	 qtdVolume = qtdVolume + listaItens.get(i).getQuantidade();
        	 peso = peso + listaItens.get(i).getPeso();
        	 qtdItens++;
         }
         
       
         String vlPedido = logica.limitaCasa(vl);
         
         /// ATENÇÂO   ATENÇÂO   ATENÇÂO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 
		 
		
		
		((TextView) lista_pedido.findViewById(R.id.textViewCliente)).setText(nome);
		((TextView) lista_pedido.findViewById(R.id.textViewNumPed)).setText(String.valueOf(item.getNumero()));
		((TextView) lista_pedido.findViewById(R.id.textViewTpVenda)).setText(String.valueOf(item.getTipoVenda()));
		((TextView) lista_pedido.findViewById(R.id.textViewData)).setText(item.getDataVenda());
		((TextView) lista_pedido.findViewById(R.id.textViewFilial)).setText(item.getTabela());
		((TextView) lista_pedido.findViewById(R.id.textViewFilial)).setTextColor(Color.RED);
		((TextView) lista_pedido.findViewById(R.id.textViewCondPag)).setText(item.getCondPagamento());
		((TextView) lista_pedido.findViewById(R.id.textViewTpPag)).setText(item.getTipoPagamento());
		((TextView) lista_pedido.findViewById(R.id.textViewQtdVolume)).setText(String.valueOf(qtdVolume));
		((TextView) lista_pedido.findViewById(R.id.textViewTotalItem)).setText(String.valueOf(qtdItens));
		((TextView) lista_pedido.findViewById(R.id.textViewTotalPedido)).setText(vlPedido);
		
		
		
		
		
		     
		
		
		//  verifica os estados do pedido 
		if (item.getStatus().equalsIgnoreCase("P")){
			((TextView) lista_pedido.findViewById(R.id.textViewStatus)).setText("Aguardando trasmissão");
			((TextView) lista_pedido.findViewById(R.id.textViewStatus)).setTextColor(Color.WHITE);
		}else if (item.getStatus().equalsIgnoreCase("T")){
		  ((TextView) lista_pedido.findViewById(R.id.textViewStatus)).setText("Pedido trasmitido");
		  ((TextView) lista_pedido.findViewById(R.id.textViewStatus)).setTextColor(Color.GREEN);
		}else if (item.getStatus().equalsIgnoreCase("ARQUIVADO")){
			  ((TextView) lista_pedido.findViewById(R.id.textViewStatus)).setText("Pedido trasmitido");
			  ((TextView) lista_pedido.findViewById(R.id.textViewStatus)).setTextColor(Color.GREEN);
	    }else if (item.getTipoVenda().contains("Or")){
			 ((TextView) lista_pedido.findViewById(R.id.textViewStatus)).setText("Aguardando Liberar");
			 ((TextView) lista_pedido.findViewById(R.id.textViewStatus)).setTextColor(Color.YELLOW);
		}
		
       
	
		

		return lista_pedido;
	}

}
