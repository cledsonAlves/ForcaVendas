package com.br.gui;



import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.br.baseDados.ManipulaBanco;


public class ReadData {
	Context ctx;
	 ManipulaBanco bd;
	 int count ;
	
	//  construtor .... 
	public ReadData(Context ctx){
		this.ctx = ctx;
		
	bd = new ManipulaBanco(this.ctx);
	   count = bd.buscaCount("FV_PEDIDO","dataVenda like '%/11/%';");
	  //Log.i("Total", "" + count);
		
	}
	
	public  ArrayList<String> groupsList;
	public  ArrayList<ArrayList<ArrayList<String>>> childsList;

	public ArrayList<String> getGroupData() {
		groupsList = new ArrayList<String>();
		groupsList.add("Janeiro");
		groupsList.add("Fevereiro");
		groupsList.add("Março");
		groupsList.add("Abril");
		groupsList.add("Maio");
		groupsList.add("Junho");
		groupsList.add("Julho");
		groupsList.add("Agosto");
		groupsList.add("Setembro");
		groupsList.add("Outubro");
		groupsList.add("Novembro");
		groupsList.add("Dezembro");
		return groupsList;
	}

	public ArrayList<ArrayList<ArrayList<String>>> getChildData() {


		
		childsList = new ArrayList<ArrayList<ArrayList<String>>>();
		
	//  janeiro
		childsList.add(new ArrayList<ArrayList<String>>());
		childsList.get(0).add(new ArrayList<String>());
		childsList.get(0).get(0).add("Quantidades de Pedidos :  "+ bd.buscaCount("FV_PEDIDO","dataVenda like '%/01/%';"));
		childsList.get(0).add(new ArrayList<String>());
		childsList.get(0).get(1).add("Pedidos Venda Normal: "  + bd.buscaCount("FV_PEDIDO","dataVenda like '%/01/%' and TPVenda like '%Venda Normal%';"));
		childsList.get(0).add(new ArrayList<String>());
		childsList.get(0).get(2).add("Pedidos Bonificados :  "+ bd.buscaCount("FV_PEDIDO","dataVenda like '%/01/%' and TPVenda like '%Bonific%';"));
		childsList.get(0).add(new ArrayList<String>());
		childsList.get(0).get(3).add("Orçamentos :  " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/01/%' and TPVenda like '%Orçamento%';"));
		childsList.get(0).add(new ArrayList<String>());
		childsList.get(0).get(4).add("Pedidos Transmitidos : " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/01/%' and Status like '%T%';"));
		childsList.get(0).add(new ArrayList<String>());
		childsList.get(0).get(5).add("Pedidos Aguardando Transmissão : " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/01/%' and Status like '%P%' and TPVenda <> 'Orçamento';"));
		childsList.get(0).add(new ArrayList<String>());
		childsList.get(0).get(6).add("Total Vendas Mês :  "+ bd.buscaSumToTal("FV_ITEM", "FV_Item.numped in (select NUMPED from fv_pedido where dataVenda like '%/01/%' and TPVenda = 'Venda Normal' )"));

		//  fevereiro
		childsList.add(new ArrayList<ArrayList<String>>());
		childsList.get(1).add(new ArrayList<String>());
		childsList.get(1).get(0).add("Quantidades de Pedidos :" +bd.buscaCount("FV_PEDIDO","dataVenda like '%/02/%';"));
		childsList.get(1).add(new ArrayList<String>());
		childsList.get(1).get(1).add("Pedidos Venda Normal:  " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/02/%' and TPVenda like '%Venda Normal%';"));
		childsList.get(1).add(new ArrayList<String>());
		childsList.get(1).get(2).add("Pedidos Bonificados : "+ bd.buscaCount("FV_PEDIDO","dataVenda like '%/02/%' and TPVenda like '%Bonific%';"));
		childsList.get(1).add(new ArrayList<String>());
		childsList.get(1).get(3).add("Orçamentos : " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/02/%' and TPVenda like '%Orçamento%';"));
		childsList.get(1).add(new ArrayList<String>());
		childsList.get(1).get(4).add("Pedidos Transmitidos : :  " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/02/%' and Status like '%T%';"));
		childsList.get(1).add(new ArrayList<String>());
		childsList.get(1).get(5).add("Pedidos Aguardando Transmissão : " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/02/%' and Status like '%P%' and TPVenda <> 'Orçamento';"));
		childsList.get(1).add(new ArrayList<String>());
		childsList.get(1).get(6).add("Total Vendas Mês : " + bd.buscaSumToTal("FV_ITEM", "FV_Item.numped in (select NUMPED from fv_pedido where dataVenda like '%/02/%' and TPVenda = 'Venda Normal' )"));

		//  março
		childsList.add(new ArrayList<ArrayList<String>>());
		childsList.get(2).add(new ArrayList<String>());
		childsList.get(2).get(0).add("Quantidades de Pedidos :"+bd.buscaCount("FV_PEDIDO","dataVenda like '%/03/%';"));
		childsList.get(2).add(new ArrayList<String>());
		childsList.get(2).get(1).add("Pedidos Venda Normal:  " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/03/%' and TPVenda like '%Venda Normal%';"));
		childsList.get(2).add(new ArrayList<String>());
		childsList.get(2).get(2).add("Pedidos Bonificados :  "+ bd.buscaCount("FV_PEDIDO","dataVenda like '%/03/%' and TPVenda like '%Bonific%';"));
		childsList.get(2).add(new ArrayList<String>());
		childsList.get(2).get(3).add("Orçamentos :   " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/03/%' and TPVenda like '%Orçamento%';"));
		childsList.get(2).add(new ArrayList<String>());
		childsList.get(2).get(4).add("Pedidos Transmitidos :  " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/03/%' and Status like '%T%';"));
		childsList.get(2).add(new ArrayList<String>());
		childsList.get(2).get(5).add("Pedidos Aguardando Transmissão : " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/03/%' and Status like '%P%' and TPVenda <> 'Orçamento';"));
		childsList.get(2).add(new ArrayList<String>());
		childsList.get(2).get(6).add("Total Vendas Mês :  " + bd.buscaSumToTal("FV_ITEM", "FV_Item.numped in (select NUMPED from fv_pedido where dataVenda like '%/03/%' and TPVenda = 'Venda Normal' )"));
		
		
		//  abril
		childsList.add(new ArrayList<ArrayList<String>>());
		childsList.get(3).add(new ArrayList<String>());
		childsList.get(3).get(0).add("Quantidades de Pedidos : " +bd.buscaCount("FV_PEDIDO","dataVenda like '%/04/%';"));
		childsList.get(3).add(new ArrayList<String>());
		childsList.get(3).get(1).add("Pedidos Venda Normal:  " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/04/%' and TPVenda like '%Venda Normal%';"));
		childsList.get(3).add(new ArrayList<String>());
		childsList.get(3).get(2).add("Pedidos Bonificados :  "+ bd.buscaCount("FV_PEDIDO","dataVenda like '%/04/%' and TPVenda like '%Bonific%';"));
		childsList.get(3).add(new ArrayList<String>());
		childsList.get(3).get(3).add("Orçamentos :  " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/04/%' and TPVenda like '%Orçamento%';"));
		childsList.get(3).add(new ArrayList<String>());
		childsList.get(3).get(4).add("Pedidos Transmitidos :  " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/04/%' and Status like '%T%';"));
		childsList.get(3).add(new ArrayList<String>());
		childsList.get(3).get(5).add("Pedidos Aguardando Transmissão :   " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/04/%' and Status like '%P%' and TPVenda <> 'Orçamento';"));
		childsList.get(3).add(new ArrayList<String>());
		childsList.get(3).get(6).add("Total Vendas Mês : " + bd.buscaSumToTal("FV_ITEM", "FV_Item.numped in (select NUMPED from fv_pedido where dataVenda like '%/04/%' and TPVenda = 'Venda Normal' )"));
		
		//  maio
		childsList.add(new ArrayList<ArrayList<String>>());
		childsList.get(4).add(new ArrayList<String>());
		childsList.get(4).get(0).add("Quantidades de Pedidos : " +bd.buscaCount("FV_PEDIDO","dataVenda like '%/05/%';"));
		childsList.get(4).add(new ArrayList<String>());
		childsList.get(4).get(1).add("Pedidos Venda Normal:    " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/05/%' and TPVenda like '%Venda Normal%';"));
		childsList.get(4).add(new ArrayList<String>());
		childsList.get(4).get(2).add("Pedidos Bonificados :  "+ bd.buscaCount("FV_PEDIDO","dataVenda like '%/05/%' and TPVenda like '%Bonific%';"));
		childsList.get(4).add(new ArrayList<String>());
		childsList.get(4).get(3).add("Orçamentos :  " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/05/%' and TPVenda like '%Orçamento%';"));
		childsList.get(4).add(new ArrayList<String>());
		childsList.get(4).get(4).add("Pedidos Transmitidos : " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/05/%' and Status like '%T%';"));
		childsList.get(4).add(new ArrayList<String>());
		childsList.get(4).get(5).add("Pedidos Aguardando Transmissão :  " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/05/%' and Status like '%P%' and TPVenda <> 'Orçamento';"));
		childsList.get(4).add(new ArrayList<String>());
		childsList.get(4).get(6).add("Total Vendas Mês :  "+ bd.buscaSumToTal("FV_ITEM", "FV_Item.numped in (select NUMPED from fv_pedido where dataVenda like '%/05/%' and TPVenda = 'Venda Normal' )"));
		
		
		//  junho
		childsList.add(new ArrayList<ArrayList<String>>());
		childsList.get(5).add(new ArrayList<String>());
		childsList.get(5).get(0).add("Quantidades de Pedidos :           "+bd.buscaCount("FV_PEDIDO","dataVenda like '%/06/%';"));
		childsList.get(5).add(new ArrayList<String>());
		childsList.get(5).get(1).add("Pedidos Venda Normal:     "+ bd.buscaCount("FV_PEDIDO","dataVenda like '%/06/%' and TPVenda like '%Venda Normal%';"));
		childsList.get(5).add(new ArrayList<String>());
		childsList.get(5).get(2).add("Pedidos Bonificados :  "+ bd.buscaCount("FV_PEDIDO","dataVenda like '%/06/%' and TPVenda like '%Bonific%';"));
		childsList.get(5).add(new ArrayList<String>());
		childsList.get(5).get(3).add("Orçamentos :  " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/06/%' and TPVenda like '%Orçamento%';"));
		childsList.get(5).add(new ArrayList<String>());
		childsList.get(5).get(4).add("Pedidos Transmitidos :  "+ bd.buscaCount("FV_PEDIDO","dataVenda like '%/06/%' and Status like '%T%';"));
		childsList.get(5).add(new ArrayList<String>());
		childsList.get(5).get(5).add("Pedidos Aguardando Transmissão :  " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/06/%' and Status like '%P%' and TPVenda <> 'Orçamento';"));
		childsList.get(5).add(new ArrayList<String>());
		childsList.get(5).get(6).add("Total Vendas Mês :  "+ bd.buscaSumToTal("FV_ITEM", "FV_Item.numped in (select NUMPED from fv_pedido where dataVenda like '%/06/%' and TPVenda = 'Venda Normal' )"));
		
		//  julho
		childsList.add(new ArrayList<ArrayList<String>>());
		childsList.get(6).add(new ArrayList<String>());
		childsList.get(6).get(0).add("Quantidades de Pedidos :"+bd.buscaCount("FV_PEDIDO","dataVenda like '%/07/%';"));
		childsList.get(6).add(new ArrayList<String>());
		childsList.get(6).get(1).add("Pedidos Venda Normal:    "+ bd.buscaCount("FV_PEDIDO","dataVenda like '%/07/%' and TPVenda like '%Venda Normal%';"));
		childsList.get(6).add(new ArrayList<String>());
		childsList.get(6).get(2).add("Pedidos Bonificados :     "+ bd.buscaCount("FV_PEDIDO","dataVenda like '%/07/%' and TPVenda like '%Bonific%';"));
		childsList.get(6).add(new ArrayList<String>());
		childsList.get(6).get(3).add("Orçamentos :  " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/07/%' and TPVenda like '%Orçamento%';"));
		childsList.get(6).add(new ArrayList<String>());
		childsList.get(6).get(4).add("Pedidos Transmitidos : " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/08/%' and Status like '%T%';"));
		childsList.get(6).add(new ArrayList<String>());
		childsList.get(6).get(5).add("Pedidos Aguardando Transmissão :  " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/08/%' and Status like '%P%' and TPVenda <> 'Orçamento';"));
		childsList.get(6).add(new ArrayList<String>());
		childsList.get(6).get(6).add("Total Vendas Mês :  " + bd.buscaSumToTal("FV_ITEM", "FV_Item.numped in (select NUMPED from fv_pedido where dataVenda like '%/07/%' and TPVenda = 'Venda Normal' )"));
		
		
		//  agosto
		childsList.add(new ArrayList<ArrayList<String>>());
		childsList.get(7).add(new ArrayList<String>());
		childsList.get(7).get(0).add("Quantidades de Pedidos :"+bd.buscaCount("FV_PEDIDO","dataVenda like '%/08/%';"));
		childsList.get(7).add(new ArrayList<String>());
		childsList.get(7).get(1).add("Pedidos Venda Normal:  "+ bd.buscaCount("FV_PEDIDO","dataVenda like '%/08/%' and TPVenda like '%Venda Normal%';"));
		childsList.get(7).add(new ArrayList<String>());
		childsList.get(7).get(2).add("Pedidos Bonificados :  "+ bd.buscaCount("FV_PEDIDO","dataVenda like '%/08/%' and TPVenda like '%Bonific%';"));
		childsList.get(7).add(new ArrayList<String>());
		childsList.get(7).get(3).add("Orçamentos :  " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/08/%' and TPVenda like '%Orçamento%';"));
		childsList.get(7).add(new ArrayList<String>());
		childsList.get(7).get(4).add("Pedidos Transmitidos :  "+ bd.buscaCount("FV_PEDIDO","dataVenda like '%/08/%' and Status like '%T%';"));
		childsList.get(7).add(new ArrayList<String>());
		childsList.get(7).get(5).add("Pedidos Aguardando Transmissão :  "+ bd.buscaCount("FV_PEDIDO","dataVenda like '%/09/%' and Status like '%P%' and TPVenda <> 'Orçamento';"));
		childsList.get(7).add(new ArrayList<String>());
		childsList.get(7).get(6).add("Total Vendas Mês :  " + bd.buscaSumToTal("FV_ITEM", "FV_Item.numped in (select NUMPED from fv_pedido where dataVenda like '%/08/%' and TPVenda = 'Venda Normal' )"));
		
	//  setembro
			childsList.add(new ArrayList<ArrayList<String>>());
			childsList.get(8).add(new ArrayList<String>());
			childsList.get(8).get(0).add("Quantidades de Pedidos :"+bd.buscaCount("FV_PEDIDO","dataVenda like '%/09/%';"));
			childsList.get(8).add(new ArrayList<String>());
			childsList.get(8).get(1).add("Pedidos Venda Normal:   " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/09/%' and TPVenda like '%Venda Normal%';"));
			childsList.get(8).add(new ArrayList<String>());
			childsList.get(8).get(2).add("Pedidos Bonificados :  "+ bd.buscaCount("FV_PEDIDO","dataVenda like '%/09/%' and TPVenda like '%Bonific%';"));
			childsList.get(8).add(new ArrayList<String>());
			childsList.get(8).get(3).add("Orçamentos : " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/09/%' and TPVenda like '%Orçamento%';"));
			childsList.get(8).add(new ArrayList<String>());
			childsList.get(8).get(4).add("Pedidos Transmitidos :  " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/09/%' and Status like '%T%';"));
			childsList.get(8).add(new ArrayList<String>());
			childsList.get(8).get(5).add("Pedidos Aguardando Transmissão :  " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/09/%' and Status like '%P%' and TPVenda <> 'Orçamento';"));
			childsList.get(8).add(new ArrayList<String>());
			childsList.get(8).get(6).add("Total Vendas Mês :  " + bd.buscaSumToTal("FV_ITEM", "FV_Item.numped in (select NUMPED from fv_pedido where dataVenda like '%/09/%' and TPVenda = 'Venda Normal' )"));
			
		// outubro
			childsList.add(new ArrayList<ArrayList<String>>());
			childsList.get(9).add(new ArrayList<String>());
			childsList.get(9).get(0).add("Quantidades de Pedidos :"+bd.buscaCount("FV_PEDIDO","dataVenda like '%/10/%';"));
			childsList.get(9).add(new ArrayList<String>());
			childsList.get(9).get(1).add("Pedidos Venda Normal:   " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/10/%' and TPVenda like '%Venda Normal%';"));
			childsList.get(9).add(new ArrayList<String>());
			childsList.get(9).get(2).add("Pedidos Bonificados :  "+ bd.buscaCount("FV_PEDIDO","dataVenda like '%/10/%' and TPVenda like '%Bonific%';"));
			childsList.get(9).add(new ArrayList<String>());
			childsList.get(9).get(3).add("Orçamentos : "+ bd.buscaCount("FV_PEDIDO","dataVenda like '%/11/%' and TPVenda like '%Orçamento%';"));
			childsList.get(9).add(new ArrayList<String>());
			childsList.get(9).get(4).add("Pedidos Transmitidos :  " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/10/%' and Status like '%T%';"));
			childsList.get(9).add(new ArrayList<String>());
			childsList.get(9).get(5).add("Pedidos Aguardando Transmissão :  " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/10/%' and Status like '%P%' and TPVenda <> 'Orçamento';"));
			childsList.get(9).add(new ArrayList<String>());
			childsList.get(9).get(6).add("Total Vendas Mês : "+ bd.buscaSumToTal("FV_ITEM", "FV_Item.numped in (select NUMPED from fv_pedido where dataVenda like '%/10/%' and TPVenda = 'Venda Normal' )"));
			
			// novembro
			childsList.add(new ArrayList<ArrayList<String>>());
			childsList.get(10).add(new ArrayList<String>());
			childsList.get(10).get(0).add("Quantidades de Pedidos :" +bd.buscaCount("FV_PEDIDO","dataVenda like '%/11/%';"));
			childsList.get(10).add(new ArrayList<String>());
			childsList.get(10).get(1).add("Pedidos Venda Normal:   " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/11/%' and TPVenda like '%Venda Normal%';"));
			childsList.get(10).add(new ArrayList<String>());
			childsList.get(10).get(2).add("Pedidos Bonificados : "+ bd.buscaCount("FV_PEDIDO","dataVenda like '%/11/%' and TPVenda like '%Bonific%';"));
			childsList.get(10).add(new ArrayList<String>());
			childsList.get(10).get(3).add("Orçamentos :  " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/11/%' and TPVenda like '%Orçamento%';"));
			childsList.get(10).add(new ArrayList<String>());
			childsList.get(10).get(4).add("Pedidos Transmitidos :  " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/11/%' and Status like '%T%';"));
			childsList.get(10).add(new ArrayList<String>());
			childsList.get(10).get(5).add("Pedidos Aguardando Transmissão :  " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/11/%' and Status like '%P%' and TPVenda <> 'Orçamento';"));
			childsList.get(10).add(new ArrayList<String>());
			childsList.get(10).get(6).add("Total Vendas Mês :  " + bd.buscaSumToTal("FV_ITEM", "FV_Item.numped in (select NUMPED from fv_pedido where dataVenda like '%/11/%' and TPVenda = 'Venda Normal' )"));
			
			// dezembro
			childsList.add(new ArrayList<ArrayList<String>>());
			childsList.get(11).add(new ArrayList<String>());
			childsList.get(11).get(0).add("Quantidades de Pedidos :"+bd.buscaCount("FV_PEDIDO","dataVenda like '%/12/%';"));
			childsList.get(11).add(new ArrayList<String>());
			childsList.get(11).get(1).add("Pedidos Venda Normal:  "+ bd.buscaCount("FV_PEDIDO","dataVenda like '%/12/%' and TPVenda like '%Venda Normal%';"));
			childsList.get(11).add(new ArrayList<String>());
			childsList.get(11).get(2).add("Pedidos Bonificados : "+ bd.buscaCount("FV_PEDIDO","dataVenda like '%/12/%' and TPVenda like '%Bonific%';"));
			childsList.get(11).add(new ArrayList<String>());
			childsList.get(11).get(3).add("Orçamentos :  "+ bd.buscaCount("FV_PEDIDO","dataVenda like '%/12/%' and TPVenda like '%Orçamentos%';"));
			childsList.get(11).add(new ArrayList<String>());
			childsList.get(11).get(4).add("Pedidos Transmitidos :  " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/12/%' and Status like '%T%';"));
			childsList.get(11).add(new ArrayList<String>());
			childsList.get(11).get(5).add("Pedidos Aguardando Transmissão :  " + bd.buscaCount("FV_PEDIDO","dataVenda like '%/12/%' and Status like '%P%' and TPVenda <> 'Orçamento';"));
			childsList.get(11).add(new ArrayList<String>());
			childsList.get(11).get(6).add("Total Vendas Mês :  " + bd.buscaSumToTal("FV_ITEM", "FV_Item.numped in (select NUMPED from fv_pedido where dataVenda like '%/12/%' and TPVenda = 'Venda Normal' )"));
			
		return childsList;
	}

}
