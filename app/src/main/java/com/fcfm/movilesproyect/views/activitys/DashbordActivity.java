package com.fcfm.movilesproyect.views.activitys;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.adapters.PagerDashbordAdapter;

import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.interfaces.IDashbordMVP;
import com.fcfm.movilesproyect.models.User;
import com.fcfm.movilesproyect.presenter.DashbordPresenter;


public class DashbordActivity extends AppCompatActivity implements IDashbordMVP.View {
	
	
	private IDashbordMVP.Presenter presenter;
	
	private DrawerLayout   drawerLayout;
	private NavigationView navigationView;
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_dashbord );
		
		this.drawerLayout = ( DrawerLayout ) findViewById( R.id.drawer_dashbord_layout );
		this.navigationView = ( NavigationView ) findViewById( R.id.nav_dashbord_view );
		
		this.presenter = new DashbordPresenter( this );
		this.presenter.init( );
	}
	
	@Override
	protected void onStart( ) {
		super.onStart( );
		
		// TODO ver si nos sirve xD
		this.drawerLayout.addDrawerListener( new DrawerLayout.DrawerListener( ) {
			@Override
			public void onDrawerSlide( @NonNull View view, float v ) {
			
			}
			
			@Override
			public void onDrawerOpened( @NonNull View view ) {
			
			}
			
			@Override
			public void onDrawerClosed( @NonNull View view ) {
			
			}
			
			@Override
			public void onDrawerStateChanged( int i ) {
			
			}
		} );
		
		this.navigationView.setNavigationItemSelectedListener(
				new NavigationView.OnNavigationItemSelectedListener( ) {
					@Override
					public boolean onNavigationItemSelected( @NonNull MenuItem menuItem ) {
						presenter.clickNavigationItem( menuItem.getItemId( ) );
						return true;
					}
				} );
	}
	
	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {
		
		if ( this.presenter.clickOptionItem( this.drawerLayout, item.getItemId( ) ) ) return true;
		else return super.onOptionsItemSelected( item );
	}
	
	@Override
	public AppCompatActivity getActivity( ) {
		return this;
	}
	
	@Override
	public NavigationView getNavigationView( ) {
		return this.navigationView;
	}
	
	@Override
	public Context getContext( ) {
		return this;
	}
	
	@Override
	public void setToolbar( ) {
		Utilidades.setToolbar( this, "Bienvenido " + User.getUser_active( ).getNombres( ) );
	}
	
	@Override
	public void setTabLayaout( ) {
		
		TabLayout       tab_layout = ( TabLayout ) findViewById( R.id.tab_layout );
		final ViewPager view_pager = ( ViewPager ) findViewById( R.id.view_pager );
		
		tab_layout.addTab( tab_layout.newTab( ).setText( "Opciones" ) );
		tab_layout.addTab( tab_layout.newTab( ).setText( "Info mensages" ) );
		tab_layout.addTab( tab_layout.newTab( ).setText( "Configuracion" ) );
		tab_layout.setTabGravity( TabLayout.GRAVITY_FILL );
		
		
		PagerDashbordAdapter pager_adapter = new PagerDashbordAdapter( getSupportFragmentManager( ),
		                                                               tab_layout.getTabCount( ),
		                                                               this.presenter );
		
		view_pager.setAdapter( pager_adapter );
		view_pager.addOnPageChangeListener(
				new TabLayout.TabLayoutOnPageChangeListener( tab_layout ) );
		
		tab_layout.addOnTabSelectedListener( new TabLayout.OnTabSelectedListener( ) {
			@Override
			public void onTabSelected( TabLayout.Tab tab ) {
				view_pager.setCurrentItem( tab.getPosition( ) );
			}
			
			@Override
			public void onTabUnselected( TabLayout.Tab tab ) {
			
			}
			
			@Override
			public void onTabReselected( TabLayout.Tab tab ) {
			
			}
		} );
		
	}
}
