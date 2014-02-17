package com.br.gui;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;

import com.br.kelma.R;

public class PaginaListView extends ListActivity {
	   
    private int itensPorPagina = 20;
    private boolean carregandoMaisItens = false;
    private ArrayList<String> listaComItens = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private Calendar data = Calendar.getInstance();
   
    @Override
    public void onCreate(Bundle savedInstanceState) {       
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listplaceholder);

 //Rodapé mostrando o carregando de mais itens
        criaRodapeDeMaisItens();
        criaAdaptador();
       
        this.getListView().setOnScrollListener(new OnScrollListener(){
           
       
            public void onScrollStateChanged(AbsListView view, int scrollState) {}
           
       
            public void onScroll(AbsListView view, int primeiroItem,
                    int itensNaTela, int totalDeItens) {
               
                int ultimoItemNatela = primeiroItem + itensNaTela;             
 //Define quando se deve carregar novos itens
                if((ultimoItemNatela == totalDeItens) && !(carregandoMaisItens)){                  
                    iniciaNovaThread();
                }
            }

        });
       
        iniciaNovaThread();
   
    }


    private void iniciaNovaThread() {
        Thread thread =  new Thread(null, carregaNovosItensNaLista);
        thread.start();
    }
   
    private void criaAdaptador() {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaComItens);
        this.setListAdapter(adapter);
    }


    private void criaRodapeDeMaisItens() {
        View footerView = ((LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.listfooter, null, false);
        this.getListView().addFooterView(footerView);
    }
   
    private Runnable carregaNovosItensNaLista = new Runnable() {       
       
        public void run() {
            //seta flag de carregamento de produtos
            carregandoMaisItens = true;
           
            listaComItens = new ArrayList<String>();
           
            //Tempo de carregamento
            try { Thread.sleep(1000);
            } catch (InterruptedException e) {}
           
            for (int i = 0; i < itensPorPagina; i++) {     
                adicionaNovaData();
            }
           
            runOnUiThread(returnResultado);
           
        }

        private void adicionaNovaData() {
            listaComItens.add("Data: " + (data.get(Calendar.MONTH)+ 1) + "/" + data.get(Calendar.DATE) + "/" + data.get(Calendar.YEAR) );           
            data.add(Calendar.DATE, 1);
        }
    }; 
   

    private Runnable returnResultado = new Runnable() {
       
        public void run() {
           
            //Adiciona no adaptador os novos itens
            if(listaComItens != null && listaComItens.size() > 0){
                for(int i=0;i<listaComItens.size();i++)
                    adapter.add(listaComItens.get(i));
            }
           
            //Atualiza titulo do app
            setTitle("Lista com " + String.valueOf(adapter.getCount()) + " itens");
           
            //Atualiza lista
            adapter.notifyDataSetChanged();
           
            //Seta flag de carregamento de novos itens
            carregandoMaisItens = false;
        }
    };
}
 