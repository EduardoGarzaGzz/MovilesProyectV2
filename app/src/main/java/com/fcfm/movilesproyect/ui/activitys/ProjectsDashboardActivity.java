package com.fcfm.movilesproyect.ui.activitys;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.presenter.interfaces.IProjectDashboard;
import com.fcfm.movilesproyect.presenter.ProjectDashboardPresenter;
import com.fcfm.movilesproyect.ui.fragments.ListaProjectsFragment;

public class ProjectsDashboardActivity extends AppCompatActivity implements IProjectDashboard.View {
	
	private IProjectDashboard.Presenter presenter;
	
	private DrawerLayout   drawerLayout;
	private NavigationView navigationView;
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_projects_dashboard );
		
		this.drawerLayout = ( DrawerLayout ) findViewById( R.id.drawer_dashbord_layout );
		this.navigationView = ( NavigationView ) findViewById( R.id.nav_dashbord_view );
		
		this.presenter = new ProjectDashboardPresenter( this );
		this.presenter.init( );
		
		Utilidades.setUserInfo( this.navigationView.getHeaderView( 0 ) );
		Utilidades.checkedMenuItem( this.navigationView, 1 );
		
		Fragment f = Utilidades.changeFragment( getSupportFragmentManager( ),
		                                        R.id.frag_dashbord_project_content,
		                                        new ListaProjectsFragment( ) );
		
		( ( ListaProjectsFragment ) f ).setPresenter( this.presenter );
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
	
	@Override
	public IProjectDashboard.Presenter getPresenter( ) {
		return this.presenter;
	}
}
