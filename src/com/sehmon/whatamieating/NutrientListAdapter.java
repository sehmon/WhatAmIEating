package com.sehmon.whatamieating;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class NutrientListAdapter extends ArrayAdapter<Nutrient> {
	Context context;

	public NutrientListAdapter(Context context, int convertView, ArrayList<Nutrient> objects) {
		super(context, convertView, objects);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		
		Nutrient nutrient = getItem(position);
		
		
		if(convertView == null){
		LayoutInflater inflater = LayoutInflater.from(getContext());
		convertView = inflater.inflate(R.layout.item_list, null);
		}
		
		TextView name = (TextView)convertView.findViewById(R.id.nutrientTextView);
		TextView amount = (TextView)convertView.findViewById(R.id.amountTextView);
		
		name.setText(nutrient.getName());
		amount.setText(String.valueOf(nutrient.getValue()));
		
		return convertView;
	}



}
