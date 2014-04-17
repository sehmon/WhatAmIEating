package com.sehmon.whatamieating;

import java.io.Serializable;

import org.json.JSONObject;


public class Food implements Serializable {


	private static final long serialVersionUID = 278902244333249739L;
	private String upc;
	private String brand;

	private int score;
	

	public int getScore() {
		return score;
	}


	public String getUpc() {
		return upc;
	}

	public String getBrand() {
		return brand;
	}
	
	//JSON Constructor, self-explanitory
	public static Food fromJson(JSONObject jsonObject){
		
		Food f = new Food();
		try{
			
			f.brand = jsonObject.getString("brand");
			f.upc = jsonObject.getString("upc");
			f.score = Integer.parseInt(jsonObject.getString("productscore"));
						
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		return f;
	}

}
