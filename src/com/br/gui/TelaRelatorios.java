package com.br.gui;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.br.kelma.R;
import com.br.kelma.R.color;

public class TelaRelatorios extends Activity {

	ExpandableListView expLView;
	TextView childtxt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_relatorio);
		expLView = (ExpandableListView) findViewById(R.id.ExpandableListViewid);
  	
		ReadData r = new ReadData(this.getApplicationContext());
		
		myExpandableAdapter adapter = new myExpandableAdapter(this,
				r.getGroupData(), r.getChildData());
		expLView.setAdapter(adapter);
	}

	public class myExpandableAdapter extends BaseExpandableListAdapter {

		private ArrayList<String> groups;

		private ArrayList<ArrayList<ArrayList<String>>> children;

		private Context context;

		public myExpandableAdapter(Context context, ArrayList<String> groups,
				ArrayList<ArrayList<ArrayList<String>>> children) {
			this.context = context;
			this.groups = groups;
			this.children = children;
		}

		@Override
		public boolean areAllItemsEnabled() {
			return true;
		}

		public ArrayList<String> getChild(int groupPosition, int childPosition) {
			return children.get(groupPosition).get(childPosition);
		}

	
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}


		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {

			final String child = (String) ((ArrayList<String>) getChild(
					groupPosition, childPosition)).get(0);

			if (convertView == null) {
				LayoutInflater infalInflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(
						R.layout.expandablelv_childview, null);
			}
			
			
		

			childtxt = (TextView) convertView.findViewById(R.id.childTXT);
			
			if (child.contains("Pedidos Transmitidos")){
			   ((TextView) convertView.findViewById(R.id.childTXT)).setBackgroundColor(Color.GREEN);
			}
			else if (child.contains("Aguardando Transmissão")){
				   ((TextView) convertView.findViewById(R.id.childTXT)).setBackgroundColor(Color.RED);
			}
			else if (child.contains("Total Vendas Mês :")){
				   ((TextView) convertView.findViewById(R.id.childTXT)).setBackgroundColor(Color.GREEN);
			}
			else if (child.contains("Orçamentos")){
				   ((TextView) convertView.findViewById(R.id.childTXT)).setBackgroundColor(Color.WHITE);
				   
			}else if (child.contains("Quantidades de Pedidos")){
				  ((TextView) convertView.findViewById(R.id.childTXT)).setBackgroundColor(Color.GREEN);
			}
			else if (child.contains("Pedidos Venda Normal")){
				  ((TextView) convertView.findViewById(R.id.childTXT)).setBackgroundColor(Color.YELLOW);
			}
			else if (child.contains("Pedidos Bonificados")){
				  ((TextView) convertView.findViewById(R.id.childTXT)).setBackgroundColor(Color.GREEN);
			}
			
			childtxt.setText(child);
			

			
		    
			
			
			
			childtxt.setOnClickListener(new OnClickListener() {

		
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(),
							"" + child, Toast.LENGTH_LONG).show();
				}
			});

			return convertView;
		}

	
		public int getChildrenCount(int groupPosition) {
			return children.get(groupPosition).size();
		}

	
		public String getGroup(int groupPosition) {
			return groups.get(groupPosition);
		}

	
		public int getGroupCount() {
			return groups.size();
		}

	
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

	
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {

			String group = (String) getGroup(groupPosition);

			if (convertView == null) {
				LayoutInflater infalInflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(
						R.layout.expandablelv_groupview, null);
			}

			TextView grouptxt = (TextView) convertView
					.findViewById(R.id.groupTXT);

			grouptxt.setText(group);

			return convertView;
		}

	
		public boolean hasStableIds() {
			return true;
		}

	
		public boolean isChildSelectable(int arg0, int arg1) {
			return true;
		}

	}

}