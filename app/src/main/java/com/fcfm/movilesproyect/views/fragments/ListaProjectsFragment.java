package com.fcfm.movilesproyect.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.interfaces.IProjectDashboard;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaProjectsFragment extends Fragment implements IProjectDashboard.View.List {
	
	private IProjectDashboard.Presenter presenter;
	
	private TextView     no_hay;
	private EditText     serch;
	private RecyclerView lista;
	private Button       btn_agregar;
	
	public ListaProjectsFragment( ) { }
	
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container,
	                          Bundle savedInstanceState ) {
		// Inflate the layout for this fragment
		View v = inflater.inflate( R.layout.fragment_lista_projects, container, false );
		
		this.no_hay = ( TextView ) v.findViewById( R.id.tv_frac_lista_projects_no_hay );
		this.serch = ( EditText ) v.findViewById( R.id.ed_frac_lista_projects_serch );
		this.lista = ( RecyclerView ) v.findViewById( R.id.rv_frac_lista_projects_list );
		this.btn_agregar = ( Button ) v.findViewById( R.id.btn_frac_lista_projects_add );
		
		this.presenter.setViewList( this );
		this.presenter.initViewList( );
		return v;
	}
	
	@Override
	public void onStart( ) {
		super.onStart( );
		
		this.btn_agregar.setOnClickListener( this.presenter.clickAgregarProyecto( ) );
		
	}
	
	@Override
	public RecyclerView getLista( ) {
		return this.lista;
	}
	
	@Override
	public void setPresenter( IProjectDashboard.Presenter presenter ) {
		this.presenter = presenter;
	}
}
