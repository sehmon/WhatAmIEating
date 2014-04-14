package com.sehmon.whatamieating;

import org.json.JSONException;
import org.json.JSONObject;

public class Nutrient {	
	
	private String name;
	private double value;
	
	//JSON Constructor, self-explanatory
	//TODO create a parent class for nutrient and additive so you can just extend the class
	public static Nutrient fromJson(JSONObject jsonObject){
		Nutrient n = new Nutrient();
		try {
			n.name = jsonObject.getString("nutrient_name");
			Double nutrientVal = 0.0;
			try {
				nutrientVal = Double.parseDouble(jsonObject.getString("nutrient_value"));
			} catch (NumberFormatException e) {
				nutrientVal = 0.0;
			}
			n.value = nutrientVal.doubleValue();
		} catch (JSONException e1) { }
		return n;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public double getValue() {
		return value;	
	}


}
