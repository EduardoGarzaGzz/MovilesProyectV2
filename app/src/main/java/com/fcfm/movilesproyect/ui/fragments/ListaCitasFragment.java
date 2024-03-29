package com.fcfm.movilesproyect.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.presenter.interfaces.ICitaDashboardMVP;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaCitasFragment extends Fragment implements ICitaDashboardMVP.View.ListCita {
	
	private ICitaDashboardMVP.Presenter presenter;
	private Button btn_agregar;
	
	public ListaCitasFragment( ) {
	}
	
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container,
	                          Bundle savedInstanceState ) {
		// Inflate the layout for this fragment
		View v = inflater.inflate( R.layout.fragment_lista_citas, container, false );
		
		this.btn_agregar = v.findViewById( R.id.btn_frag_lista_citas_agregar );
		
		this.presenter.initListaCita( );
		
		return v;
	}
	
	@Override
	public void onStart( ) {
		super.onStart( );
		
		this.btn_agregar.setOnClickListener( this.presenter.clickAgregarCita( ) );
	}
	
	@Override
	public ListaCitasFragment getFragment( ) {
		return this;
	}
	
	@Override
	public void setPresenter( ICitaDashboardMVP.Presenter presenter ) {
		this.presenter = presenter;
	}
}
