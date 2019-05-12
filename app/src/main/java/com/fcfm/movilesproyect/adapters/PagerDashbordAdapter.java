package com.fcfm.movilesproyect.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.fcfm.movilesproyect.interfaces.IDashbordMVP;
import com.fcfm.movilesproyect.views.fragments.ConfigureFragment;
import com.fcfm.movilesproyect.views.fragments.DashbordFragment;
import com.fcfm.movilesproyect.views.fragments.InformativeMessagesFragment;

public class PagerDashbordAdapter extends FragmentStatePagerAdapter {
	
	private int                    number_tabs;
	private IDashbordMVP.Presenter presenter;
	
	public PagerDashbordAdapter( FragmentManager fm, int tabs, IDashbordMVP.Presenter presenter ) {
		super( fm );
		
		this.number_tabs = tabs;
		this.presenter = presenter;
	}
	
	@Override
	public Fragment getItem( int i ) {
		
		Fragment fragment = null;
		
		switch ( i ) {
			case 0:
				fragment = new DashbordFragment( );
				break;
			case 1:
				fragment = new InformativeMessagesFragment( );
				( ( InformativeMessagesFragment ) fragment ).setPresenter( this.presenter );
				break;
			case 2:
				fragment = new ConfigureFragment( );
				( ( ConfigureFragment ) fragment ).setPresenter( this.presenter );
				break;
		}

//		switch ( i ) {
//			case 0:
//				return new DashbordFragment( );
//			case 1:
//
//
//				return new InformativeMessagesFragment( );
//			case 2:
//				return new ConfigureFragment( );
//		}
		return fragment;
	}
	
	@Override
	public int getCount( ) {
		return this.number_tabs;
	}
}
