package com.sehmon.whatamieating;


import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class NutrientFragment extends Fragment {

	//Getting the provider for the data
	//This class is basically the same as the other fragment
	//TODO create a parent fragment for these two, so we can just extend
	NutrientProvider provider;
	public ArrayList<Nutrient> nutrients;
	NutrientListAdapter adapter;
	ListView lv1;
	
	public static Fragment newInstance() {
		return new NutrientFragment();
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.fragment_nutrient_list, null);
		adapter = new NutrientListAdapter(getActivity(), R.id.list_item, (ArrayList<Nutrient>) provider.getNutrients());
		lv1 = (ListView)rootView.findViewById(R.id.nutrientListView);
		lv1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(getActivity(), DetailActivity.class);
				i.putExtra("name", adapter.getItem(arg2).getName());
				startActivity(i);	
			}	
		
		});
		
		return rootView;
	}
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		provider = (NutrientProvider)activity;
		
		
	}
	
	@Override
	public void onResume(){
		super.onResume();	
		adapter = new NutrientListAdapter(getActivity(), R.id.list_item,  (ArrayList<Nutrient>) provider.getNutrients());
		lv1.setAdapter(adapter);
		
		
		
	}
	
}
