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

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfigureFragment extends Fragment {
	
	private Button            btn_my_perfil;
	private Button            btn_close_session;
	private SharedPreferences prefs;
	
	public ConfigureFragment( ) {
		// Required empty public constructor
	}
	
	
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container,
							  Bundle savedInstanceState ) {
		// Inflate the layout for this fragment
		View tmp_view = inflater.inflate( R.layout.fragment_configure, container, false );
		
		this.btn_my_perfil = tmp_view.findViewById( R.id.btn_frag_configure_my_perfil );
		this.btn_close_session = tmp_view.findViewById( R.id.btn_frag_configure_close_session );
		this.prefs = this.getActivity( ).getSharedPreferences( "info_user", Context.MODE_PRIVATE );
		
		return tmp_view;
	}
	
	@Override public void onStart( ) {
		super.onStart( );
		
		this.btn_close_session.setOnClickListener( new View.OnClickListener( ) {
			@Override public void onClick( View v ) {
				setCloseSession( );
				
				startActivity( new Intent( getContext( ), MainActivity.class ) );
				getActivity( ).finish( );
			}
		} );
	}
	
	public void setCloseSession( ) {
		if ( this.prefs == null )
			return;
		
		SharedPreferences.Editor edit_prefs = prefs.edit( );
		
		edit_prefs.putString( "login_status", "false" );
		edit_prefs.putString( "login_fecha", "" );
		edit_prefs.putString( "id_user", "0" );
		edit_prefs.apply( );
		
		Log.w( "MY APP LOG", "Close session " );
		
	}
}
