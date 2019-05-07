package com.fcfm.movilesproyect.activitys;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.adapters.PagerAdapter;
import com.fcfm.movilesproyect.fragments.DashbordFragment;
import com.fcfm.movilesproyect.models.User;


public class DashbordActivity extends AppCompatActivity {
	
	private DrawerLayout   drawerLayout;
	private NavigationView navigationView;
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_dashbord );
		setToolbar( );
		
		this.drawerLayout = ( DrawerLayout ) findViewById( R.id.drawer_dashbord_layout );
		this.navigationView = ( NavigationView ) findViewById( R.id.nav_dashbord_view );
		
		// TODO definir el fragmento por defecto
	}
	
	@Override protected void onStart( ) {
		super.onStart( );
		
		// TODO ver si nos sirve xD
		this.drawerLayout.addDrawerListener( new DrawerLayout.DrawerListener( ) {
			@Override public void onDrawerSlide( @NonNull View view, float v ) {
			
			}
			
			@Override public void onDrawerOpened( @NonNull View view ) {
			
			}
			
			@Override public void onDrawerClosed( @NonNull View view ) {
			
			}
			
			@Override public void onDrawerStateChanged( int i ) {
			
			}
		} );
		
		this.navigationView.setNavigationItemSelectedListener(
				new NavigationView.OnNavigationItemSelectedListener( ) {
					@Override
					public boolean onNavigationItemSelected( @NonNull MenuItem menuItem ) {
						
						boolean  fragment_trsnsaction = false;
						Fragment fragment             = null;
						
						switch ( menuItem.getItemId( ) ) {
							// TODO agregar las opciones de Navigation Drawer
							
							/*
							 * case R.id.opcion:
							 * 	fragment = new FragmentOpcion();
							 * 	fragmentTransaction = true;
							 * 	break;
							 *
							 * */
						}
						
						if ( fragment_trsnsaction == true ) {
							changeFragment( fragment, menuItem );
							drawerLayout.closeDrawers( );
						}
						
						return true;
					}
				} );
	}
	
	private void setToolbar( ) {
		
		Toolbar toolbar = ( Toolbar ) findViewById( R.id.toolbar );
		toolbar.setTitle( "Bienvenido " + User.getUser_active().getNombres() );
		setSupportActionBar( toolbar );
		getSupportActionBar( ).setHomeAsUpIndicator( R.drawable.ic_buerger_menu );
		getSupportActionBar( ).setDisplayHomeAsUpEnabled( true );
		
		TabLayout       tab_layout = ( TabLayout ) findViewById( R.id.tab_layout );
		final ViewPager view_pager = ( ViewPager ) findViewById( R.id.view_pager );
		
		tab_layout.addTab( tab_layout.newTab( ).setText( "Opciones" ) );
		tab_layout.addTab( tab_layout.newTab( ).setText( "Info mensages" ) );
		tab_layout.addTab( tab_layout.newTab( ).setText( "Configuracion" ) );
		tab_layout.setTabGravity( TabLayout.GRAVITY_FILL );
		
		PagerAdapter pager_adapter = new PagerAdapter( getSupportFragmentManager( ),
													   tab_layout.getTabCount( ) );
		
		view_pager.setAdapter( pager_adapter );
		view_pager.addOnPageChangeListener(
				new TabLayout.TabLayoutOnPageChangeListener( tab_layout ) );
		
		tab_layout.addOnTabSelectedListener( new TabLayout.OnTabSelectedListener( ) {
			@Override public void onTabSelected( TabLayout.Tab tab ) {
				view_pager.setCurrentItem( tab.getPosition() );
			}
			
			@Override public void onTabUnselected( TabLayout.Tab tab ) {
			
			}
			
			@Override public void onTabReselected( TabLayout.Tab tab ) {
			
			}
		} );
		
		
	}
	
	@Override public boolean onOptionsItemSelected( MenuItem item ) {
		
		switch ( item.getItemId( ) ) {
			case android.R.id.home:
				drawerLayout.openDrawer( GravityCompat.START );
				return true;
		}
		
		return super.onOptionsItemSelected( item );
	}
	
	private void changeFragment( Fragment fragment, MenuItem item ) {
		//		getSupportFragmentManager( )
		//				.beginTransaction( )
		//				.replace( R.id.content_dashbord_frame, fragment )
		//				.commit( );
		//
		//		item.setChecked( true );
		//		getSupportActionBar().setTitle( item.getTitle() );
	}
}
