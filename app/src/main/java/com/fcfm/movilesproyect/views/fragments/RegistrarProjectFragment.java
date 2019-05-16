package com.fcfm.movilesproyect.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.interfaces.IProjectDashboard;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrarProjectFragment extends Fragment implements IProjectDashboard.View.Form {
	
	private IProjectDashboard.Presenter presenter;
	private boolean status;
	
	private EditText title;
	private EditText description;
	private Button   btn_accion;
	private Button   btn_cancelar;
	
	public RegistrarProjectFragment( ) { }
	
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container,
	                          Bundle savedInstanceState ) {
		View v = inflater.inflate( R.layout.fragment_registrar_project, container, false );
		
		this.title = ( EditText ) v.findViewById( R.id.et_frac_register_project_title );
		this.description = ( EditText ) v.findViewById( R.id.et_frac_register_project_description );
		this.btn_accion = ( Button ) v.findViewById( R.id.btn_frac_register_project_action );
		this.btn_cancelar = ( Button ) v.findViewById( R.id.btn_frac_register_project_cacelar );
		
		this.presenter.setViewForm( this );
		this.presenter.initViewForm( );
		return v;
	}
	
	@Override
	public void onStart( ) {
		super.onStart( );
		
		this.btn_accion.setOnClickListener( this.presenter.clickActionProyecto( ) );
		this.btn_cancelar.setOnClickListener( this.presenter.clickCacelarProyecto( ) );
	}
	
	@Override
	public void setStatus( boolean status ) {
		this.status = status;
	}
	
	@Override
	public boolean getStatus( ) {
		return this.status;
	}
	
	@Override
	public void setPresenter( IProjectDashboard.Presenter presenter ) {
		this.presenter = presenter;
	}
}
