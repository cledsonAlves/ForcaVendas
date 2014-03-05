package com.br.logica;

import java.util.ArrayList;

import com.br.gui.ItemListView;

public class GeraXML {
	
	
	public static void geraXml(ArrayList<ItemListView> itens){	
		for (int i =0; i < itens.size(); i++){
			String codigo = String.valueOf(itens.get(i).getCodigo());
			String descricao = itens.get(i).getDescricao();
			String qtd = String.valueOf(itens.get(i).getQuantidade());
			String vlUnit = String.valueOf(itens.get(i).getValorUnitario());
			String vlTotal = String.valueOf(itens.get(i).getValorTotal());
			String s = codigo + ";" +descricao+";"+qtd+";"+vlUnit+" ;"+vlTotal+ "\n";
		
			
		}
	
	 
		
	}

}
