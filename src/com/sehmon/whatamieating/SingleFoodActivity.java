package com.sehmon.whatamieating;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class SingleFoodActivity extends Activity implements
		ActionBar.TabListener, NutrientProvider {
	
	//These three are used for the provider
	Food f;
	ArrayList<Nutrient> nutrients;
	ArrayList<Additive> additives;
	
	//This page adapter is what allows for multiple fragments in one activity
	//If your project is too memory intensive, then you should use a PagerAdapter
	SectionsPagerAdapter mSectionsPagerAdapter;

	//This pager hosts the section contents
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_food);
		f = (Food) getIntent().getSerializableExtra("food");
		nutrients = (ArrayList<Nutrient>) getIntent().getSerializableExtra("nutrients");
		additives = (ArrayList<Additive>) getIntent().getSerializableExtra("additives");
		
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
		
		//When the pager opens, it starts in the middle
		mViewPager.setCurrentItem(1);
		
		actionBar.setTitle(f.getBrand());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.single_food, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}
		
		
		//This is where the fragments are returned on page swipe
		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.

			Fragment f = new Fragment();
			switch(position){
			case 0:
				f = NutrientFragment.newInstance();
				break;
			case 1:
				f = ScoreFragment.newInstance();
				break;
			case 2:
				f = AdditiveFragment.newInstance();
			} 
			
			return f;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	


	
	//These are the methods you need, because SingleFoodActivity extends the Nutrient Provier Class
	@Override
	public List<Nutrient> getNutrients() {
		if (nutrients == null){
			return new ArrayList<Nutrient>();
		}
		return nutrients;
	}
	
	public List<Additive> getAdditives(){
		if(additives == null){
			return new ArrayList<Additive>();
		}
		return additives;
	}

	@Override
	public Food getFood() {
		if(f== null){
			return new Food();
		}
		return f;
	}


}
