package com.fcfm.movilesproyect.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.presenter.interfaces.IDashbordMVP;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfigureFragment extends Fragment implements IDashbordMVP.View.Configure {
	
	private IDashbordMVP.Presenter presenter;
	
	private Button btn_my_perfil;
	private Button btn_close_session;
	
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
		
		return tmp_view;
	}
	
	@Override
	public void onStart( ) {
		super.onStart( );
		
		this.btn_my_perfil.setOnClickListener( this.presenter.clickMyPerfil( ) );
		this.btn_close_session.setOnClickListener( this.presenter.clickCloseSession( ) );
		
	}
	
	@Override
	public void setPresenter( IDashbordMVP.Presenter presenter ) {
		this.presenter = presenter;
	}
}
