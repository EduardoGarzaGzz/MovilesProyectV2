package com.fcfm.movilesproyect.presenter;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.fcfm.movilesproyect.R;
import com.fcfm.movilesproyect.db.viewmodel.ProjectViewModel;
import com.fcfm.movilesproyect.ui.adapters.ListProjectsAdapter;

import com.fcfm.movilesproyect.configurations.Utilidades;
import com.fcfm.movilesproyect.presenter.interfaces.IProjectDashboard;
import com.fcfm.movilesproyect.db.models.Project;
import com.fcfm.movilesproyect.db.models.User;
import com.fcfm.movilesproyect.ui.fragments.ListaProjectsFragment;
import com.fcfm.movilesproyect.ui.fragments.RegistrarProjectFragment;


import java.util.List;


public class ProjectDashboardPresenter implements IProjectDashboard.Presenter {
	
	private ProjectViewModel model;
	
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
	public void initViewList( final RecyclerView recycler_view, final RecyclerView.Adapter adapter,
	                          RecyclerView.LayoutManager layout_manager ) {
		
		Utilidades.setToolbar( this.view.getActivity( ),
		                       "Proyectos de " + User.getUser_active( ).getNombres( ) );
		
		recycler_view.setHasFixedSize( true );
		recycler_view.setItemAnimator( new DefaultItemAnimator( ) );
		recycler_view.setLayoutManager( layout_manager );
		recycler_view.setAdapter( adapter );
		
		
		this.view_list.getInfoText( ).setText( "Buscando proyectos" );
		
		this.model = ViewModelProviders.of( this.view.getActivity( ) )
		                               .get( ProjectViewModel.class );
		this.model.getProjects( )
		          .observe( this.view.getActivity( ), new Observer< List< Project > >( ) {
			          @Override
			          public void onChanged( @Nullable List< Project > projects ) {
				
				          if ( projects.size( ) > 0 ) view_list.getInfoText( ).setText( "" );
				          else view_list.getInfoText( ).setText( "No hay proyectos" );
				
				          view_list.setListProjects( projects );
			          }
		          } );
	}
	
	@Override
	public void initViewForm( ) {
		if ( view_form.getStatus( ) )
			Utilidades.setToolbar( this.view.getActivity( ), "Agregar un nuevo proyecto" );
		else Utilidades.setToolbar( this.view.getActivity( ), "Editar proyecto" );
	}
	
	@Override
	public ListProjectsAdapter.OnItemClickListener clickItemListProject( ) {
		return new ListProjectsAdapter.OnItemClickListener( ) {
			@Override
			public void onItemClick( Project project, int position ) {
				Fragment f = Utilidades.changeFragment(
						view.getActivity( ).getSupportFragmentManager( ),
						R.id.frag_dashbord_project_content, new RegistrarProjectFragment( ) );
				( ( RegistrarProjectFragment ) f ).setPresenter( view.getPresenter( ) );
				( ( RegistrarProjectFragment ) f ).setStatus( false );
				( ( RegistrarProjectFragment ) f ).setProject( project );
			}
		};
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
				( ( RegistrarProjectFragment ) f ).setStatus( true );
			}
		};
	}
	
	@Override
	public View.OnClickListener clickActionProyecto( ) {
		return new View.OnClickListener( ) {
			@Override
			public void onClick( View v ) {
				model = ViewModelProviders.of( view.getActivity( ) ).get( ProjectViewModel.class );
				
				if ( view_form.getStatus( ) )
					model.newProject( view.getContext( ), view_form.getProject( ) );
				else model.updateProject( view.getContext( ), view_form.getProject( ) );
				
				Fragment f = Utilidades.changeFragment(
						view.getActivity( ).getSupportFragmentManager( ),
						R.id.frag_dashbord_project_content, new ListaProjectsFragment( ) );
				( ( ListaProjectsFragment ) f ).setPresenter( view.getPresenter( ) );
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
					Utilidades.printToastInfo( view.getContext( ), "Ya estas en proyectos" );
				else
					Utilidades.openActivityWithItemId( view.getActivity( ), menuItem.getItemId( ) );
				
				return true;
			}
		};
	}
}
