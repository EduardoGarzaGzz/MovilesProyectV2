package com.fcfm.movilesproyect.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.presenter.CitaDashboardPresenter;
import com.fcfm.movilesproyect.presenter.interfaces.ICitaDashboardMVP;
import com.fcfm.movilesproyect.ui.fragments.ListaCitasFragment;

public class CitasDashbordActivity extends AppCompatActivity implements ICitaDashboardMVP.View {
	
	private ICitaDashboardMVP.Presenter presenter;
	
	private DrawerLayout drawerLayout;
	private NavigationView navigationView;
	
	private FrameLayout frame_layout;
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_citas_dashbord );
		
		
		this.drawerLayout = ( DrawerLayout ) findViewById( R.id.drawer_dashbord_layout );
		this.navigationView = ( NavigationView ) findViewById( R.id.nav_dashbord_view );
		this.frame_layout = ( FrameLayout ) findViewById( R.id.frag_dashbord_citas_content );
		
		this.presenter = new CitaDashboardPresenter( this );
		this.presenter.initCitaDashboard( );
	}
	
	@Override
	protected void onStart( ) {
		super.onStart( );
		
		this.drawerLayout.addDrawerListener( this.presenter.clickDrawerListener( ) );
		this.navigationView
				.setNavigationItemSelectedListener( this.presenter.clickNavigationView( ) );
	}
	
	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {
		
		switch ( item.getItemId( ) ) {
			case android.R.id.home:
				this.drawerLayout.openDrawer( GravityCompat.START );
				return true;
		}
		
		return super.onOptionsItemSelected( item );
	}
	
	@Override
	public AppCompatActivity getActivity( ) {
		return this;
	}
	
	@Override
	public Context getContext( ) {
		return this;
	}
	
	public ICitaDashboardMVP.Presenter getPresenter( ) {
		return presenter;
	}
}
