package com.fcfm.movilesproyect.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.ui.adapters.InfoMsgAdapter;
import com.fcfm.movilesproyect.presenter.interfaces.IDashbordMVP;
import com.fcfm.movilesproyect.db.models.InfoMessages;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InformativeMessagesFragment extends Fragment implements IDashbordMVP.View.InfoMsg {
	
	private IDashbordMVP.Presenter presenter;
	
	private RecyclerView               recycler_view;
	private InfoMsgAdapter             adapter;
	private RecyclerView.LayoutManager layout_manager;
	
	public InformativeMessagesFragment( ) {
		// Required empty public constructor
	}
	
	
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container,
	                          Bundle savedInstanceState ) {
		View v = inflater.inflate( R.layout.fragment_informative_messages, container, false );
		
		this.recycler_view = ( RecyclerView ) v.findViewById(
				R.id.rv_informative_msg_lista_my_msg );
		
		this.layout_manager = new LinearLayoutManager( getContext( ) );
		
		this.adapter = new InfoMsgAdapter( this.presenter.clickItemInfoMsgAdapter( ) );
		
		this.presenter.initInfoMsg( this );
		this.presenter.initInfoMsgRecyclerView( this.recycler_view, this.adapter, this.layout_manager );

		return v;
	}
	
	public void setPresenter( IDashbordMVP.Presenter presenter ) {
		this.presenter = presenter;
	}
	
	
	@Override
	public void setListOfInfoMsg( List< InfoMessages > msg ) {
		this.adapter.setMsg( msg );
	}
}
