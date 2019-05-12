package com.fcfm.movilesproyect.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fcfm.movilesproyect.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashbordFragment extends Fragment {
	
	public DashbordFragment( ) {
	}
	
	
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container,
							  Bundle savedInstanceState ) {
		// Inflate the layout for this fragment
		View tmp_view = inflater.inflate( R.layout.fragment_dashbord, container, false );
		
		return tmp_view;
	}
	
	@Override public void onStart( ) {
		super.onStart( );
	}
	
}
