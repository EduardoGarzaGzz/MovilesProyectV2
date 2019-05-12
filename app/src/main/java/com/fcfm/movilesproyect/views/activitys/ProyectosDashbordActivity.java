package com.fcfm.movilesproyect.views.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.fcfm.movilesproyect.R;

public class ProyectosDashbordActivity extends AppCompatActivity {
	
	private Context this_context;
	
	private DrawerLayout   drawerLayout;
	private NavigationView navigationView;
	
	private FrameLayout frame_layout;
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_proyectos_dashbord );
		
		this.this_context = this;
		
		this.setToolbar( );
		
		this.drawerLayout = ( DrawerLayout ) findViewById( R.id.drawer_dashbord_layout );
		this.navigationView = ( NavigationView ) findViewById( R.id.nav_dashbord_view );
		this.frame_layout = ( FrameLayout ) findViewById( R.id.frag_dashbord_citas_content );
		
		this.navigationView.getMenu( ).getItem( 1 ).setChecked( true );
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
						
						switch ( menuItem.getItemId( ) ) {
							// TODO agregar las opciones de Navigation Drawer
							case R.id.menu_dashbord:
								startActivity( new Intent( this_context, DashbordActivity.class ) );
								finish( );
								break;
							case R.id.menu_proyectos:
								Toast.makeText( this_context, "Ya estas en proyectos",
								                Toast.LENGTH_LONG ).show( );
								break;
							case R.id.menu_citas:
								startActivity(
										new Intent( this_context, CitasDashbordActivity.class ) );
								finish();
								break;
						}
						
						return true;
					}
				} );
	}
	
	private void setToolbar( ) {
		Toolbar toolbar = ( Toolbar ) findViewById( R.id.toolbar );
		toolbar.setTitle( "Proyectos " );
		setSupportActionBar( toolbar );
		getSupportActionBar( ).setHomeAsUpIndicator( R.drawable.ic_buerger_menu );
		getSupportActionBar( ).setDisplayHomeAsUpEnabled( true );
	}
}
