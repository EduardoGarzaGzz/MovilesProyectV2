package com.fcfm.movilesproyect.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.presenter.interfaces.IDashbordMVP;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashbordFragment extends Fragment implements IDashbordMVP.View.Dashbord {
	
	private IDashbordMVP.Presenter presenter;
	
	private ImageView projects;
	private ImageView tasks;
	private ImageView friends;
	private ImageView cites;
	
	public DashbordFragment( ) { }
	
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container,
	                          Bundle savedInstanceState ) {
		// Inflate the layout for this fragment
		View tmp_view = inflater.inflate( R.layout.fragment_dashbord, container, false );
		
		this.projects = tmp_view.findViewById( R.id.im_frac_dashbord_projects );
		this.tasks = tmp_view.findViewById( R.id.im_frac_dashbord_task );
		this.friends = tmp_view.findViewById( R.id.im_frac_dashbord_friends );
		this.cites = tmp_view.findViewById( R.id.im_frac_dashbord_cites );
		
		return tmp_view;
	}
	
	@Override
	public void onStart( ) {
		super.onStart( );
		
		this.projects.setOnClickListener( this.presenter.clickQuickProjects( ) );
		this.tasks.setOnClickListener( this.presenter.clickQuickTask( ) );
		this.friends.setOnClickListener( this.presenter.clickQuickFriends( ) );
		this.cites.setOnClickListener( this.presenter.clickQuickCites( ) );
		
	}
	
	@Override
	public void setPresenter( IDashbordMVP.Presenter presenter ) {
		this.presenter = presenter;
	}
}
