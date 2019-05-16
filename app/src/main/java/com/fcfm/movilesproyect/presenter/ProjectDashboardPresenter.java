package com.fcfm.movilesproyect.presenter;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.interfaces.IProjectDashboard;
import com.fcfm.movilesproyect.models.User;
import com.fcfm.movilesproyect.views.fragments.ListaProjectsFragment;
import com.fcfm.movilesproyect.views.fragments.RegistrarProjectFragment;

public class ProjectDashboardPresenter implements IProjectDashboard.Presenter {
	
	private IProjectDashboard.View      view;
	private IProjectDashboard.View.List view_list;
	private IProjectDashboard.View.Form view_form;
	
	public ProjectDashboardPresenter( IProjectDashboard.View view ) {
		this.view = view;
	}
	
	@Override
	public void init( ) {
	
	}
	
	@Override
	public void setViewList( IProjectDashboard.View.List view_list ) {
		this.view_list = view_list;
	}
	
	@Override
	public void setViewForm( IProjectDashboard.View.Form view_form ) {
	this.view_form = view_form;
	}
	
	@Override
	public void initViewList( ) {
		
		Utilidades.setToolbar( this.view.getActivity( ),
		                       "Proyectos de " + User.getUser_active( ).getNombres( ) );
		
		this.view_list.getLista( ).setVisibility( View.INVISIBLE );
		
	}
	
	@Override
	public void initViewForm( ) {
		Utilidades.setToolbar( this.view.getActivity( ), "Agregar un nuevo proyecto" );
	}
	
	@Override
	public View.OnClickListener clickAgregarProyecto( ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				Fragment f = Utilidades.changeFragment(
						view.getActivity( ).getSupportFragmentManager( ),
						R.id.frag_dashbord_project_content, new RegistrarProjectFragment( ) );
				( ( RegistrarProjectFragment ) f ).setPresenter( view.getPresenter( ) );
			}
		};
	}
	
	@Override
	public View.OnClickListener clickActionProyecto( ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
			
			}
		};
	}
	
	@Override
	public View.OnClickListener clickCacelarProyecto( ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				Fragment f = Utilidades.changeFragment(
						view.getActivity( ).getSupportFragmentManager( ),
						R.id.frag_dashbord_project_content, new ListaProjectsFragment( ) );
				( ( ListaProjectsFragment ) f ).setPresenter( view.getPresenter( ) );
			}
		};
	}
	
	@Override
	public DrawerLayout.DrawerListener clickDrawerListener( ) {
		return new DrawerLayout.DrawerListener( ) {
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
		};
	}
	
	@Override
	public NavigationView.OnNavigationItemSelectedListener clickNavigationView( ) {
		return new NavigationView.OnNavigationItemSelectedListener( ) {
			@Override
			public boolean onNavigationItemSelected( @NonNull MenuItem menuItem ) {
				if ( R.id.menu_proyectos == menuItem.getItemId( ) )
					Utilidades.printToast( view.getContext( ), "Ya estas en proyectos" );
				else
					Utilidades.openActivityWithItemId( view.getActivity( ), menuItem.getItemId( ) );
				
				return true;
			}
		};
	}
}
