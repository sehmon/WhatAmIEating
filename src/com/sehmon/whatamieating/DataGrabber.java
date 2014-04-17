package com.sehmon.whatamieating;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class DataGrabber extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data_grabber);
		getFoodInfo();
		}
	
	//This is the method that gives you the food data using Async
	//Still don't understand it 100%
	public void getFoodInfo(){
		final ProgressDialog dialog = new ProgressDialog(DataGrabber.this);  
		AsyncTask<String, String, String> async = new AsyncTask<String, String, String>() {
			

			@Override
			protected String doInBackground(String... arg0) {
				//This grabs the Intent from the Scan and gets the upc code of the food
				Intent i = getIntent();
				String s = i.getStringExtra("upcCode");
				HttpClient client = new DefaultHttpClient();
				
				//This is the url that you create to make the Get Request
				HttpGet getReq = new HttpGet("http://api.foodessentials.com/productscore?u=" 
				+ s + "&sid=b318d6d9-3858-432e-81bf-fe037cc313ae&f=json&api_key=m5pkqfejmtvxsw3en5rnjagu");
				
				String responseString = "";
				
				//I still don't know what this does exactly:

				try {
					//Here you set the response of the client to resp
					//Then you use a BufferedReader to parse the response
					//After, you use the while loop to add each line of the response to a new string
					//Finally you return the response to whatever asked for it
					HttpResponse resp = client.execute(getReq);
					BufferedReader reader = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
					// read the response
					String temp = "";
					while ((temp = reader.readLine()) != null) {
						responseString += temp;
					}

					return responseString;
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return responseString;
			}
			
			@Override
			protected void onPreExecute(){
			   dialog.show();
			}
			
			
			//This is a default method of the Async Class that you have to override
			@Override
			protected void onPostExecute(String res) {
				
				//Because it is Async, you have to do all your variable setting here
				try {
					//Use the entire response and make a JSON Object out of it
					JSONObject jsonObject = new JSONObject(res);
					Food f = Food.fromJson(jsonObject.getJSONObject("product"));	
					JSONArray jsonAdditives = jsonObject.getJSONObject("product").getJSONArray("additives");
					JSONArray jsonNutrients = jsonObject.getJSONObject("product").getJSONArray("nutrients");
					ArrayList<Nutrient> nutrients = new ArrayList<Nutrient>();
					ArrayList<Additive> additives = new ArrayList<Additive>();
					for(int i = 0; i < jsonNutrients.length(); i++){
						nutrients.add(Nutrient.fromJson(jsonNutrients.getJSONObject(i)));
					}
					for(int i = 0; i < jsonAdditives.length(); i++){
						additives.add(Additive.fromJson(jsonAdditives.getJSONObject(i)));
					}
					
					Log.i("test", additives.toString());
					Intent i = new Intent(getApplicationContext(), SingleFoodActivity.class);
					i.putExtra("food", f);
					i.putExtra("nutrients", nutrients);
					i.putExtra("additives", additives);

					startActivity(i);
					
				} catch (JSONException e) {
					Log.w("Test", "Food Not Found");
					
				}

				finish();
				
			}
			
		};
		
		//Because all of that was an anonymous class, the method really is just this part right here
		//I think the execute method doesn't need parameters for this one, so we just set the String to blah
		async.execute("blah");
	}

}
