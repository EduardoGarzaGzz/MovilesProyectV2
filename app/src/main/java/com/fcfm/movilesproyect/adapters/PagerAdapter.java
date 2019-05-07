package com.fcfm.movilesproyect.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.fcfm.movilesproyect.fragments.ConfigureFragment;
import com.fcfm.movilesproyect.fragments.DashbordFragment;
import com.fcfm.movilesproyect.fragments.InformativeMessagesFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
	
	private int number_tabs;
	
	public PagerAdapter( FragmentManager fm, int tabs ) {
		super( fm );
		
		this.number_tabs = tabs;
	}
	
	@Override public Fragment getItem( int i ) {
		
		switch ( i ) {
			case 0:
				return new DashbordFragment( );
			case 1:
				return new InformativeMessagesFragment( );
			case 2:
				return new ConfigureFragment( );
		}
		return new DashbordFragment( );
	}
	
	@Override public int getCount( ) {
		return this.number_tabs;
	}
}
