package com.br.adaptadores;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
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

public class AdaptadorPedido extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<ItemListView> itens;
	private Logica logica = new Logica();
	private final List<String> selecionados = new ArrayList<String>();

	CheckBox chBox1;

	public AdaptadorPedido(Context context, ArrayList<ItemListView> itens) {
		// Itens que preencheram o listview
		this.itens = itens;
		// responsavel por pegar o Layout do item.
		mInflater = LayoutInflater.from(context);

	}

	/**
	 * Retorna a quantidade de itens
	 * 
	 * @return
	 */
	public int getCount() {
		return itens.size();
	}

	/**
	 * Retorna o item de acordo com a posicao dele na tela.
	 * 
	 * @param position
	 * @return
	 */
	public ItemListView getItem(int position) {
		return itens.get(position);
	}

	/**
	 * Sem implementação
	 * 
	 * @param position
	 * @return
	 */
	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup parent) {
		// Pega o item de acordo com a posção.
		ItemListView item = itens.get(position);
		// infla o layout para podermos preencher os dados
		view = mInflater.inflate(R.layout.item_listview, null);

		// atravez do layout pego pelo LayoutInflater, pegamos cada id
		// relacionado
		// ao item e definimos as informações.
		((TextView) view.findViewById(R.id.textViewNomeItem)).setText(item
				.getDescricao());
		((TextView) view.findViewById(R.id.textViewCodItem)).setText(String
				.valueOf(item.getCodigo()));
		((TextView) view.findViewById(R.id.textViewQtItem)).setText(String
				.valueOf(item.getQuantidade()));
		
		
		// formata antes de jogar na tela, limita casa decimal
		
		String valorUnitario = logica.limitaCasa(item.getValorUnitario());
		((TextView) view.findViewById(R.id.textViewPrUni)).setText(valorUnitario);
		
		
		
		String valorTotal = logica.limitaCasa(item.getValorTotal());
		((TextView) view.findViewById(R.id.textViewPrTotal)).setText(valorTotal);
		
		chBox1 = (CheckBox) view.findViewById(R.id.checkBoxExcluiItem);
		chBox1.setTag(itens);

		// adiciona o evento ao checkbox
		chBox1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				CheckBox chk = (CheckBox) v;
				String estado = (String) chk.getTag().toString();
				if (chk.isChecked()) {
					if (!selecionados.contains(estado))
						selecionados.add(estado);
					ItemListView item = itens.get(position);
					TelaVenda2.itensSelecionados.add(item);
				} else {
					if (selecionados.contains(estado))
						selecionados.remove(estado);
					TelaVenda2.itensSelecionados.clear();
				}
			

			}

		});
		if (selecionados.contains(itens)) {
			chBox1.setChecked(true);
		} else {
			chBox1.setChecked(false);
			TelaVenda2.itensSelecionados.clear();
		}

		return view;
	}

}
