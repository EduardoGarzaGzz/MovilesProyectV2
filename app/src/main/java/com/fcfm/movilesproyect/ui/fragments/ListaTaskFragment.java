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
import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.db.models.Project;
import com.fcfm.movilesproyect.presenter.interfaces.ITaskDashboardMVP;
import com.fcfm.movilesproyect.ui.adapters.ListProjectsAdapter;
import com.fcfm.movilesproyect.ui.adapters.ListTaskAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaTaskFragment extends Fragment implements ITaskDashboardMVP.View.ListTask {
	
	private ITaskDashboardMVP.Presenter presenter;
	
	private RecyclerView recycler_view;
	private ListTaskAdapter adapter;
	private RecyclerView.LayoutManager layout_manager;
	
	private TextView no_hay;
	private EditText serch;
	private Button btn_agregar;
	
	public ListaTaskFragment( ) {
		// Required empty public constructor
	}
	
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container,
	                          Bundle savedInstanceState ) {
		// Inflate the layout for this fragment
		View view = inflater.inflate( R.layout.fragment_lista_task, container, false );
		
		this.recycler_view = view.findViewById( R.id.rv_frac_lista_task_list );
		this.layout_manager = new LinearLayoutManager( getContext( ) );
		this.adapter = new ListTaskAdapter( R.layout.recycler_view_list_task, this.presenter.clickItemListTask( ) );
		
		this.no_hay = view.findViewById( R.id.tv_frac_lista_task_no_hay );
		this.serch = view.findViewById( R.id.ed_frac_lista_task_serch );
		this.btn_agregar = view.findViewById( R.id.btn_frac_lista_task_add );
		
		this.presenter.setViewList( this );
		this.presenter.initListTask( this.recycler_view, this.adapter, this.layout_manager );
		return view;
	}
	
	@Override
	public void onStart( ) {
		super.onStart( );
		
		this.btn_agregar.setOnClickListener( this.presenter.clickAgregarTask( ) );
	}
	
	@Override
	public void setPresenter( ITaskDashboardMVP.Presenter presenter ) {
		this.presenter = presenter;
	}
	
	@Override
	public ListaTaskFragment getFragment( ) {
		return this;
	}
	
	public TextView getNo_hay( ) {
		return no_hay;
	}
	
	public EditText getSerch( ) {
		return serch;
	}
	
	public Button getBtn_agregar( ) {
		return btn_agregar;
	}
	
}
