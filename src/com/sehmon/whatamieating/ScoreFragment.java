package com.sehmon.whatamieating;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//This is the fragment in the center of the tabView
public class ScoreFragment extends Fragment {
	
	NutrientProvider provider;
	TextView tv1;

	public static Fragment newInstance() {
		return new ScoreFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_score,
				container, false);
		tv1 = (TextView)rootView.findViewById(R.id.scoreTextView);
		
		
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
		if(provider.getFood() == null){
			tv1.setText("0");
		} else {
			tv1.setText(provider.getFood().getScore());
		}
	}
	
	public ScoreFragment(){
		
	}
}
