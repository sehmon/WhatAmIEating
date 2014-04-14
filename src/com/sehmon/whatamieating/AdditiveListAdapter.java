package com.sehmon.whatamieating;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

//TODO Create a parent class for this adapter because it's the same as the nutrient one
public class AdditiveListAdapter extends ArrayAdapter<Additive> {
	Context context;

	//Typical constructor
	public AdditiveListAdapter(Context context, int convertView, ArrayList<Additive> objects) {
		super(context, convertView, objects);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		
		Additive additive = getItem(position);
		
		if(convertView == null){
		LayoutInflater inflater = LayoutInflater.from(getContext());
		convertView = inflater.inflate(R.layout.item_list, null);
		}
		
		//Sets TextView Fields to what you need them to be
		TextView name = (TextView)convertView.findViewById(R.id.nutrientTextView);
		TextView desc = (TextView)convertView.findViewById(R.id.amountTextView);
		name.setText(additive.getName());
		desc.setText(additive.getAdditives());
		
		
		return convertView;
	}



}
