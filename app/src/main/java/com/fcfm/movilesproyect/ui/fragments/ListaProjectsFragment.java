package com.fcfm.movilesproyect.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.ui.adapters.ListProjectsAdapter;
import com.fcfm.movilesproyect.presenter.interfaces.IProjectDashboard;
import com.fcfm.movilesproyect.db.models.Project;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaProjectsFragment extends Fragment implements IProjectDashboard.View.List {
	
	private IProjectDashboard.Presenter presenter;
	
	private RecyclerView               recycler_view;
	private ListProjectsAdapter        adapter;
	private RecyclerView.LayoutManager layout_manager;
	
	private TextView no_hay;
	private EditText serch;
	private Button   btn_agregar;
	
	public ListaProjectsFragment( ) { }
	
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container,
	                          Bundle savedInstanceState ) {
		// Inflate the layout for this fragment
		View v = inflater.inflate( R.layout.fragment_lista_projects, container, false );
		
		this.no_hay = ( TextView ) v.findViewById( R.id.tv_frac_lista_task_no_hay );
		this.serch = ( EditText ) v.findViewById( R.id.ed_frac_lista_task_serch );
		this.recycler_view = ( RecyclerView ) v.findViewById( R.id.rv_frac_lista_task_list );
		this.btn_agregar = ( Button ) v.findViewById( R.id.btn_frac_lista_task_add );
		
		this.layout_manager = new LinearLayoutManager( getContext( ) );
		this.adapter = new ListProjectsAdapter( this.presenter.clickItemListProject( ) );
		
		this.presenter.setViewList( this );
		this.presenter.initViewList( this.recycler_view, this.adapter, this.layout_manager );
		
		return v;
	}
	
	@Override
	public void onStart( ) {
		super.onStart( );
		
		this.btn_agregar.setOnClickListener( this.presenter.clickAgregarProyecto( ) );
		
	}
	
	@Override
	public void setListProjects( List< Project > projects ) {
		this.adapter.setListProjects( projects );
	}
	
	@Override
	public RecyclerView getLista( ) {
		return this.recycler_view;
	}
	
	@Override
	public TextView getInfoText( ) {
		return this.no_hay;
	}
	
	@Override
	public void setPresenter( IProjectDashboard.Presenter presenter ) {
		this.presenter = presenter;
	}
}
