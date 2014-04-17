package com.sehmon.whatamieating;

import java.io.Serializable;

import org.json.JSONObject;

import android.util.Log;

public class Additive implements Serializable {
	
	private static final long serialVersionUID = 1524835687757604994L;
	private String type;
	private String additives;
	
	//Creating a new food from a JSON Object
	public static Additive fromJson(JSONObject jsonObject){
		Additive a = new Additive();
		try{
			a.type = jsonObject.getString("additive_name");
			String total = (jsonObject.getString("additive_red_ingredients") + jsonObject.getString("additive_yellow_ingredients"));
			a.setAdditives(total);
		} catch (Exception e){
			Log.e("Error", "Additive not Added" + e.toString());
			}
		return a;
	}
	public void setName(String name) {
		this.type = name;
	}
	public String getAdditives() {
		return additives;
	}
	public void setAdditives(String additives) {
		this.additives = additives;
	}
	public String getName() {
		return type;
	}
	
	
}
