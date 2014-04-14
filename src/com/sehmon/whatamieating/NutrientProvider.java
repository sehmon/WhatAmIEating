package com.sehmon.whatamieating;

import java.util.List;

//Interface that allows easy access to the data of the tab-swipe activity
public interface NutrientProvider {

	public List<Nutrient> getNutrients();
	
	public List<Additive> getAdditives();
	
	public Food getFood();
}
