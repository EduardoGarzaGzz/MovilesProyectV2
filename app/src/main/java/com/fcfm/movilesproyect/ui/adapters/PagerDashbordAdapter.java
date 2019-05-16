package com.fcfm.movilesproyect.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.fcfm.movilesproyect.presenter.interfaces.IDashbordMVP;
import com.fcfm.movilesproyect.ui.fragments.ConfigureFragment;
import com.fcfm.movilesproyect.ui.fragments.DashbordFragment;
import com.fcfm.movilesproyect.ui.fragments.InformativeMessagesFragment;

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
				( ( DashbordFragment ) fragment ).setPresenter( this.presenter );
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
		
		return fragment;
	}
	
	@Override
	public int getCount( ) {
		return this.number_tabs;
	}
}
