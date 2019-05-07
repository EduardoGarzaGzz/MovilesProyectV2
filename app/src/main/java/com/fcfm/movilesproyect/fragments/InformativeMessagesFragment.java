package com.fcfm.movilesproyect.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.adapters.InfoMsgAdapter;
import com.fcfm.movilesproyect.apis.InfoMsgAPIService;
import com.fcfm.movilesproyect.configurations.ApiUtils;
import com.fcfm.movilesproyect.configurations.RetrofitClient;
import com.fcfm.movilesproyect.models.InfoMessages;
import com.fcfm.movilesproyect.models.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class InformativeMessagesFragment extends Fragment {
	
	private Context this_context;
	
	private RecyclerView               recycler_view;
	private RecyclerView.Adapter       adapter;
	private RecyclerView.LayoutManager layout_manager;
	
	public InformativeMessagesFragment( ) {
		// Required empty public constructor
	}
	
	
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container,
							  Bundle savedInstanceState ) {
		View v = inflater.inflate( R.layout.fragment_informative_messages, container, false );
		
		this.this_context = getContext( );
		
		this.recycler_view = ( RecyclerView ) v.findViewById(
				R.id.rv_informative_msg_lista_my_msg );
		
		this.layout_manager = new LinearLayoutManager( getContext( ) );
		
		this.adapter = new InfoMsgAdapter(
				User.getUser_active( ).getId( ),
				R.layout.recycler_view_info_msg,
				new InfoMsgAdapter.OnItemClickListener( ) {
					@Override
					public void onItemClick( InfoMessages msg, int posicion ) {
					
					}
				} );
		
		this.recycler_view.setHasFixedSize( true );
		this.recycler_view.setItemAnimator( new DefaultItemAnimator( ) );
		
		this.recycler_view.setLayoutManager( this.layout_manager );
		this.recycler_view.setAdapter( this.adapter );
		
		this.adapter.notifyDataSetChanged( );
		return v;
	}
	
}
