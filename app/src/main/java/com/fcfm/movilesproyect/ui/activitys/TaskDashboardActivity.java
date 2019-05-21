package com.fcfm.movilesproyect.ui.activitys;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.presenter.TaskDashboardPresenter;
import com.fcfm.movilesproyect.presenter.interfaces.ITaskDashboardMVP;

public class TaskDashboardActivity extends AppCompatActivity implements ITaskDashboardMVP.View {
	
	private ITaskDashboardMVP.Presenter presenter;
	
	private DrawerLayout   drawerLayout;
	private NavigationView navigationView;
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_task_dashboard );
		
		this.drawerLayout = ( DrawerLayout ) findViewById( R.id.drawer_dashbord_layout );
		this.navigationView = ( NavigationView ) findViewById( R.id.nav_dashbord_view );
		
		Utilidades.setUserInfo( this.navigationView.getHeaderView( 0 ) );
		Utilidades.checkedMenuItem( this.navigationView, 2 );
		
		this.presenter = new TaskDashboardPresenter( this );
		this.presenter.initDashboard( this.getSupportFragmentManager( ) );
	}
	
	@Override
	protected void onStart( ) {
		super.onStart( );
		
		this.drawerLayout.addDrawerListener( this.presenter.clickDrawerListener( ) );
		this.navigationView
				.setNavigationItemSelectedListener( this.presenter.clickNavigationView( ) );
	}
	
	@Override
	public AppCompatActivity getActivity( ) {
		return this;
	}
	
	@Override
	public Context getContext( ) {
		return this;
	}
	
	@Override
	public ITaskDashboardMVP.Presenter getPresenter( ) {
		return this.presenter;
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
}
