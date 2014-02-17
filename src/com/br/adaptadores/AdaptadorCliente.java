package com.br.adaptadores;

import java.util.List;

/**
 * Atualizado 17-02-2014
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.br.kelma.R;
import com.br.logica.Logica;
import com.br.objetos.Cliente;

public class AdaptadorCliente  extends BaseAdapter{
	Context ctx;
	List<Cliente> lista;
	Logica logica = new Logica();
	 private  RatingBar estrelaClientes ;
	
	public AdaptadorCliente(Context ctx, List<Cliente> lista){
		this.ctx = ctx;
		this.lista = lista;
		
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return lista.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lista.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		//recupera o carro da posição atual
		Cliente cliente = lista.get(position);
		
	
		
	// infla a  tela produtos 
		
		
		View tela_clientes = convertView;    
	    
		if (tela_clientes == null) {    
		        LayoutInflater inflater = (LayoutInflater) ctx      
		                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);      
		        tela_clientes = inflater.inflate(R.layout.tela_clientes, parent, false);      
		} 
		
		
	
		// atualiza o valor do textview
		
		//  busca a data da ultima compra e valor do cliente
		String[] dados = logica.buscaUltimaCompra(ctx,cliente.getCodigo());
		
		

		
	
		
		TextView nome = (TextView)tela_clientes.findViewById(R.id.textView1);
		nome.setText(cliente.getNome());
		
		TextView codigo = (TextView)tela_clientes.findViewById(R.id.textViewVlTotal);
		codigo.setText(String.valueOf(cliente.getCodigo()));
		
		
		 TextView tel= (TextView)tela_clientes.findViewById(R.id.textView4);
		 tel.setText(cliente.getTel1());
		 
		TextView email= (TextView)tela_clientes.findViewById(R.id.textView6);
		email.setText(cliente.getEmail());
		
		 TextView endereco= (TextView)tela_clientes.findViewById(R.id.textView8);
		 endereco.setText(cliente.getEnd());
		 
		 TextView cidade= (TextView)tela_clientes.findViewById(R.id.textView10);
		 cidade.setText(cliente.getCidade());
		 
		 TextView ultimaCompra = (TextView)tela_clientes.findViewById(R.id.textView12);
		 ultimaCompra.setText(dados[0]);
		 
		 TextView valorUltimaCompra = (TextView)tela_clientes.findViewById(R.id.textView14);
		 valorUltimaCompra.setText(dados[1]);
		 
		 //  Voltar aqui depois  ... 
		/**  RatingBar estrelaClientes =  (RatingBar)view.findViewById(R.id.estrelaClientes);
		  estrelaClientes.setEnabled(false);
		  estrelaClientes.setBackgroundColor(Color.RED);
		  estrelaClientes.setStepSize(3);**/
		 
		 
		
			
		 
		 
		 
		 
		
		
		//TextView cnpj= (TextView)view.findViewById(R.id.textViewCpf);
		//cnpj.setText(cliente.getCnpj());
		
	
		
		
	
	
		

		
	
	
		return tela_clientes;
	}

}
