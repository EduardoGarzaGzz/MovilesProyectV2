package com.fcfm.movilesproyect.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.activitys.MainActivity;

import java.util.Date;

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
