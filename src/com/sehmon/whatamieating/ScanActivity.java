package com.sehmon.whatamieating;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanActivity extends Activity {
	
	ImageButton b1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan);
		
		//Arrow button to start scan
		b1 = (ImageButton)findViewById(R.id.scanButton);
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				IntentIntegrator scanIntegrator = new IntentIntegrator(ScanActivity.this);
				scanIntegrator.initiateScan();
				
			}
		});
				
		
	}
	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		//retrieve scan result
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		
		if (scanningResult != null) {
			//we have a result
			String scanContent = scanningResult.getContents();
			String scanFormat = scanningResult.getFormatName();
			
			Log.i("Scanner", "Barcode: " + scanContent);
			Log.i("Scanner", "Scan Format: " + scanFormat);
			
			Intent i = new Intent(this, SingleFoodActivity.class);
			i.putExtra("upcCode", scanContent);
			startActivity(i);
			
			
			} else{
			    Toast toast = Toast.makeText(getApplicationContext(), 
			            "No scan data received!", Toast.LENGTH_SHORT);
			        toast.show();
			    }
		}

}
