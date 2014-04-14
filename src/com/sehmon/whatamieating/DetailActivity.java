package com.sehmon.whatamieating;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends Activity {
	
	TextView view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		view = (TextView) findViewById(R.id.textView2);
		//Gets the item you clicked on and brings back the title
		//TODO Find a way to get information about the item and assign it here
		String title = getIntent().getStringExtra("name");
		view.setText(title);
		
	}

}
